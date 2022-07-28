import request from '@/utils/request'

const API_PATH = '/admin/auth/role'

export default {
    // 分页查询角色信息
    pageQueryRole(index, limit, queryCondition) {
        return request({
            url: `${API_PATH}/pageQueryRole/${index}/${limit}`,
            method: 'POST',
            data: queryCondition // url查询字符串或表单键值对
        })
    },
    // 根据id获取用户角色
    getRole(roleId) {
        return request({
            url: `${API_PATH}/getRole/${roleId}`,
            method: 'get'
        })
    },
    // 添加角色
    addRole(role) {
        return request({
            url: `${API_PATH}/addRole`,
            method: 'POST',
            data: role
        })
    },
    // 修改角色
    updateRole(role) {
        return request({
            url: `${API_PATH}/updateRole`,
            method: 'POST',
            data: role
        })
    },
    // 删除角色
    deleteRole(userId) {
        return request({
            url: `${API_PATH}/deleteRole/${userId}`,
            method: 'DELETE'
        })
    },
    // 批量删除角色
    batchDeleteRole(roleIdList) {
        return request({
            url: `${API_PATH}/batchDeleteRole`,
            method: 'DELETE',
            data: roleIdList
        })
    },
}
