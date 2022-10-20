import request from "@/utils/request"

const API_PATH = "admin/statistics/dashboard"

export default {
    // 首页面板统计
    getStatisticsData() {
        return request({
            url: `${API_PATH}/getStatisticsData`,
            method: 'GET'
        })
    }
}