package org.example.domain.repository;

import org.example.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Produtos extends JpaRepository<Produto, Integer> {
    Produto findOneById(Integer id);
}
