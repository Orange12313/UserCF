package com.lch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lch.dao.QuestionDao;
import com.lch.domain.Question;
import com.lch.service.AnswerService;
import com.lch.service.QuestionBackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionBackendServiceImpl  extends ServiceImpl<QuestionDao, Question> implements QuestionBackendService {
    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private AnswerService answerService;

    //新增
    @Override
    public boolean save(Question question) {
        return questionDao.insert(question) > 0;
    }

    //删除
    @Override
    public Boolean delete(Integer id) {
        return questionDao.deleteById(id) > 0;
    }

    //修改
    @Override
    public Boolean update(Question question) {
        return questionDao.updateById(question) > 0;
    }

    //查询
    @Override
    public Question getById(Integer id) {
        return questionDao.selectById(id);
    }

    @Override
    public List<Question> getAll() {
        return questionDao.selectList(null);
    }
}
