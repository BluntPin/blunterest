package com.bluntpin.blunterest.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Pin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String imageUrl;
//    TO-DO:
//    private List<String> tags;
//    private LocalDateTime createdAt;
//    private User user;
//    private boolean isPublic;
}

