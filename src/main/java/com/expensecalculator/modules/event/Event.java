package com.expensecalculator.modules.event;

import com.expensecalculator.modules.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
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
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "event")
public class Event {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idEvent;

    @NotBlank(message = "Can not null")
    @Column(nullable = false)
    private String name;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime startDataTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "event", orphanRemoval = true)
    private List<User> users;

    @PersistenceConstructor
    public Event() {
    }

    @PrePersist
    protected void onCreated() {
        this.startDataTime = LocalDateTime.now();
    }


//    public void addUser(User user) {
//        if (users == null) {
//            users = new HashSet<>();
//        }
//        users.add(user);
//        user.setEvent(this);
//    }

    @Override
    public String toString() {
        return "Event{" +
                "idEvent=" + idEvent +
                ", name='" + name + '\'' +
                ", startDataTime=" + startDataTime +
                ", users=" + users +
                '}';
    }
}
