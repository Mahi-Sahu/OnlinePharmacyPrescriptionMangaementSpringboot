package com.cg.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "medicine_tag_mapping")
public class MedicineTagMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicine_tag_mapping_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private MedicineTag tag;
}
