import request from '@/utils/request'

const API_PATH = 'user/cms/banner'

export default {
    // 首页获取所有的banner信息
    getAllBanner() {
        return request({
            url: `${API_PATH}/getAllBanner`,
            method: 'GET'
        })
    }
}