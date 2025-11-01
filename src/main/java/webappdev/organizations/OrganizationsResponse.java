package webappdev.organizations;

import java.util.List;

public class OrganizationsResponse {
    private List<Organizations> data;

    public OrganizationsResponse() {}

    public OrganizationsResponse(List<Organizations> data) {
        this.data = data;
    }

    public List<Organizations> getData() {
        return data;
    }

    public void setData(List<Organizations> data) {
        this.data = data;
    }
}
