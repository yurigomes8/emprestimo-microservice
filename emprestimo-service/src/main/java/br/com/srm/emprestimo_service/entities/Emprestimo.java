package br.com.srm.emprestimo_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "emprestimo")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorEmprestimo;

    private Integer numeroParcelas;

    private String statusPagamento;

    private LocalDate dataPagamento;

    private LocalDate dataCriacao;

    private LocalDate dataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;
}
