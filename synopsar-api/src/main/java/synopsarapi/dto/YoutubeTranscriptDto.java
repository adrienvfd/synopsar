package synopsarapi.dto;

import java.util.ArrayList;

import lombok.Data;

@Data
public class YoutubeTranscriptDto {

    private ArrayList<YoutubeTranscriptTextDto> transcript;

}
