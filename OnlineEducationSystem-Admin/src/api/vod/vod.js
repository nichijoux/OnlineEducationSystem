import request from '@/utils/request'

const API_PATH = 'admin/vod'

export default {
    // 根据视频id删除阿里云端视频
    deleteAliVideo(videoSourceId) {
        return request({
            url: `${API_PATH}/deleteAliVideo/${videoSourceId}`,
            method: 'DELETE'
        })
    }
}