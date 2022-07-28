import request from '@/utils/request'

const API_PATH = 'admin/edu/chapter'

export default {
    // 根据课程id获取所有课程章节和小节
    getAllChapterAndVideo(courseId) {
        return request({
            url: `${API_PATH}/getAllChapterAndVideo/${courseId}`,
            method: 'GET'
        })
    },
    // 根据章节id获取章节信息
    getChapter(chapterId) {
        return request({
            url: `${API_PATH}/getChapter/${chapterId}`,
            method: 'GET'
        })
    },
    // 添加章节
    addChapter(chapter) {
        return request({
            url: `${API_PATH}/addChapter`,
            method: 'POST',
            data: chapter
        })
    },
    // 更新章节
    updateChapter(chapter) {
        return request({
            url: `${API_PATH}/updateChapter`,
            method: 'POST',
            data: chapter
        })
    },
    // 删除章节
    deleteChapter(chapterId) {
        return request({
            url: `${API_PATH}/deleteChapter/${chapterId}`,
            method: 'DELETE'
        })
    }
}