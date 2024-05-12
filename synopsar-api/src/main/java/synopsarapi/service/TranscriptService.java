package synopsarapi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TranscriptService {

    public String getTranscriptFromUrl(String youtubeUrl) {
        String videoId = getVideoIdFromUrl(youtubeUrl);

        System.out.println("Video ID: " + videoId);

        // Call local python script
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("http://transcriptor:5000/transcript/%s", videoId);

        System.out.println("Calling " + url);
        String response = restTemplate.getForObject(url, String.class, videoId);
        System.out.println("Transcript has been retrieved from " + url);
        return response;
    }

    private static String getVideoIdFromUrl(String url) {
        String pattern = "(?<=v=)[^&]+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(url);
        if (m.find()) {
            return m.group();
        }
        return null;
    }
}
