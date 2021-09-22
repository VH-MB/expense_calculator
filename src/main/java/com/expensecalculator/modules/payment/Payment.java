package com.expensecalculator.modules.payment;

import com.expensecalculator.modules.user.User;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.math.BigDecimal.ROUND_HALF_UP;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    @NotBlank(message = "Description cannot be empty")
    @Size(min = 5, max = 50)
    @Column(columnDefinition = "text", nullable = false)
    private String description;

    @Column
    private String location;

    @Column(nullable = false)
    private BigDecimal price;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime startDataTime;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @PersistenceConstructor
    public Payment() {
    }

    @PrePersist
    protected void onCreated() {
        this.startDataTime = LocalDateTime.now();
    }

    public void addUser(User newUser) {
        if (user == null) user = new User();
        this.user = newUser;
    }
}
