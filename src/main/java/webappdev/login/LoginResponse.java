package webappdev.login;

public class LoginResponse {
    private String data;
    private String username;
    private Long id;

    public String getData() {
        return data;
    }

    public void setData(String d) {
        data = d;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
