package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "rating")
@Data // Génère getters, setters, toString, equals, hashCode
@NoArgsConstructor // Constructeur vide pour JPA
@AllArgsConstructor // Constructeur avec tous les champs
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // clé primaire
    private String moodysRating;
    private String sandPRating;
    private String fitchRating;
    private Integer orderNumber;

    public Rating(String moodysRating, String sandPRating, String fitchRating, int i) {
    }


}