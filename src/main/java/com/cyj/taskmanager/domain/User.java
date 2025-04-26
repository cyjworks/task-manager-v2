package com.cyj.taskmanager.domain;

import com.cyj.taskmanager.dto.UserUpdateDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String fullName;

    public void updateProfile(UserUpdateDTO dto) {
        this.fullName = dto.getFullName();
        this.email = dto.getEmail();
    }
}
