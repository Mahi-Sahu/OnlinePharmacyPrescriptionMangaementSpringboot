package com.cg.entity;

import com.cg.enums.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "medicines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id")
    private Long medicineId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "medicine_name")
    private String medicineName;

    @Column(name = "generic_name")
    private String genericName;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    private ProductType productType;

    @Column(name = "wellness_segment")
    private String wellnessSegment;
    private String description;

    @Column(name = "usage_description")
    private String usageDescription;

    @Column(name = "dosage_notes")
    private String dosageNotes;

    private String restrictions;
    private Double price;

    @Column(name = "discount_percentage")
    private Double discountPercentage;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "prescription_required")
    private Integer prescriptionRequired;

    @Column(name = "minimum_age")
    private Integer minimumAge;

    @Column(name = "maximum_order_quantity")
    private Integer maximumOrderQuantity;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "medicine")
    private Inventory inventory;
}
