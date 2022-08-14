package com.zh.oes.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.oes.model.entity.edu.StudyRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 学生学习记录 Mapper 接口
 * </p>
 *
 * @author nichijoux
 * @since 2022-08-14
 */
@Mapper
public interface StudyRecordMapper extends BaseMapper<StudyRecord> {

}
