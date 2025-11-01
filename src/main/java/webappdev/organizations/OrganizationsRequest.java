package webappdev.organizations;

public class OrganizationsRequest {

    private String name;
    private String url;
    private String image;
    private String slug;
    private Long userId;

    public OrganizationsRequest() {}

    public OrganizationsRequest(Long userId) {
        this.userId = userId;
    }

    public OrganizationsRequest(String name, String url, String image, String slug, Long userId) {
        this.name = name;
        this.url = url;
        this.image = image;
        this.slug = slug;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    public byte[] getImage() {
//        return image;
//    }
//
//    public void setImage(byte[] image) {
//        this.image = image;
//    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
