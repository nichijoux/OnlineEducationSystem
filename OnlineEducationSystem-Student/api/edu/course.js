import request from '@/utils/request'

const API_PATH = 'user/edu/course';

export default {
    // 分页查询分页列表
    pageQueryCourse(index, limit,queryCondition) {
        return request({
            url: `${API_PATH}/pageQueryCourse/${index}/${limit}`,
            method: 'POST',
            data : queryCondition
        })
    },
    // 查询课程信息
    getCourseInfo(courseId) {
        return request({
            url: `${API_PATH}/getCourseInfo/${courseId}`,
            method: 'GET',
        })
    },
}