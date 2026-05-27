package com.bluntpin.blunterest.Controller;

import com.bluntpin.blunterest.DTO.PinDto;
import com.bluntpin.blunterest.Model.Pin;
import com.bluntpin.blunterest.Service.PinService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class PinController {

    private final PinService pinService;

    @GetMapping("/pins")
    public PagedModel<Pin> getAllPins(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Pin> pins = pinService.getAllPins(pageable);
        return new PagedModel<>(pins);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPin(
            @RequestPart("file") MultipartFile file,
            @RequestPart("pin") PinDto pinDto) {

        pinService.uploadPin(file, pinDto);
        return ResponseEntity.ok("Pin uploaded successfully");
    }
}
