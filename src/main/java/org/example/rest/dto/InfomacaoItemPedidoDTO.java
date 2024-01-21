package org.example.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfomacaoItemPedidoDTO {
    private String descicaoProduto;
    private BigDecimal precoUnitario;
    private Integer quantidade;
}
