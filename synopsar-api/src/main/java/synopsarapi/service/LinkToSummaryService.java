package synopsarapi.service;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LinkToSummaryService {

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

}
