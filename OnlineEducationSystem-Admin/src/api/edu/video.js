import request from '@/utils/request'

const API_PATH = 'admin/edu/video'

export default {
    // 根据小节id获取小节信息
    getVideo(videoId) {
        return request({
            url: `${API_PATH}/getVideo/${videoId}`,
            method: 'GET'
        })
    },
    // 添加小节
    addVideo(video) {
        return request({
            url: `${API_PATH}/addVideo`,
            method: 'POST',
            data: video
        })
    },
    // 修改小节
    updateVideo(video) {
        return request({
            url: `${API_PATH}/updateVideo`,
            method: 'POST',
            data: video
        })
    },
    // 根据id删除小节视频,底层会调用vod删除阿里云端的视频
    deleteVideo(videoId) {
        return request({
            url: `${API_PATH}/deleteVideo/${videoId}`,
            method: 'DELETE',
        })
    }
}