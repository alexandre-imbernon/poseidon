package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "curvepoint")
@Data // Génère getters, setters, toString, equals, hashCode
@NoArgsConstructor // Constructeur vide pour JPA
@AllArgsConstructor // Constructeur avec tous les champs
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // clé primaire

    private Integer curveId;
    private Timestamp asOfDate;
    private Double term;
    private Double value;
    private Timestamp creationDate;

    public CurvePoint(int i, double v, double v1) {
    }
}