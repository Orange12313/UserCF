package com.lch.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lch.domain.Answer;

public interface AnswerService extends IService<Answer> {
    public void saveAnswer(Integer id_que,String right_answer);
}
