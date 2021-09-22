package com.expensecalculator.modules.user;

import com.expensecalculator.modules.event.Event;
import com.expensecalculator.modules.payment.Payment;
import com.expensecalculator.modules.user.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @Column(nullable = false)
    private String firstName;
    @Column
    private String lastName;
    @Column(unique = true)
    private String userName;
    @Column(unique = true, updatable = false)
    private String email;
    @Column(length = 300)
    private String password;

    @ElementCollection(targetClass = ERole.class)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<ERole> role = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade =
            {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_event")
    private Event event;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Payment> payments;

    @Transient
    public Collection<? extends GrantedAuthority> authorities;

    @PersistenceConstructor
    public User() {
    }

    public User(Long idUser, String userName, String password, String email,
                Collection<? extends GrantedAuthority> authorities) {
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    void addEvent(Event newEvent) {
        if (event == null) event = new Event();
        this.event = newEvent;
    }

    /**
     * SECURITY
     */

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
