import request from '@/utils/request'

const API_PATH = 'admin/order/payOrder'

export default {
    // 分页查询订单信息
    pageQueryOrder(index, limit, queryCondition) {
        return request({
            url: `${API_PATH}/pageQueryOrder/${index}/${limit}`,
            method: 'POST',
            data: queryCondition
        })
    }
}