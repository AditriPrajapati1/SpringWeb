package io.herald.SpringWeb.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ImageTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
}
