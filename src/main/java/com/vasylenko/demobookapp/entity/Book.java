package com.vasylenko.demobookapp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@ApiModel(value = "book class")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @ApiModelProperty(value = "title of book")
    private String title;

    @Column(nullable = false, unique = true)
    @ApiModelProperty(value = "international standard book number")
    private String isbn;

    @Column(nullable = false)
    @ApiModelProperty(value = "author of book")
    private String author;
}
