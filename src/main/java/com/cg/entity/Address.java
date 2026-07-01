package com.cg.entity;

import com.cg.enums.AddressLabel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Enumerated(EnumType.STRING)
    private AddressLabel address_label;

    private String receiver_name;
    private String receiver_phone;
    private String house_no;
    private String street;
    private String landmark;
    private String city;
    private String state;
    private String pincode;
    private Integer is_default;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
