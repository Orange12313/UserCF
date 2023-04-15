package com.lch.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lch.domain.Answer;
import com.lch.domain.reqbody.QuestionReqBody;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnswerDao extends BaseMapper<Answer> {

    @Select("insert into tb_answer values (#{id_que},#{right_answer})")
    void insertNew(Integer id_que,String right_answer);

    @Select("delete from tb_answer where id_que=#{id_que}")
    void deleteItem(Integer id_que);

    @Select("update tb_answer set right_answer = #{right_answer} where id_que = #{id_que}")
    void updateItem(Integer id_que,String right_answer);

    @Select("select right_answer from tb_answer where id_que = #{id_que}")
    String getItem(Integer id_que);

    @Select("select * from tb_question LEFT JOIN tb_answer using(id_que)")
    List<QuestionReqBody> getAllItem();
}
