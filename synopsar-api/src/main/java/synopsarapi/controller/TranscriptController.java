package synopsarapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import synopsarapi.service.TranscriptService;

@RestController
@RequiredArgsConstructor
public class TranscriptController {

    private final TranscriptService transcriptService;

    @GetMapping()
    public String getTranscriptFromUrl(@RequestParam String url) {

        System.out.println("Get transcript from: " + url);
        
        return transcriptService.getTranscriptFromUrl(url);  
    }
}
