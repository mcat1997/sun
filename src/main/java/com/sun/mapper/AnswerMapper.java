package com.sun.mapper;

import com.sun.entity.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnswerMapper {

    List<Answer> selectAll();

    Integer insertPatch(@Param("answers") List<Answer> answers);

    List<Answer> selectByQuestionIds(@Param("questionIds") List<Integer> questionIds);

    Integer selectNumByQuestionIds(@Param("questionIds") List<Integer> questionIds);
}
