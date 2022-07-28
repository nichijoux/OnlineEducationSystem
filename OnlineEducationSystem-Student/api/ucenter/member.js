import request from '@/utils/request'

const API_PATH = 'user/ucenter/member'

export default {
    // 修改用户信息
    updateUserInfo(userInfo) {
        return request({
            url: `${API_PATH}/updateUserInfo`,
            method: 'post',
            data: userInfo
        })
    },
    // 修改用户密码
    updatePassword(passwordVO) {
        return request({
            url: `${API_PATH}/updatePassword`,
            method: 'post',
            data: passwordVO
        })
    }
}