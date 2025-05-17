package com.bluntpin.blunterest.Controller;

import com.bluntpin.blunterest.DTO.PinDto;
import com.bluntpin.blunterest.Model.Pin;
import com.bluntpin.blunterest.Service.PinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class PinController {

    private final PinService pinService;

    @GetMapping("/pins")
    public List<Pin> getAllPins() {
        return pinService.getAllPins();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPin(
            @RequestPart("file") MultipartFile file,
            @RequestPart("pin") PinDto pinDto) {

        pinService.uploadPin(file, pinDto);
        return ResponseEntity.ok("Pin uploaded successfully");
    }
}
