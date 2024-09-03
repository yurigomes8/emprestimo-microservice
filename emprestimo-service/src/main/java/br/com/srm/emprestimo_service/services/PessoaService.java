package br.com.srm.emprestimo_service.services;

import br.com.srm.emprestimo_service.entities.Pessoa;
import br.com.srm.emprestimo_service.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa salvarPessoa(Pessoa pessoa) {
        definirTipoIdentificador(pessoa);
        definirValoresEmprestimo(pessoa);
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> listarTodasPessoas() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPessoaPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    public void deletarPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }

    private void definirTipoIdentificador(Pessoa pessoa) {
        int length = pessoa.getIdentificador().length();
        if (length == 11) {
            pessoa.setTipoIdentificador("PF");
        } else if (length == 14) {
            pessoa.setTipoIdentificador("PJ");
        } else if (length == 8) {
            pessoa.setTipoIdentificador("EU");
        } else if (length == 10) {
            pessoa.setTipoIdentificador("AP");
        } else {
            throw new IllegalArgumentException("Identificador inválido");
        }
    }

    private void definirValoresEmprestimo(Pessoa pessoa) {
        switch (pessoa.getTipoIdentificador()) {
            case "PF":
                pessoa.setValorMinimoParcelas(300);
                pessoa.setValorMaximoEmprestimo(10000);
                break;
            case "PJ":
                pessoa.setValorMinimoParcelas(1000);
                pessoa.setValorMaximoEmprestimo(100000);
                break;
            case "EU":
                pessoa.setValorMinimoParcelas(100);
                pessoa.setValorMaximoEmprestimo(10000);
                break;
            case "AP":
                pessoa.setValorMinimoParcelas(400);
                pessoa.setValorMaximoEmprestimo(25000);
                break;
            default:
                throw new IllegalArgumentException("Tipo de identificador inválido");
        }
    }

}
