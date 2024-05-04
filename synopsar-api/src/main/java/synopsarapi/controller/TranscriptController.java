package synopsarapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import synopsarapi.service.TranscriptService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transcript")
public class TranscriptController {

    private final TranscriptService transcriptService;

    //TODO Controller present for test purposes, should be deleted out in the future as we use directly the service
    @GetMapping("/")
    public String getTranscriptFromUrl(@RequestParam String url) {

        System.out.println("Get transcript from: " + url);
    
        return transcriptService.getTranscriptFromUrl(url);  
    }
}
