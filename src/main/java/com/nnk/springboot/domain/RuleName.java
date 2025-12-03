package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "rulename")
@Data // Génère getters, setters, toString, equals, hashCode
@NoArgsConstructor // Constructeur vide pour JPA
@AllArgsConstructor // Constructeur avec tous les champs

public class RuleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(columnDefinition = "TEXT")
    private String json;

    private String template;

    @Column(name = "sql_str") // si le nom de la colonne est SQL_STR en base
    private String sqlStr;

    @Column(name = "sql_part") // si le nom de la colonne est SQL_PART en base
    private String sqlPart;

    public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {

    }
}