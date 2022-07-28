// 导入路由脚本文件中的固定路由和动态路由
import { asyncRoutes, constantRoutes } from '@/router'
import loginAPI from '@/api/login'
// layout布局
import Layout from '@/layout/index'

/**
 * Use meta.role to determine if the current user has permission
 * 判断当前用户是否拥有该角色下的菜单信息
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
    if (route.meta && route.meta.roles) {
        return roles.some(role => route.meta.roles.includes(role))
    } else {
        return true
    }
}

/**
 * Filter asynchronous routing tables by recursion
 * 过滤出所拥有的的菜单信息
 * @param routes asyncRoutes
 * @param roles
 */
function filterAsyncRouter(asyncRouterMap) { // 遍历后台传来的路由字符串，转换为组件对象
    try {
        const accessedRouters = asyncRouterMap.filter(route => {
            if (route.component) {
                if (route.component === 'Layout') {
                    // Layout组件特殊处理
                    route.component = Layout
                } else {
                    // 否则则进行路由拼接
                    const component = route.component
                    route.component = (resolve) => require([`@/views${component}.vue`], resolve)
                }
            }
            // 如果有子菜单
            if (route.children && route.children.length) {
                route.children = filterAsyncRouter(route.children)
            }
            return true
        })
        return accessedRouters
    } catch (e) {
        console.log(e)
    }
}
const state = {
    routes: [],
    addRoutes: []
}

// 将路由信息保存到store中
const mutations = {
    SET_ROUTES: (state, routes) => {
        state.addRoutes = routes
        state.routes = constantRoutes.concat(routes)
    }
}

// 动态生成路由
const actions = {
    async generateRoutes({ commit }) {
        // generateRoutes
        const response = await loginAPI.getMenu()
        // 菜单获取
        return new Promise(resolve => {
            const accessedRouters = filterAsyncRouter(response.data)
            commit("SET_ROUTES", accessedRouters)
            resolve(accessedRouters)
        })
    }
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}