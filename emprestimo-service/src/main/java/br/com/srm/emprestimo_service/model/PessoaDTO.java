package br.com.srm.emprestimo_service.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    @NotBlank(message = "Nome é obrigatório")
    @NotNull(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Identificador é obrigatório")
    private String identificador;

    @NotNull(message = "Data de nascimento é obrigatória")
    private LocalDate dataNascimento;
}
