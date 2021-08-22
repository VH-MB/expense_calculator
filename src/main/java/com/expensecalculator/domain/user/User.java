package com.expensecalculator.domain.user;

import com.expensecalculator.domain.event.MeetingEvent;
import com.expensecalculator.domain.payment.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(nullable = false)
    private String firstName;

    @Column
    private String lastName;

    @Column(columnDefinition = "text", nullable = false)
    private String description;

    @Column
    private String location;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idEvent")
    private MeetingEvent meetingEvent;

    @ManyToMany(mappedBy = "users")
    private Set<Payment> payments;

    @PersistenceConstructor
    public User() {
    }

//    public User(String firstName, String lastName, String description, String location) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.description = description;
//        this.location = location;
//    }
}
