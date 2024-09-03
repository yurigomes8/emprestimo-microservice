package br.com.srm.emprestimo_service.controllers;

import br.com.srm.emprestimo_service.entities.Pessoa;
import br.com.srm.emprestimo_service.mapping.PessoaMapping;
import br.com.srm.emprestimo_service.model.PessoaDTO;
import br.com.srm.emprestimo_service.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private  PessoaMapping pessoaMapping;


    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@Validated @RequestBody PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaMapping.toPessoa(pessoaDTO);
        Pessoa pessoaSalva = pessoaService.salvarPessoa(pessoa);
        return ResponseEntity.ok(pessoaSalva);
    }

    @GetMapping
    public List<Pessoa> listarPessoas() {
        return pessoaService.listarTodasPessoas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaService.buscarPessoaPorId(id);
        return pessoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }
}
