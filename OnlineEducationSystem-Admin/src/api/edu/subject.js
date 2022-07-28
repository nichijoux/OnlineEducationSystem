import request from '@/utils/request'

const API_PATH = 'admin/edu/subject'

export default {
    // 获取所有学科
    getAllSubject() {
        return request({
            url: `${API_PATH}/getAllSubject`,
            method: "GET"
        })
    },
    // 获取学科pid为{pid}的科目及其子节点
    getChildSubject(pid) {
        return request({
            url: `${API_PATH}/getChildSubjec/${pid}`,
            method: 'GET'
        })
    },
    // 为subjectId下添加子节点
    addChildSubject(subjectId, subject) {
        return request({
            url: `${API_PATH}/addChildSubject/${subjectId}`,
            method: 'POST',
            data: subject
        })
    },
    // 将id为{subjectId}的科目更新
    updateSubject(subject) {
        return request({
            url: `${API_PATH}/updateSubject`,
            method: 'POST',
            data: subject
        })
    },
    // 根据学科id删除科目分类及其子节点
    deleteSubject(subjectId) {
        return request({
            url: `${API_PATH}/deleteSubject/${subjectId}`,
            method: 'DELETE'
        })
    }
}