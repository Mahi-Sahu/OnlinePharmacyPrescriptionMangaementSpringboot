package com.cg.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prescription_medicine_mapping")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionMedicineMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_medicine_mapping_id")
    private Long prescriptionMappingId;

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @Column(name = "requested_quantity")
    private Integer requestedQuantity;

    @Column(name = "doctor_notes")
    private  String doctorNotes;

}
