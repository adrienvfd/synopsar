package synopsarapi.converter;

import synopsarapi.dto.SummaryDto;
import synopsarapi.dto.SummaryDtoLite;
import synopsarapi.entity.Summary;

public class SummaryConverter {

    public static Summary convertDtoLiteToEntity(SummaryDtoLite summaryDtoLite) {
        return Summary.builder()
                .id(summaryDtoLite.getId())
                .url(summaryDtoLite.getUrl())
                .text(summaryDtoLite.getText())
                .build();
    }

    public static SummaryDto convertEntityToDto(Summary summary) {
        return SummaryDto.builder()
                .id(summary.getId())
                .userId(summary.getUserId())
                .url(summary.getUrl())
                .title(summary.getTitle())
                .text(summary.getText())
                .date(summary.getDate())
                .build();
    }
}
