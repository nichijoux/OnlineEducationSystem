import request from '@/utils/request'

const API_PATH = 'admin/edu/course'

export default {
    // 获取所有课程
    getAllCourse() {
        return request({
            url: `${API_PATH}/getAllCourse`,
            method: 'GET'
        })
    },
    // 分页查询课程信息
    pageQueryCourse(index, limit, queryCondition) {
        return request({
            url: `${API_PATH}/pageQueryCourse/${index}/${limit}`,
            method: "POST",
            data: queryCondition
        })
    },
    // 根据课程id获取课程基本信息
    getCourse(courseId) {
        return request({
            url: `${API_PATH}/getCourse/${courseId}`,
            method: "GET",
        })
    },
    // 添加课程基本信息
    addCourse(courseInfo) {
        return request({
            url: `${API_PATH}/addCourse`,
            method: "POST",
            data: courseInfo
        })
    },
    // 根据课程id更新课程基本信息
    updateCourse(courseInfo) {
        return request({
            url: `${API_PATH}/updateCourse`,
            method: "POST",
            data: courseInfo
        })
    },
    // 根据课程id删除课程信息,包括其下的小节和章节
    deleteCourse(courseId) {
        return request({
            url: `${API_PATH}/deleteCourse/${courseId}`,
            method: 'DELETE'
        })
    },
    // 根据课程id获取课程发布时要显示的信息
    getPublishCourse(courseId) {
        return request({
            url: `${API_PATH}/getPublishCourse/${courseId}`,
            method: "GET",
        })
    },
    // 根据课程id发布课程
    publishCourse(courseId) {
        return request({
            url: `${API_PATH}/publishCourse/${courseId}`,
            method: "POST"
        })
    }

}