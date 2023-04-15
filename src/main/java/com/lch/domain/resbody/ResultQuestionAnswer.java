package com.lch.domain.resbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultQuestionAnswer {
    private String question;
    private Integer id;
    private Map<String,String> answer;
    private String tag_type;
    private Integer code;
    private String message;
}
