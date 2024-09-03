package br.com.srm.emprestimo_service.services;

import br.com.srm.emprestimo_service.entities.Emprestimo;
import br.com.srm.emprestimo_service.entities.Pessoa;
import br.com.srm.emprestimo_service.repositories.EmprestimoRepository;
import br.com.srm.emprestimo_service.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Optional;


@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public Emprestimo realizarEmprestimo(Long pessoaId, Double valorEmprestimo, Integer numeroParcelas) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(pessoaId);
        if (pessoaOptional.isEmpty()) {
            throw new IllegalArgumentException("Pessoa não encontrada.");
        }

        Pessoa pessoa = pessoaOptional.get();

        // Validar CPF, CNPJ, e outras regras
        validarIdentificador(pessoa);
        validarEmprestimo(pessoa, valorEmprestimo, numeroParcelas);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setPessoa(pessoa);
        emprestimo.setValorEmprestimo(valorEmprestimo);
        emprestimo.setNumeroParcelas(numeroParcelas);
        emprestimo.setStatusPagamento("Pendente");
        emprestimo.setDataCriacao(LocalDate.now());
        emprestimo.setDataAtualizacao(LocalDate.now());

        emprestimo = emprestimoRepository.save(emprestimo);

        executarPagamento(emprestimo);

        return emprestimo;
    }

    private void validarIdentificador(Pessoa pessoa) {
        String identificador = pessoa.getIdentificador();
        String tipoIdentificador = pessoa.getTipoIdentificador();

        switch (tipoIdentificador) {
            case "PF":
                validarCPF(identificador);
                break;
            case "PJ":
                validarCNPJ(identificador);
                break;
            case "EU":
                validarEstudanteUniversitario(identificador);
                break;
            case "AP":
                validarAposentado(identificador);
                break;
            default:
                throw new IllegalArgumentException("Tipo de identificador inválido.");
        }
    }

    private void validarCPF(String cpf) {
        if (cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido.");
        }
    }

    private void validarCNPJ(String cnpj) {
        if (cnpj.length() != 14 || !cnpj.matches("\\d{14}")) {
            throw new IllegalArgumentException("CNPJ inválido.");
        }
    }

    private void validarEstudanteUniversitario(String identificador) {
        if (identificador.length() != 8) {
            throw new IllegalArgumentException("Identificador de Estudante Universitário inválido.");
        }
        int primeiroDigito = Character.getNumericValue(identificador.charAt(0));
        int ultimoDigito = Character.getNumericValue(identificador.charAt(7));
        if (primeiroDigito + ultimoDigito != 9) {
            throw new IllegalArgumentException("Soma do primeiro e último dígito do identificador de Estudante Universitário deve ser 9.");
        }
    }

    private void validarAposentado(String identificador) {
        if (identificador.length() != 10) {
            throw new IllegalArgumentException("Identificador de Aposentado inválido.");
        }
        char ultimoDigito = identificador.charAt(9);
        if (identificador.substring(0, 9).indexOf(ultimoDigito) != -1) {
            throw new IllegalArgumentException("O último dígito do identificador de Aposentado não pode estar presente nos outros 9 dígitos.");
        }
    }

    private void validarEmprestimo(Pessoa pessoa, Double valorEmprestimo, Integer numeroParcelas) {
        if (numeroParcelas > 24) {
            throw new IllegalArgumentException("Número de parcelas não pode exceder 24.");
        }

        if (valorEmprestimo > pessoa.getValorMaximoEmprestimo()) {
            throw new IllegalArgumentException("Valor do empréstimo excede o limite máximo permitido.");
        }

        Double valorMinimoParcelas = pessoa.getValorMinimoParcelas();
        Double valorParcela = valorEmprestimo / numeroParcelas;
        if (valorParcela < valorMinimoParcelas) {
            throw new IllegalArgumentException("Valor das parcelas é inferior ao mínimo permitido.");
        }
    }

    private void executarPagamento(Emprestimo emprestimo) {
        try{
            this.kafkaTemplate.send("topico-pagamento", emprestimo.getId().toString());
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao executar pagamento.", e);
        }

    }
}
