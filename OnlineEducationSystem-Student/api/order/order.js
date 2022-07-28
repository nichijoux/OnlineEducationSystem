import request from '@/utils/request'

const API_PATH = 'user/order/payOrder'

const LOG_API_PATH = 'user/order/payLog'

export default {
    // 生成订单
    createOrder(courseId) {
        return request({
            url: `${API_PATH}/generateOrder/${courseId}`,
            method: 'POST'
        })
    },
    // 根据订单号查询订单信息
    getOrder(orderNo) {
        return request({
            url: `${API_PATH}/getOrderInfo/${orderNo}`,
            method: 'GET'
        })
    },
    // 生成二维码的方法
    createNative(orderNo) {
        return request({
            url: `${LOG_API_PATH}/generateOrCode/${orderNo}`,
            method: 'GET'
        })
    },
    // 查询支付状态的方法
    queryOrderStatus(orderNo) {
        return request({
            url: `${LOG_API_PATH}/queryOrderState/${orderNo}`,
            method: 'GET'
        })
    }
}
