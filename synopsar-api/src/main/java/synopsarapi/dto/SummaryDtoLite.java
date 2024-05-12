package synopsarapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SummaryDtoLite {
    Long id;
    String url;
    String text;
}
