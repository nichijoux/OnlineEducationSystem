import request from '@/utils/request'

const API_PATH = 'user/vod'

export default {
    // 获取视频播放凭证
    getPlayAuth(videoId) {
        return request({
            url: `${API_PATH}/getPlayAuth/${videoId}`,
            method: 'GET'
        })
    }
}