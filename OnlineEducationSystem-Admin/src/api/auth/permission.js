import request from '@/utils/request'

const API_PATH = '/admin/auth/permission'

export default {
  // 获取所有权限
  getAllPermission() {
    return request({
      url: `${API_PATH}/getAllPermission`,
      method: 'GET'
    })
  },
  // 添加新的权限菜单
  addPermission(menu) {
    return request({
      url: `${API_PATH}/addPermission`,
      method: "POST",
      data: menu
    })
  },
  // 更新权限菜单
  updatePermission(menu) {
    return request({
      url: `${API_PATH}/updatePermission`,
      method: "POST",
      data: menu
    })
  },
  // 递归删除权限菜单
  deletePermission(id) {
    return request({
      url: `${API_PATH}/deletePermission/${id}`,
      method: "DELETE"
    })
  },
  // 根据角色id获取角色的权限
  getRolePermission(roleId) {
    return request({
      url: `${API_PATH}/getRolePermission/${roleId}`,
      method: 'GET'
    })
  },
  // 给roleId的角色分配权限
  assignRolePermission(roleId, permissionIdList) {
    return request({
      url: `${API_PATH}/assignRolePermission`,
      method: "POST",
      params: { roleId, permissionIdList }
    })
  }
}
