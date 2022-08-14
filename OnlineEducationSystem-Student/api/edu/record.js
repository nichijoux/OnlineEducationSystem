import request from '@/utils/request'

const API_PATH = 'user/edu/studyRecord'

export default {
    // 添加学习记录
    addStudyRecord(teacherName, courseName, courseCover, videoName, videoSourceId, courseId) {
        return request({
            url: `${API_PATH}/addStudyRecord/${teacherName}/${courseName}/${videoName}/${videoSourceId}/${courseId}`,
            method: "POST",
            params: {
                courseCover: courseCover,
            }
        })
    },
    // 分页查询学习记录
    pageQueryUserStudyRecord(index, limit) {
        return request({
            url: `${API_PATH}/pageQueryUserStudyRecord/${index}/${limit}`,
            method: 'POST'
        })
    }
}