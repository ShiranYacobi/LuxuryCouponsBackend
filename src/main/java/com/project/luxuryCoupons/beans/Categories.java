package com.project.luxuryCoupons.beans;

import com.project.luxuryCoupons.enums.Category;
import lombok.*;

import javax.persistence.*;

/**
 * The Category bean
 * represent the category information managed by the application
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Categories {
    /**
     * Id field - for each of the enums types
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Name field - for each of the enums types
     */
    @Enumerated(value= EnumType.STRING)
    private Category name;
}
