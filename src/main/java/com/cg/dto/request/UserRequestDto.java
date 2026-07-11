package com.cg.dto.request;

import com.cg.enums.Gender;
import com.cg.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private LocalDate dob;
    private Gender gender;
    private Role role;
    private String emergencyContactName;
    private String  emergencyContactPhone;
    private Integer isActive;

}
