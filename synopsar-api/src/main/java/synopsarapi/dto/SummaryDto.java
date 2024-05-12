package synopsarapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class SummaryDto {
    Long id;
    String userId;
    String url;
    String title;
    String text;
    Instant date;
}
