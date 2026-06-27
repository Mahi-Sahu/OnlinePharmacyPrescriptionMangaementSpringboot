package com.cg.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medicine_alternatives")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineAlternatives {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alternative_id;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @ManyToOne
    @JoinColumn(name = "alternative_medicine_id" )
    private Medicine alternativeMedicine;
    private String reason;
}
