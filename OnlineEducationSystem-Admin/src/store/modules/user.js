import loginAPI from '@/api/login'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    // 用户名
    name: '',
    // 用户头像
    avatar: '',
    // 用户角色
    roles: [],
    // 权限值列表
    permissionList: []
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  SET_PERMISSION_VALUE_LIST: (state, permissionList) => {
    state.permissionList = permissionList
  }
}

const actions = {
  // 用户登录
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      loginAPI.login(username.trim(), password).then(response => {
        const token = response.data
        commit('SET_TOKEN', token)
        // 设置cookie中的token信息
        setToken(token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 获取用户信息
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      loginAPI.getInfo(state.token).then(response => {
        // 获取返回的数据
        const data = response.data

        if (!data) {
          return reject('校验失败,请重新登录')
        }
        // 获取数据中的设置值
        const { name, roles, avatar, permissionValueList } = data
        if (roles && roles.length > 0) {
          // 设置角色
          commit('SET_ROLES', roles)
        } else {
          // 如果没有角色,则直接拒绝
          reject("用户不存在角色!!!")
        }

        // 设置用户名
        commit('SET_NAME', name)
        // 设置头像
        commit('SET_AVATAR', avatar)
        // 设置权限值
        commit('SET_PERMISSION_VALUE_LIST', permissionValueList)

        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 用户退出
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      loginAPI.logout(state.token).then(() => {
        removeToken() // 先移除token
        resetRouter() // 再重置router
        commit('RESET_STATE')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 清除token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

