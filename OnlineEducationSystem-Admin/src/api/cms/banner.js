import request from '@/utils/request'

const API_PATH = 'admin/cms/banner'

export default {
    // 分页查询banner信息
    pageQueryBanner(index, limit, queryCondition) {
        return request({
            url: `${API_PATH}/pageQueryBanner/${index}/${limit}`,
            method: 'POST',
            data: queryCondition
        })
    },
    // 根据id获取banner信息
    getBanner(bannerId) {
        return request({
            url: `${API_PATH}/getBanner/${bannerId}`,
            method: 'GET'
        })
    },
    // 添加banner信息
    addBanner(banner) {
        return request({
            url: `${API_PATH}/addBanner`,
            method: 'POST',
            data: banner
        })
    },
    // 更新banner信息
    updateBanner(banner) {
        return request({
            url: `${API_PATH}/updateBanner`,
            method: 'POST',
            data: banner
        })
    },
    // 禁用或者启用某个banner
    enableOrDisableBanner(bannerId, isEnable) {
        return request({
            url: `${API_PATH}/enableOrDisableBanner/${bannerId}`,
            method: 'POST',
            params: { "isEnable": isEnable }
        })
    },
    // 根据banner id删除banner数据
    deleteBanner(bannerId) {
        return request({
            url: `${API_PATH}/deleteBanner/${bannerId}`,
            method: 'DELETE'
        })
    }
}