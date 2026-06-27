package com.cg.entity;

import com.cg.enums.Gender;
import com.cg.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String first_name;
    private String last_name;
    private String email;
    @Column(name = "password_hash")
    private String password;
    private String phone;
    @Column(name = "date_of_birth")
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Role  role;

    private String emergency_contact_name;
    private String  emergency_contact_phone;
    private Integer is_active;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    @OneToMany(mappedBy = "updatedBy")
    private List<Inventory> updatedInventories;

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SupportTicket>  supportTickets;
}
