package com.bluntpin.blunterest.Controller;

import com.bluntpin.blunterest.DTO.CreatePinDto;
import com.bluntpin.blunterest.DTO.PinDetailDto;
import com.bluntpin.blunterest.DTO.PinDto;
import com.bluntpin.blunterest.Service.PinService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://127.0.0.1:5173")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class PinController {

    private final PinService pinService;

    @GetMapping("/pins")
    public PagedModel<PinDto> getAllPins(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PinDto> pins = pinService.getAllPins(pageable);
        return new PagedModel<>(pins);
    }

    @GetMapping("/pin/{id}")
    public PinDetailDto getPinById(@PathVariable int id) {
        return pinService.getPinById(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPin(
            @NotNull(message = "There is no file uploaded")
            @RequestPart("file") MultipartFile file,
            @Valid @NotNull(message = "Pin data cannot be null")
            @RequestPart("pin") CreatePinDto createPinDto) {

        validateImage(file);
        pinService.uploadPin(file, createPinDto);
        return ResponseEntity.ok("Pin uploaded successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePin(@PathVariable int id) {
        pinService.deletePin(id);
        return ResponseEntity.ok("Pin deleted successfully");
    }

    private void validateImage(MultipartFile file) {
        long maxMbAllowed = 30;

        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        long fileSize = file.getSize();

        if (fileSize > maxMbAllowed * 1024 * 1024) {
            throw new IllegalArgumentException("File is too large");
        }

        if (!file.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("File is not an image");
        }

    }
}
