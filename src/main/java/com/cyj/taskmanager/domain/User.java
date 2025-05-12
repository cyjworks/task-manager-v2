package com.cyj.taskmanager.domain;

import com.cyj.taskmanager.dto.user.UserUpdateDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private String fullName;
    private String bio;
    private String location;
    private String profileImageUrl;

    public void updateProfile(UserUpdateDTO dto, PasswordEncoder passwordEncoder) {
        if(dto.getPassword() != null && !dto.getPassword().isBlank()) {
            this.password = passwordEncoder.encode(dto.getPassword());
        }
        if(dto.getEmail() != null) {
            this.email = dto.getEmail();
        }
        if(dto.getFullName() != null) {
            this.fullName = dto.getFullName();
        }
        if(dto.getBio() != null) {
            this.bio = dto.getBio();
        }
        if(dto.getLocation() != null) {
            this.location = dto.getLocation();
        }
        if(dto.getProfileImageUrl() != null) {
            this.profileImageUrl = dto.getProfileImageUrl();
        }
    }
}
