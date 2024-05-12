package synopsarapi.enricher;

import synopsarapi.entity.AppUser;
import synopsarapi.entity.Summary;

public class SummaryEnricher {

    public static Summary enrich(Summary summary) {

        if (summary == null) {
            return null;
        }

        if (summary.getText() == null) {
            summary.setText("");
        }

        if (summary.getUrl() == null) {
            summary.setUrl("");
        }

        if (summary.getTitle() == null) {
            String[] lines = summary.getText().split("\\r?\\n");
            if (lines.length > 0) {
                summary.setTitle(lines[0]);
            } else {
                summary.setTitle("");
            }
        }

        AppUser appUser = AppUser.getCurrentAppUser();
        summary.setUserId(appUser.getId());

        return summary;
    }
}
