package com.zifrasports.zifrasports.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "category")
@Data
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
