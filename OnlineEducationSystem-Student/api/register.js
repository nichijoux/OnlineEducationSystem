import request from '@/utils/request'

const API_PATH = 'user/ucenter/member'
const SMS_API_PATH = 'user/sms'

export default {
    // 提交注册信息
    submitRegister(params) {
        return request({
            url: `${API_PATH}/userRegister`,
            method: 'post',
            data: params
        })
    },

    // 获取验证码
    getVerificationCode(phone) {
        return request({
            url: `${SMS_API_PATH}/sendVerificationCode/${phone}`,
            method: 'get'
        })
    }
}