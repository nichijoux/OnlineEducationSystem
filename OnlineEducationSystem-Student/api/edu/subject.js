import request from '@/utils/request'

const API_PATH = 'user/edu/subject'

export default {
    // 查询所有分类,一级分类下面包含二级分类,二级还可以包含三级
    getAllSubject() {
        return request({
            url: `${API_PATH}/getAllSubject`,
            method: 'GET'
        })
    }
}