package org.example.domain.service;

import org.example.domain.entity.Pedido;
import org.example.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
}
