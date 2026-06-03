package com.bluntpin.blunterest.Service;

import com.bluntpin.blunterest.DTO.CreatePinDto;
import com.bluntpin.blunterest.DTO.PinDetailDto;
import com.bluntpin.blunterest.DTO.PinDto;
import com.bluntpin.blunterest.Mapper.PinMapper;
import com.bluntpin.blunterest.Model.Pin;
import com.bluntpin.blunterest.Repository.PinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PinService {


    private final PinRepository pinRepository;
    private final StorageService storageService;
    private final PinMapper pinMapper;


    public Page<PinDto> getAllPins(@PageableDefault(sort = {"createdDate"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return pinRepository.findAll(pageable)
                .map(pinMapper::toDto);
    }

    public PinDetailDto getPinById(int id) {
        Pin pin = pinRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pin not found"));
        return pinMapper.toDetailDto(pin);
    }

    @Transactional
    public void uploadPin(MultipartFile multipartFile, CreatePinDto createPinDto) {
        UploadResult uploadResult;
        String imageUrl;

        Pin pin = new Pin();
        uploadResult = storageService.uploadFile(multipartFile);
        imageUrl = uploadResult.imageUrl();

        pin.setImageKey(uploadResult.imageKey());
        pin.setImageUrl(imageUrl);
        pin.setTitle(createPinDto.getTitle());
        pin.setDescription(createPinDto.getDescription());
        pin.setImageUrl(imageUrl);
        pin.setCreatedDate(LocalDateTime.now());
        pinRepository.save(pin);
    }

    @Transactional
    public void deletePin(int id) {
        Pin pin = pinRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pin not found"));
        String imageKey = pin.getImageUrl();
        try {
            pinRepository.delete(pin);
            storageService.deleteFileByUrl(pin.getImageKey());
            pinRepository.deleteById(id);
            if (imageKey != null) {
                storageService.deleteFileByUrl(imageKey);
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Pin not found");
        }
    }
}
