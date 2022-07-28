import request from "@/utils/request"

const API_PATH = "admin/auth/user"

export default {
    // 分页查询用户
    pageQueryUser(index, limit, queryCondition) {
        return request({
            url: `${API_PATH}/pageQueryUser/${index}/${limit}`,
            method: 'POST',
            data: queryCondition
        })
    },
    // 根据用户id获取用户
    getUser(userId) {
        return request({
            url: `${API_PATH}/getUser/${userId}`,
            method: 'GET'
        })
    },
    // 添加新的管理用户
    addUser(user) {
        return request({
            url: `${API_PATH}/addUser`,
            method: 'POST',
            data: user
        })
    },
    // 更新管理用户
    updateUser(user) {
        return request({
            url: `${API_PATH}/updateUser`,
            method: 'POST',
            data: user
        })
    },
    // 删除用户
    deleteUser(userId) {
        return request({
            url: `${API_PATH}/deleteUser/${userId}`,
            method: 'DELETE'
        })
    },
    // 批量删除用户
    batchDeleteUser(userIdList) {
        return request({
            url: `${API_PATH}/batchDeleteUser`,
            method: 'DELETE',
            data: userIdList
        })
    },
    // 获取用户被分配的角色
    getUserRole(userId) {
        return request({
            url: `${API_PATH}/getUserRole/${userId}`,
            method: 'GET'
        })
    },
    // 为用户分配角色
    assignRole(userId, roleIdList) {
        return request({
            url: `${API_PATH}/assignRole`,
            method: 'POST',
            params: { userId, roleIdList }
        })
    },
}