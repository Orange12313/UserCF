package com.lch.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lch.domain.Question;

import java.util.List;

public interface QuestionBackendService extends IService<Question> {
    boolean save (Question question);

    Boolean delete(Integer id);

    Boolean update(Question question);

    Question getById(Integer id);

    List<Question> getAll();
}
