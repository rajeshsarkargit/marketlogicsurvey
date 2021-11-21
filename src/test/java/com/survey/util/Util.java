package com.survey.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.survey.model.QuestionAnswers;
import com.survey.model.SelectedQuestionAnswer;

import java.util.List;

public class Util {
    public static String asJsonString(final QuestionAnswers obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static QuestionAnswers stringToObj(final String str) {
        try {
            return new ObjectMapper().readValue(str, QuestionAnswers.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static List<QuestionAnswers> stringToObjList(final String str) {
        try {
            return new ObjectMapper().readValue(str, new TypeReference<List<QuestionAnswers>>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String asJsonStrFrmList(final List<SelectedQuestionAnswer> obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static List<SelectedQuestionAnswer> convert_SQA_Str_To_Obj(final String str) {
        try {
            return new ObjectMapper().readValue(str, new TypeReference<List<SelectedQuestionAnswer>>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
