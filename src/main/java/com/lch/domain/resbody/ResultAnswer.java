package com.lch.domain.resbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultAnswer {
    private String userId;
    private List<Map<String,Object>> results;
}
