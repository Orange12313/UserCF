package com.lch.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {
    @TableId("id_grade")
    private Integer idGrade;
    private String idUser;
    private Integer idQue;
    private String statue;
}
