package com.lch.dao;

import com.lch.domain.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface GradeDao {
    @Select("insert into tb_grade values (#{id_grade},#{id_user},#{id_que},0)")
    void insertNewItem(Integer id_grade,String id_user,Integer id_que);

    @Select("select count(id_grade) from tb_grade where id_user = #{id_user} and id_que = #{id_que}")
    Integer isExistQue(String id_user,Integer id_que);

    @Select("select count(id_grade) from tb_grade")
    Integer count();

    @Select("insert into tb_grade values (#{id_grade},#{id_user},#{id_que},0)")
    void insertNewUser(Integer id_grade,String id_user,Integer id_que);

    @Select("select count(id_user) from tb_grade where id_user = #{id_user}")
    Integer isExistUser(String id_user);

    @Select("update tb_grade set statue = 2 where id_user = #{id_user} and id_que = #{id_que}")
    Grade update2(String id_user,Integer id_que);

    @Select("update tb_grade set statue = 3 where id_user = #{id_user} and id_que = #{id_que}")
    Grade update3(String id_user,Integer id_que);

    @Select("select distinct id_user from tb_grade")
    ArrayList<String> selectAlluser();

    @Select("select statue from tb_grade where id_user = #{id_user}")
    ArrayList<Integer> selectAllscore(String id_user);
}
