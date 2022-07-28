import request from '@/utils/request'

const API_PATH = 'admin/edu/user'

export function login(data) {
  return request({
    url: `${API_PATH}/login`,
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: `${API_PATH}/info`,
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: `${API_PATH}/logout`,
    method: 'post'
  })
}
