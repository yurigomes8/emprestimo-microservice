package br.com.srm.emprestimo_service.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String identificador;

    private String tipoIdentificador;

    private LocalDate dataNascimento;

    private double valorMinimoParcelas;

    private double valorMaximoEmprestimo;

    private LocalDate dataCriacao;

    private LocalDate dataAtualizacao;
}
