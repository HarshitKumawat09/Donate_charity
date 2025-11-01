package webappdev.preferences;

import java.util.ArrayList;
import java.util.List;

public class PreferencesResponse {
    private List<String> data;

    public PreferencesResponse(List<Preferences> causes) {
        data = new ArrayList<String>();
        for (Preferences p : causes) {
            if (p.getCause() != null) {
                data.add(p.getCause());
            }
        }
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
