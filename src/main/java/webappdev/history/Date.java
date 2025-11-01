package webappdev.history;

import jakarta.persistence.*;
import webappdev.login.User;

@Entity
@Table(name = "history")
public class Date {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private java.sql.Date date;

    @Column(name = "amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // Constructors, getters, and setters

    public Date() {
    }

    public Date(Long userId) {
        setUid(userId);
    }

    public Date(String name, java.sql.Date date, int amount, Long userId) {
        this.name = name;
        this.date = date;
        this.amount = amount;
        setUid(userId);
    }

    // Getters and Setters for id, date, amount, and userId

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    // toString() method for logging and debugging purposes
    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", name=" + name +
                ", date=" + date +
                ", amount=" + amount +
                ", uid=" + user.getId() +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
