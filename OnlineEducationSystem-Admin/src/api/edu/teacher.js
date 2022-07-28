import request from '@/utils/request'

const API_PATH = 'admin/edu/teacher'

export default {
    // 获取所有讲师
    getAllTeacher() {
        return request({
            url: `${API_PATH}/getAllTeacher`,
            method: 'GET'
        })
    },
    // 根据讲师id获取讲师
    getTeacher(teacherId) {
        return request({
            url: `${API_PATH}/getTeacher/${teacherId}`,
            method: 'GET'
        })
    },
    // 分页查询讲师信息
    pageQueryTeacher(index, limit) {
        return request({
            url: `${API_PATH}/pageQueryTeacher/${index}/${limit}`,
            method: 'POST',
        })
    },
    // 多条件分页查询讲师信息
    pageQueryTeacher(index, limit, queryCondition) {
        return request({
            url: `${API_PATH}/pageQueryTeacher/${index}/${limit}`,
            method: 'POST',
            data: queryCondition
        })
    },
    // 添加讲师信息
    addTeacher(teacher) {
        return request({
            url: `${API_PATH}/addTeacher`,
            method: 'POST',
            data: teacher
        })
    },
    // 更新讲师信息
    updateTeacher(teacher) {
        return request({
            url: `${API_PATH}/updateTeacher`,
            method: 'POST',
            data: teacher
        })
    },
    // 根据id删除讲师
    deleteTeacher(teacherId) {
        return request({
            url: `${API_PATH}/deleteTeacher/${teacherId}`,
            method: 'DELETE',
        })
    }
}