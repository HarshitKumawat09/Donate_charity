package webappdev.organizations;

import jakarta.persistence.*;
import webappdev.login.User;

@Entity
@Table(name = "organizations")
public class Organizations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    //@Lob
    @Column(name = "image")//, columnDefinition = "LONGBLOB")
    private String image;

    @Column(name = "slug")
    private String slug;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // Constructors, getters, and setters

    public Organizations() {
    }

    public Organizations(String name, String url, String image, String slug, Long userId) {
        this.name = name;
        this.url = url;
        this.image = image;
        this.slug = slug;
        System.out.println(userId + " HELLLOOOOOO");
        setUid(userId);
        System.out.println(user.getId() + " YEEEE");
    }

    public User getUser() { return this.user; }

    public void setUser(User u) { this.user = u; }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getUid() {
        return (user != null) ? user.getId() : -1;
    }

    public void setUid(Long uid) {
        System.out.println(uid);
        if (user == null) {
            user = new User();
        }
        user.setId(uid);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name=" + name +
                ", url=" + url +
                ", image=" + image +
                ", slug=" + slug +
                ", uid=" + getUid() +
                '}';
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