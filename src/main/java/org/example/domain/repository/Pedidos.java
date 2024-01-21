package org.example.domain.repository;

import org.example.domain.entity.Cliente;
import org.example.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
    Optional<Pedido> findByIdFetchItens(Integer id);
}
