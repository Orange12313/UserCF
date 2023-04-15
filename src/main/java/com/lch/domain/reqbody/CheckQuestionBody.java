package com.lch.domain.reqbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckQuestionBody {
    private String userId;
    private List<Map<String, Object>> question;
}
