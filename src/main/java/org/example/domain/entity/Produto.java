package org.example.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    @NotEmpty(message = "Campo descrição é obrigatorio")
    private String descricao;

    @Column
    @NotNull(message = "O campo preço é obrigatorio")
    private BigDecimal preco;
}
