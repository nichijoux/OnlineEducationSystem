package com.zh.oes.common.base.client;

import com.zh.oes.common.base.exception.OESException;
import com.zh.oes.model.vo.edu.user.CourseUserInfoVO;
import org.springframework.stereotype.Component;

@Component
public class EduClientHystrix implements EduClient {
    @Override
    public CourseUserInfoVO remoteGetCourseInfo(Long courseId) {
        throw new OESException("Course服务器宕机");
    }

    @Override
    public Long remoteCountCourse() {
        return 0L;
    }

    @Override
    public Long remoteCountComment() {
        return 0L;
    }
}
