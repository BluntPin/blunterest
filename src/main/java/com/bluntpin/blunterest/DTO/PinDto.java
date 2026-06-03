package com.bluntpin.blunterest.DTO;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PinDto {
    @Id
    private long id;
    private String Title;
    @NotNull
    private String imageUrl;
}
