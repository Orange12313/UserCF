package com.lch.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lch.domain.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionDao extends BaseMapper<Question>  {
}
