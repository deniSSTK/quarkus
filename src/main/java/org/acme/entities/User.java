package org.acme.entities;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;
import org.acme.enums.UserRole;
import org.acme.enums.UserStatus;
import org.acme.services.UserService;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User extends PanacheEntity {

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String email;

    @Column(nullable = false)
    public String passwordHash;

    @Column(name = "created_at_local", nullable = false)
    public LocalDateTime createdAtLocal = LocalDateTime.now();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public UserStatus status =  UserStatus.ACTIVE;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public UserRole role = UserRole.DEFAULT;

    public User() {}

    public User(
            String name,
            String email,
            String passwordHash
    ) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
