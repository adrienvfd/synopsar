package synopsarapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import synopsarapi.converter.SummaryConverter;
import synopsarapi.dto.SummaryDto;
import synopsarapi.dto.SummaryDtoLite;
import synopsarapi.enricher.SummaryEnricher;
import synopsarapi.entity.Summary;
import synopsarapi.service.SummaryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/summary")
public class SummaryController {

    private final SummaryService summaryService;

    @GetMapping
    public String getSummaryFromUrl(@RequestParam String url) {

        return summaryService.getSummaryFromUrl(url);
    }

    @PostMapping
    public SummaryDto saveSummary(@RequestBody SummaryDtoLite summaryDtoLite) {
        System.out.println("Input Summary: " + summaryDtoLite);
        Summary summary = SummaryConverter.convertDtoLiteToEntity(summaryDtoLite);
        summary = SummaryEnricher.enrich(summary);
        System.out.println("Enriched Summary: " + summary.toString());
        summary = summaryService.saveSummary(summary);
        System.out.println("Saved Summary: " + summary);
        return SummaryConverter.convertEntityToDto(summary);
    }

    @GetMapping("/history")
    public List<SummaryDto> getCurrentUserHistory() {
        List<Summary> summaries = summaryService.getCurrentUserHistory();
        summaries.forEach(s -> System.out.println(s.getDate().toString()));

        return summaries.stream()
                .map(SummaryConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteSummaryForCurrentUser(@PathVariable Long id) {
        // Secured in the service by Auth token
        summaryService.deleteSummaryById(id);
    }

    @DeleteMapping()
    public void deleteAllSummariesForCurrentUser() {
        // Secured in the service by Auth Token
        summaryService.deleteAllSummaries();
    }
}


