package com.expensecalculator.modules.payment;

import com.expensecalculator.modules.person.Person;
import com.expensecalculator.shared.validation.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
import javax.persistence.PrePersist;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    @Column(columnDefinition = "text", nullable = false)
    private String description;

    @Column
    private String location;

    @Column(nullable = false)
    private BigDecimal price;

    @JsonFormat(pattern = ValidationMessages.FORMAT_DATE)
    @Column(updatable = false)
    private LocalDateTime startDataTime;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH})
    @JoinColumn(name = "person_id")
    private Person person;

    @PersistenceConstructor
    public Payment() {
    }

    @PrePersist
    protected void onCreated() {
        this.startDataTime = LocalDateTime.now();
    }

    public void addPerson(Person newPerson) {
        if (person == null) person = new Person();
        this.person = newPerson;
    }
}
