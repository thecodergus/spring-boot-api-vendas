package org.example.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    @NotEmpty(message = "Campo nome é obrigatorio")
    private String nome;

    @Column(name = "cpf", length = 13)
    @NotEmpty(message = "Campo cpf é obrigatorio")
    @CPF(message = "Informe um cpf valido")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;


    public Cliente(String nome){
        this.nome = nome;
    }
    public Cliente(Integer id, String nome){
        this.id = id;
        this.nome = nome;
    }

}
