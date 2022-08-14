import request from '@/utils/request'

const API_PATH = 'admin/edu/studyRecord'

export default {
    // 分页查询学习记录
    pageQueryStudyRecord(index, limit, queryCondition) {
        return request({
            url: `${API_PATH}/pageQueryStudyRecord/${index}/${limit}`,
            method: 'POST',
            data: queryCondition
        })
    }
}