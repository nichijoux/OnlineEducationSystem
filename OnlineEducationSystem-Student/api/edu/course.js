import request from '@/utils/request'

const API_PATH = 'user/edu/course';

export default {
    // 分页查询分页列表
    pageQueryCourse(index, limit, queryCondition) {
        return request({
            url: `${API_PATH}/pageQueryCourse/${index}/${limit}`,
            method: 'POST',
            data: queryCondition
        })
    },
    // 查询课程信息
    getCourseInfo(courseId) {
        return request({
            url: `${API_PATH}/getCourseInfo/${courseId}`,
            method: 'GET',
        })
    },
    // 查询收藏课程
    pageQueryCollectCourse(index, limit) {
        return request({
            url: `${API_PATH}/pageQueryCollectCourse/${index}/${limit}`,
            method: 'GET',
        })
    },
    // 收藏课程
    collectCourse(courseId) {
        return request({
            url: `${API_PATH}/collectCourse/${courseId}`,
            method: 'POST',
        })
    },
    // 取消收藏课程
    cancelCollectCourse(courseId) {
        return request({
            url: `${API_PATH}/cancelCollectCourse/${courseId}`,
            method: 'POST',
        })
    }
}