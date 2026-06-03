package com.bluntpin.blunterest.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PinDetailDto {
    private long id;
    private String title;
    private String description;
    private String imageUrl;
    private int likesAmount;
//    private PinAuthor pinAuthor;
//    private List<String> comments;
}
