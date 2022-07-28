import request from '@/utils/request'

const API_PATH = 'admin/auth'

export default {
  // 登录
  login(username, password) {
    return request({
      url: `${API_PATH}/login`,
      method: 'POST',
      data: {
        username,
        password
      }
    })
  },
  // 获取用户信息
  getInfo(token) {
    return request({
      url: `${API_PATH}/index/info`,
      method: 'GET',
      params: { token }
    })
  },
  // 登出
  logout() {
    return request({
      url: `${API_PATH}/index/logout`,
      method: 'POST'
    })
  },
  // 获取菜单
  getMenu() {
    return request({
      url: `${API_PATH}/index/menu`,
      method: 'GET'
    })
  }
}