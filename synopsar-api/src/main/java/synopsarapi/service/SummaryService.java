package synopsarapi.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import synopsarapi.entity.AppUser;
import synopsarapi.entity.Summary;
import synopsarapi.repository.SummaryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SummaryService {

    private final SummaryRepository summaryRepository;
    private final TranscriptService transcriptService;
    private final LLMService lLmService;

    public String getSummaryFromUrl(String url) {

        System.out.println("Getting the transcript from:" + url);
        String transcript = transcriptService.getTranscriptFromUrl(url);

        System.out.println("Parsing the transcript");
        JsonArray jsonArray = JsonParser.parseString(transcript).getAsJsonArray();

        System.out.println("Converting the transcript into a string");
        StringBuilder text = new StringBuilder();
        text.append("\"");
        jsonArray.forEach(jsonElement -> {
            String textExtract = jsonElement.getAsJsonObject().get("text").getAsString();
            textExtract = cleanString(textExtract);
            text.append(textExtract);
            text.append(" ");
        });
        text.append("\"");
        System.out.println("Sending the transcript to llm to make summary");
        return lLmService.getSummaryFromText(text.toString());
    }

    private String cleanString(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isLetterOrDigit(c) || " ;:!?,.-'".contains(String.valueOf(c))) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public Summary saveSummary(Summary summary) {
        return summaryRepository.save(summary);
    }

    public List<Summary> getCurrentUserHistory() {
        AppUser appUser = AppUser.getCurrentAppUser();
        System.out.println("APP USER: " + appUser.toString());

        return summaryRepository.findAllByUserId(appUser.getId());
    }

    @Transactional
    public void deleteSummaryById(Long id) {
        AppUser appUser = AppUser.getCurrentAppUser();
        System.out.println("About to delete summary w id " + id + " for user " + appUser.getId());
        Optional<Summary> summaryOptional = summaryRepository.findByIdAndUserId(id, appUser.getId());
        summaryOptional.ifPresent(summaryRepository::delete);
    }

    @Transactional
    public void deleteAllSummaries() {
        AppUser appUser = AppUser.getCurrentAppUser();
        List<Summary> summaries = summaryRepository.findAllByUserId(appUser.getId());
        summaryRepository.deleteAll(summaries);
    }
}
