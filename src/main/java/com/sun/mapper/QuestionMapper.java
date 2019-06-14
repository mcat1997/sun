package com.sun.mapper;

import com.sun.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {
    List<Question> selectAll();

    List<Question> selectByQuestionnaireId(@Param("questionnaireId") Integer questionnaireId);

    Integer selectMaxSerialByQuestionnaireId(@Param("questionnaireId") Integer questionnaireId);

    Integer insertOne(@Param("question") Question question);

    Integer insertList(@Param("questions") List<Question> questions);
}
