package com.lch.controller;

import com.lch.dao.AnswerDao;
import com.lch.domain.Question;
import com.lch.domain.reqbody.QuestionReqBody;
import com.lch.domain.resbody.Result;
import com.lch.service.AnswerService;
import com.lch.service.QuestionBackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/crud")
public class CrudQuestionController {
    @Autowired
    private QuestionBackendService questionBackendService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private AnswerDao answerDao;

    //新增题目
    @PostMapping("/save")
    public Result save(@RequestBody QuestionReqBody questionReqBody) {
        Question question = new Question();
        question.setIdQue(questionReqBody.getIdQue());
        question.setQuestion(questionReqBody.getQuestion());
        question.setChoiceA(questionReqBody.getChoiceA());
        question.setChoiceB(questionReqBody.getChoiceB());
        question.setChoiceC(questionReqBody.getChoiceC());
        question.setChoiceD(questionReqBody.getChoiceD());
        question.setTagType(questionReqBody.getTagType());
        if (questionBackendService.save(question)) {
            answerDao.insertNew(questionReqBody.getIdQue(),questionReqBody.getRightAnswer());
            return new Result(true, "success");
        }
        return new Result(false, "新增失败，请修改题目序号");
    }

    //删除题目
    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") Integer id) {
        if (questionBackendService.delete(id)) {
            answerDao.deleteItem(id);
            return new Result(true, "success");
        }
        return new Result(false, "删除失败");
    }

    //修改题目
    @PutMapping("/update")
    public Result update(@RequestBody QuestionReqBody questionReqBody) {
        Question question = new Question();
        question.setIdQue(questionReqBody.getIdQue());
        question.setQuestion(questionReqBody.getQuestion());
        question.setChoiceA(questionReqBody.getChoiceA());
        question.setChoiceB(questionReqBody.getChoiceB());
        question.setChoiceC(questionReqBody.getChoiceC());
        question.setChoiceD(questionReqBody.getChoiceD());
        question.setTagType(questionReqBody.getTagType());
        String rightAnswer = questionReqBody.getRightAnswer();
        if (questionBackendService.update(question)) {
            answerDao.updateItem(questionReqBody.getIdQue(),rightAnswer);
            return new Result(true, "success");
        }
        return new Result(false, "插入失败，请修改题目序号");
    }

    //查询题目
    @GetMapping("/get")
    public Result getById(@RequestParam("id") Integer id) {
        QuestionReqBody questionReqBody = new QuestionReqBody();
        Question question = questionBackendService.getById(id);
        questionReqBody.setIdQue(question.getIdQue());
        questionReqBody.setQuestion(question.getQuestion());
        questionReqBody.setChoiceA(question.getChoiceA());
        questionReqBody.setChoiceB(question.getChoiceB());
        questionReqBody.setChoiceC(question.getChoiceC());
        questionReqBody.setChoiceD(question.getChoiceD());
        questionReqBody.setTagType(question.getTagType());
        questionReqBody.setRightAnswer(answerDao.getItem(question.getIdQue()));
        if (question.getQuestion() == null || question.getIdQue() == null) {
            return new Result(false, "查询失败");
        }
        return new Result(true, questionReqBody);
    }

    @GetMapping("/all")
    public Result getAll(){
        return new Result(true,answerDao.getAllItem());
    }
}
