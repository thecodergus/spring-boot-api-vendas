package org.example.rest.controller;

import org.example.domain.entity.Cliente;
import org.example.domain.entity.Produto;
import org.example.domain.repository.Produtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    private Produtos produtos;

    @GetMapping
    public List<Produto> findAll(){
        return produtos
                .findAll();
    }

    @GetMapping("/{id}")
    public Produto findProduto(@PathVariable Integer id){
        return produtos
                .findById(id)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto postProduto(@RequestBody Produto produto){
        return produtos.save(produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduto(@PathVariable Integer id){
        produtos
                .findById(id)
                .map(produto -> {
                    produtos.delete((produto));
                    return produto;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

    }
    @PutMapping("/{id}")
    public ResponseEntity putClient(@PathVariable Integer id, @RequestBody Produto produto){
        return produtos
                .findById(id)
                .map(clienteExistente ->{
                    produto.setId(clienteExistente.getId());
                    produtos.save(produto);

                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> {
                    return ResponseEntity.notFound().build();
                });
    }
}
