package com.expensecalculator.event;

import com.expensecalculator.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "event")
public class Event {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idEvent;

    @Column(nullable = false)
    private String name;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime startDataTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "event", orphanRemoval = true)
    private Set<User> users;

    @PersistenceConstructor
    public Event() {
    }

    @PrePersist
    protected void onCreated() {
        this.startDataTime = LocalDateTime.now();
    }

    public Event(String name) {
        this.name = name;
    }

    public void addUser(User user) {
        if (users == null) {
            users = new HashSet<>();
        }
        users.add(user);
        user.setEvent(this);
    }

//    @Override
//    public String toString() {
//        return "Event{" +
//                "idEvent=" + idEvent +
//                ", name='" + name + '\'' +
//                ", startDataTime=" + startDataTime +
//                ", users=" + users +
//                '}';
}
