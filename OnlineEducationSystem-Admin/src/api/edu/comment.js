import request from '@/utils/request'

const API_PATH = 'admin/edu/comment'

export default {
    // 分页查询获取评论信息
    pageQueryComment(index, limit, queryCondition) {
        return request({
            url: `${API_PATH}/pageQueryComment/${index}/${limit}`,
            method: 'POST',
            data: queryCondition
        })
    },
    // 删除评论
    deleteComment(commentId) {
        return request({
            url: `${API_PATH}/deleteComment/${commentId}`,
            method: 'DELETE'
        })
    }
}