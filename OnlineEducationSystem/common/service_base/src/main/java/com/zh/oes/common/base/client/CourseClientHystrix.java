package com.zh.oes.common.base.client;

import com.zh.oes.common.base.exception.OESException;
import com.zh.oes.model.vo.edu.user.CourseUserInfoVO;
import org.springframework.stereotype.Component;

@Component
public class CourseClientHystrix implements CourseClient{
    @Override
    public CourseUserInfoVO remoteGetCourseInfo(Long courseId) {
        throw new OESException("Course服务器宕机");
    }
}
