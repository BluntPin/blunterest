package com.bluntpin.blunterest.Service;

import com.bluntpin.blunterest.DTO.PinDto;
import com.bluntpin.blunterest.Model.Pin;
import com.bluntpin.blunterest.Repository.PinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PinService {


    private final PinRepository pinRepository;
    private final StorageService storageService;


    public Page<Pin> getAllPins(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return pinRepository.findAllById(pageable);
    }

    public void uploadPin(MultipartFile multipartFile, PinDto pinDto) {
        String imageUrl = null;
        try {
            imageUrl = storageService.uploadFile(multipartFile);
            pinDto.setImageUrl(imageUrl);

            Pin pin = new Pin();
            pin.setTitle(pinDto.getTitle());
            pin.setDescription(pinDto.getDescription());
            pin.setImageUrl(imageUrl);
            pinRepository.save(pin);
        } catch (Exception e) {
            if (imageUrl != null) {
                storageService.deleteFileByUrl(imageUrl);
            }
            throw e;
        }

    }
}
