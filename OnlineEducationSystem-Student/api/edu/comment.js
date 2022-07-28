import request from '@/utils/request'

const API_PATH = 'user/edu/comment'

export default {
    // 获取课程列表
    pageQueryComment(courseId, index, limit,) {
        return request({
            url: `${API_PATH}/pageQueryComment/${courseId}/${index}/${limit}`,
            method: 'GET',
        })
    },
    // 添加评论
    addComment(comment) {
        return request({
            url: `${API_PATH}/addComment`,
            method: 'POST',
            data: comment
        })
    }
}