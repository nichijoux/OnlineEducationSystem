import request from '@/utils/request'

const API_PATH = 'user/edu/index'

export default {
    //查询热门课程和名师
    getHotTeacherAndHotCourse() {
        return request({
            url: `${API_PATH}/getHotTeacherAndHotCourse`,
            method: 'GET'
        })
    }
}