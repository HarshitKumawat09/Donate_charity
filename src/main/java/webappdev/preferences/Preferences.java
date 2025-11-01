package webappdev.preferences;

import jakarta.persistence.*;
import webappdev.login.User;

@Entity
@Table(name = "preferences")
public class Preferences
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cause;
    private long size;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Preferences() {}

    public Preferences(String causes, Long user_id) {
        this.cause = causes;
        this.size = 0;
        setUid(user_id);
    }

    public Long getUid() {
        return (user != null) ? user.getId() : -1;
    }

    public void setUid(Long uid) {
        if (user == null) {
            user = new User();
        }
        user.setId(uid);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String causes) {
        this.cause = causes;
    }

    public User getUser() { return this.user; }

    public void setUser(User u) { this.user = u; }

    @Override
    public String toString() {
        return "Preferences{" +
                "id=" + id +
                ", cause='" + getCause() +
                ", uid='" + getUid() +
                '}';
    }
}
