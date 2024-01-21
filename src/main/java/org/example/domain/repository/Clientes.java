package org.example.domain.repository;

import org.example.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface Clientes extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNomeLike(String nome);
    List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);
    Cliente findOneByNome(String nome);
    @Query(value = "select c from Cliente c where c.nome like :nome")
    List<Cliente> procurarPorNome(String nome);

    boolean existsByNome(String nome);

    @Query(value = "select c from Cliente c left join fetch c.pedidos where c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);

//    Cliente
}
