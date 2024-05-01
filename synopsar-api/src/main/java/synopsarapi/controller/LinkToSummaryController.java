package synopsarapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import synopsarapi.service.LinkToSummaryService;

@RestController
@RequiredArgsConstructor
public class LinkToSummaryController {

    private final LinkToSummaryService linkToSummaryService;

    @GetMapping("/link-to-summary")
    public String getSummaryFromUrl(@RequestParam String url) {

        return linkToSummaryService.getSummaryFromUrl(url);
    }

}


