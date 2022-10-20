package com.zh.oes.common.base.client;

import com.zh.oes.model.vo.edu.user.CourseUserInfoVO;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-edu",fallback = EduClientHystrix.class)
public interface EduClient {
    @GetMapping("user/edu/course/remoteGetCourseInfo/{courseId}")
    CourseUserInfoVO remoteGetCourseInfo(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable("courseId") Long courseId);

    @GetMapping("admin/edu/course/remoteCountCourse")
    Long remoteCountCourse();

    @GetMapping("admin/edu/comment/remoteCountComment")
    Long remoteCountComment();
}
