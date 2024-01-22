package org.example.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.validation.NotEmptyList;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    @NotNull(message = "Informe o codigo do cliente")
    private Integer cliente;

    @NotNull(message = "Campo total do pedido é obrigatorio")
    private BigDecimal total;

    @NotEmptyList(message = "O pedido não pode ser realizado sem itens")
    private List<ItensPedidosDTO> itens;
}
