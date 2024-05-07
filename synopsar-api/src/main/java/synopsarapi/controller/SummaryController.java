package synopsarapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import synopsarapi.service.SummaryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/summary")
public class SummaryController {

    private final SummaryService summaryService;

    @GetMapping("/")
    public String getSummaryFromUrl(@RequestParam String url) {

        return summaryService.getSummaryFromUrl(url);
    }


//    @PostMapping("/save")
//    public void saveSummary(
//            @RequestParam String url,
//            @RequestParam String summary,
//            @RequestHeader("Authorization") String token) {
//
//        summaryService.saveSummary(url, summary, token);
//    }

}


