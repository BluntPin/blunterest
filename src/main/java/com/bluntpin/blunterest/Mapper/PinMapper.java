package com.bluntpin.blunterest.Mapper;

import com.bluntpin.blunterest.DTO.PinDetailDto;
import com.bluntpin.blunterest.DTO.PinDto;
import com.bluntpin.blunterest.Model.Pin;
import org.springframework.stereotype.Component;

@Component
public class PinMapper {
    public PinDto toDto(Pin pin) {
        PinDto pinDto = new PinDto();

        pinDto.setId(pin.getId());
        pinDto.setImageUrl(pin.getImageUrl());
        pinDto.setTitle(pin.getTitle());

        return pinDto;
    }

    public PinDetailDto toDetailDto(Pin pin) {
        PinDetailDto pinDetailDto = new PinDetailDto();

        pinDetailDto.setId(pin.getId());
        pinDetailDto.setImageUrl(pin.getImageUrl());
        pinDetailDto.setTitle(pin.getTitle());
        pinDetailDto.setDescription(pin.getDescription());
        pinDetailDto.setLikesAmount(pin.getLikesAmount());

        return pinDetailDto;
    }
}
