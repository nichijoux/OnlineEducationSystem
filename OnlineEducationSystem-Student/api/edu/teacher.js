import request from '@/utils/request'

const API_PATH = 'user/edu/teacher'

export default {
    // 获得讲师列表的接口
    pageQueryTeacher(index, limit) {
        return request({
            url: `${API_PATH}/pageQueryTeacher/${index}/${limit}`,
            method: 'GET'
        })
    },
    // 获取讲师及其所讲课程
    getTeacherAndCourse(teacherId) {
        return request({
            url: `${API_PATH}/getTeacherAndCourse/${teacherId}`,
            method: 'GET'
        })
    }
}