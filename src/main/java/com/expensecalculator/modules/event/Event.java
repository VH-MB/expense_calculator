package com.expensecalculator.modules.event;

import com.expensecalculator.modules.person.Person;
import com.expensecalculator.security.user.User;
import com.expensecalculator.shared.validation.ValidationMessages;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
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

    @Column(nullable = false)
    private String name;

    @JsonFormat(pattern = ValidationMessages.FORMAT_DATE)
    @Column(updatable = false)
    private LocalDateTime startDataTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "event", orphanRemoval = true)
    private List<Person> persons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    private User user;

    @PersistenceConstructor
    public Event() {
    }

    @PrePersist
    protected void onCreated() {
        this.startDataTime = LocalDateTime.now();
    }

    public void addUser(User newUser) {
        if (user == null) user = new User();
        this.user = newUser;
    }

    @Override
    public String toString() {
        return "Event{" +
                "idEvent=" + idEvent +
                ", name='" + name + '\'' +
                ", startDataTime=" + startDataTime +
                ", persons=" + persons +
                '}';
    }
}
