package org.example.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.entity.Cliente;
import org.example.domain.entity.ItemPedido;
import org.example.domain.entity.Pedido;
import org.example.domain.entity.Produto;
import org.example.domain.repository.Clientes;
import org.example.domain.repository.ItensPedidos;
import org.example.domain.repository.Pedidos;
import org.example.domain.repository.Produtos;
import org.example.domain.service.PedidoService;
import org.example.exception.RegraNegocioException;
import org.example.rest.dto.ItensPedidosDTO;
import org.example.rest.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private final Pedidos repository;

    @Autowired
    private final Clientes clientesRepository;

    @Autowired
    private final Produtos produtosRepository;

    @Autowired
    private final ItensPedidos itensPedidosRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> {
                    return new RegraNegocioException("Codigo de cliente invalido");
                });

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedido = converterItens(pedido, dto.getItens());
        repository.save(pedido);
        itensPedidosRepository.saveAll(itensPedido);

        pedido.setItens(itensPedido);

        return pedido;
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItensPedidosDTO> itens){
        if(itens.isEmpty()){
            throw new RegraNegocioException("Não é possivel realizar um pedido sem itens");
        }

    return itens
            .stream()
            .map(dto -> {
                Integer idProduto = dto.getProduto();
                Produto produto = produtosRepository
                        .findById(idProduto)
                        .orElseThrow(() -> {
                            return new RegraNegocioException("Codigo de produto invalido: " + idProduto);
                        });;

                ItemPedido item = new ItemPedido();
                item.setQuantidade(dto.getQuantidade());
                item.setPedido(pedido);
                item.setProduto(produto);

                return item;
            }).collect(Collectors.toList());
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id){
        return repository
                .findByIdFetchItens(id);

    }
}

