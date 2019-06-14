package com.sun.mapper;

import com.sun.entity.Questionnaire;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionnaireMapper {
    List<Questionnaire> selectAll();

    Integer insertOne(@Param("questionnaire") Questionnaire questionnaire);

    Integer updateQuestionNumById(@Param("id") Integer id);

    Integer updateAnswerNumById(@Param("id") Integer id);

    Integer updateQuestionNumByQuestionNumAndId(@Param("questionNum") Integer questionNum, @Param("id") Integer id);

    Integer selectNumByAccountId(@Param("accountId") Integer accountId);

    Integer selectNum();

    List<Questionnaire> selectByAccountId(@Param("accountId") Integer accountId);

    Questionnaire selectById(@Param("id") Integer id);

    Integer updateBandedById(@Param("id") Integer id, @Param("isBanded") Boolean isBanded);
}
