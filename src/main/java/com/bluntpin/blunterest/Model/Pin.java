package com.bluntpin.blunterest.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Pin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String imageUrl;
    private String imageKey;
    private LocalDateTime createdDate;
    @Column(nullable = false, columnDefinition = "integer default 0")
    private int likesAmount = 0;

//    private List<String> tags;
//    private User pinAuthor;
//    private List<String> comments;
//    private boolean isPublic;
}

