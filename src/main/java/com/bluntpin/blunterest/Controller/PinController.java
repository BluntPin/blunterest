package com.bluntpin.blunterest.Controller;

import com.bluntpin.blunterest.Model.Pin;
import com.bluntpin.blunterest.Service.PinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class PinController {

    private final PinService pinService;

    @GetMapping("/pins")
    public ResponseEntity<?> getAllPins() {
        return new ResponseEntity<>(pinService.getAllPins(), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadPin(@RequestParam("file") MultipartFile file, Pin pin) throws IOException {
//        validateImage(file);
        try {
            pinService.uploadPin(file, pin);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
