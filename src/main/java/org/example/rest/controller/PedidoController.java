package org.example.rest.controller;

import org.example.domain.entity.Pedido;
import org.example.domain.service.PedidoService;
import org.example.rest.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido pedidoSalvo = service.salvar(dto);
        return pedidoSalvo.getId();
    }
}
