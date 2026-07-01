package com.cg.dto.response;

import com.cg.enums.Gender;
import com.cg.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long userId;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String phone;
    private LocalDate dob;
    private Gender gender;
    private Role role;
    private String emergency_contact_name;
    private String  emergency_contact_phone;
    private Integer is_active;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
