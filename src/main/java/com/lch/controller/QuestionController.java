package com.lch.controller;

import com.lch.dao.GradeDao;
import com.lch.domain.Answer;
import com.lch.domain.Question;
import com.lch.domain.resbody.ResultQuestionAnswer;
import com.lch.service.AnswerService;
import com.lch.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private GradeDao gradeDao;

    //查询全部
    @GetMapping("/all")
    public ResultQuestionAnswer[] getAll() {
        Question question;
        Answer rightAnswer;
        ResultQuestionAnswer[] resultQuestionAnswer = new ResultQuestionAnswer[90];

        for (int i = 0; i < 90; i++) {
            resultQuestionAnswer[i] = new ResultQuestionAnswer();
        }

        for (int i = 0; i < 90; i++) {
            question = questionService.getById(i+1);
            rightAnswer = answerService.getById(i+1);
            if (question == null || rightAnswer == null) {
                resultQuestionAnswer[i].setCode(400);
                resultQuestionAnswer[i].setMessage("error");
                return resultQuestionAnswer;
            }

            resultQuestionAnswer[i].setQuestion(question.getQuestion());
            resultQuestionAnswer[i].setId(i+1);
            resultQuestionAnswer[i].setTag_type(question.getTagType());

            Map<String,String> answerMap = new HashMap<>();
            answerMap.put("A",question.getChoiceA());
            answerMap.put("B",question.getChoiceB());
            answerMap.put("C",question.getChoiceC());
            answerMap.put("D",question.getChoiceD());

            resultQuestionAnswer[i].setAnswer(answerMap);
        }
        
        return resultQuestionAnswer;
    }


    //条件查询
    @GetMapping("/userCF")
    public ResultQuestionAnswer[] getByIdRe(@RequestParam("userId") String userId){
        ArrayList<String> userList;
        userList = gradeDao.selectAlluser();

        Integer lenUser = userList.size();
        Integer lenScore = questionService.count();

        ArrayList<Integer> scoreList;

        Integer[][] CoM = new Integer[userList.size()][lenScore];

        for (int i = 0; i < lenUser; i++) {
            String idUser = userList.get(i);
            scoreList = gradeDao.selectAllscore(idUser);
            for (int j = 0; j < lenScore; j++) {
                CoM[i][j] = scoreList.get(j);
            }
        }




        Integer id = 1;

        Question question;
        Answer rightAnswer;
        ResultQuestionAnswer[] resultQuestionAnswer = new ResultQuestionAnswer[3];

        for (int i = 0; i < 3; i++) {
            resultQuestionAnswer[i] = new ResultQuestionAnswer();
        }

        for (int i = 0; i < 3; i++) {
            question = questionService.getById(i+id);
            rightAnswer = answerService.getById(i+id);
            if (question == null || rightAnswer == null || id == null) {
                resultQuestionAnswer[i].setCode(400);
                resultQuestionAnswer[i].setMessage("error");
                return resultQuestionAnswer;
            }

            resultQuestionAnswer[i].setQuestion(question.getQuestion());
            resultQuestionAnswer[i].setId(id+i);
            resultQuestionAnswer[i].setTag_type(question.getTagType());

            Map<String,String> answerMap = new HashMap<>();
            answerMap.put("A",question.getChoiceA());
            answerMap.put("B",question.getChoiceB());
            answerMap.put("C",question.getChoiceC());
            answerMap.put("D",question.getChoiceD());

            resultQuestionAnswer[i].setAnswer(answerMap);
        }

        return resultQuestionAnswer;
    }

}
