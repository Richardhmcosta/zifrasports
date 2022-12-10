package com.zifrasports.zifrasports.repository;

import com.zifrasports.zifrasports.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    public List<ProductEntity> findByCategory(String category);
}
