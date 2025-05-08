package com.bluntpin.blunterest.Controller;

import com.bluntpin.blunterest.Model.Pin;
import com.bluntpin.blunterest.Repository.PinRepository;
import com.bluntpin.blunterest.Service.PinService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.http.HttpResponse;
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

    @PostMapping("/pins")
    public HttpResponse<?> uploadPin(MultipartFile file) {
//        validateImage(file);
        pinService.uploadPin(file);
        return null;
    }
}
