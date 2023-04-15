package com.lch.controller;

import com.lch.dao.GradeDao;
import com.lch.domain.Answer;
import com.lch.domain.reqbody.CheckQuestionBody;
import com.lch.domain.resbody.ResultAnswer;
import com.lch.service.AnswerService;
import com.lch.service.GradeService;
import com.lch.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class CheckController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private GradeDao gradeDao;

    @Autowired
    private QuestionService questionService;

    @PostMapping(value = "check")
    public ResultAnswer readPost(@RequestBody CheckQuestionBody json) {
//        System.out.println("json = " + json);
        int length = json.getQuestion().size();
        List<Map<String,Object>> results = new ArrayList<>(length);
        String id_user = json.getUserId();

        if (gradeDao.isExistUser(id_user) == 0){
            Integer len = gradeDao.count();
            for (int i = 0; i < questionService.count(); i++) {
                gradeDao.insertNewUser(len+i+1,id_user,i+1);
            }
        }

        for (int i = 0; i< length;i++) {
            Integer len = gradeDao.count();
            Integer id_que = (Integer) json.getQuestion().get(i).get("id");
            if (gradeDao.isExistQue(id_user,id_que) == 0){
                gradeDao.insertNewItem(len+1,id_user,id_que);
            }
        }

        for (int i = 0; i< length;i++) {
            Integer id = (Integer) json.getQuestion().get(i).get("id");
            String choose = (String) json.getQuestion().get(i).get("answer");
            Answer answer = answerService.getById(id);
            Map<String,Object> tmpAnswer = new HashMap<>();
            tmpAnswer.put("id",id);
            tmpAnswer.put("isCorrect", answer.getRightAnswer().equals(choose));
            results.add(tmpAnswer);
            if(answer.getRightAnswer().equals(choose)){
                gradeDao.update2(id_user,id);
            }else {
                gradeDao.update3(id_user,id);
            }

        }

        return new ResultAnswer(json.getUserId(),results);
    }
}
