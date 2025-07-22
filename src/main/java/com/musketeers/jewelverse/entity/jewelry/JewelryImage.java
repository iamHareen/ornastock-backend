package com.musketeers.jewelverse.entity.jewelry;

import com.musketeers.jewelverse.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "jewelry_images")
public class JewelryImage extends BaseEntity {

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "is_primary")
    private Boolean isPrimary = false;

    @Column(name = "display_order")
    private Integer displayOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jewelry_id", nullable = false)
    private Jewelry jewelry;

    // Constructors
    public JewelryImage() {}

    public JewelryImage(String imageUrl, String imageName, Jewelry jewelry) {
        this.imageUrl = imageUrl;
        this.imageName = imageName;
        this.jewelry = jewelry;
    }

}

