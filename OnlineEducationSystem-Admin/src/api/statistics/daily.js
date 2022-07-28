import request from "@/utils/request"

const API_PATH = "admin/statistics/daily"

export default {
    // 统计注册人数
    registerMemberCount(day) {
        return request({
            url: `${API_PATH}/registerMemberCount/${day}`,
            method: 'POST'
        })
    },
    // 获取统计数据的方法
    getStatisticsData(type, begin, end) {
        return request({
            url: `${API_PATH}/getStatisticsData/${type}/${begin}/${end}`,
            method: 'GET'
        })
    }
}