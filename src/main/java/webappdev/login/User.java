package webappdev.login;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    private String status;
    private String password;
    private String email;

    public User() {}

    public User (String un, String pass) {
        this.username=un;
        this.password=pass;
        this.email="test";
        this.status="";
    }

    public User (String un, String pass, String status, String e) {
        this.username=un;
        this.password=pass;
        this.email=e;
        this.status="";
    }

    public User(String username, String password, String email) {
        this.username=username;
        this.password=password;
        this.email=email;
        this.status="";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername () {
        return username;
    }
    public void setUsername (String name) {
        this.username=name;
    }

    public String getPassword () {
        return password;
    }
    public void setPassword (String pass) {
        this.password=pass;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail (String e) {
        this.email=e;
    }

    public String toString() {
        return "User{" +
                "id=" + id +
                "username=" + username +
                ", password=" + password +
                ", email=" + email +
                "}";
    }

}