package webappdev.preferences;

import java.util.ArrayList;
import java.util.List;

public class PreferencesRequest {
    private List<String> causes;
    private Long userId;

    public PreferencesRequest() {}

    public PreferencesRequest(Long userId) {
        this.userId = userId;
        this.causes = new ArrayList<String>();
    }

    public PreferencesRequest(ArrayList<String> causes, Long userId) {
        this.userId = userId;
        this.causes = causes;
    }

    public Long getUserId() {
        return userId;
    }

    public List<String> getCauses() {
        return causes;
    }

    public void setCauses(List<String> causes) {
        this.causes = causes;
    }
}
