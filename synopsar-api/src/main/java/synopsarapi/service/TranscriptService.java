package synopsarapi.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TranscriptService {

    public String getTranscriptFromUrl(String youtubeUrl) {
        String videoId = getVideoIdFromUrl(youtubeUrl);
        
        System.out.println("Video ID: " + videoId);
        
        // Call local python script
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("http://localhost:5000/transcript/%s", videoId);
        System.out.println("Calling " + url);
        String response = restTemplate.getForObject(url, String.class, videoId);
        System.out.println("Response: " + response);
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

//import org.springframework.web.client.RestTemplate;

// public class TranscriptClient {
//     public static void main(String[] args) {
//         RestTemplate restTemplate = new RestTemplate();
//         String url = "http://localhost:5000/transcript/{video_id}";
//         String videoId = "your_video_id";
//         String response = restTemplate.getForObject(url, String.class, videoId);
//         System.out.println(response);
//     }
// }
