import request from '@/utils/request'

const API_PATH = 'admin/ucenter/member'

export default {
    // 分页查询用户
    pageQueryMember(index, limit, queryCondition) {
        return request({
            url: `${API_PATH}/pageQueryMember/${index}/${limit}`,
            method: 'POST',
            data: queryCondition
        })
    },
    // 禁用或者启用某个用户
    enableOrDisableMember(userId, value) {
        return request({
            url: `${API_PATH}/enableOrDisableMember/${userId}`,
            method: 'POST',
            params: { "isEnable": value }
        })
    }
}