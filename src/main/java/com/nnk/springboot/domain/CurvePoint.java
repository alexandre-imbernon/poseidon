package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "curvepoint")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "CurveId must not be null")
    private Integer curveId;

    private Timestamp asOfDate;

    @NotNull(message = "Term must not be null")
    private Double term;

    @NotNull(message = "Value must not be null")
    private Double value;

    private Timestamp creationDate;

    // Constructeur pratique
    public CurvePoint(Integer curveId, Double term, Double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }
}
