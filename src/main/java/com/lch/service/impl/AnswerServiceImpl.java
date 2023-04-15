package com.lch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lch.dao.AnswerDao;
import com.lch.domain.Answer;
import com.lch.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerDao, Answer> implements AnswerService {

    @Autowired
    private AnswerDao answerDao;

    @Override
    public void saveAnswer(Integer id_que,String right_answer) {
        answerDao.insertNew(id_que,right_answer);
    }
}
