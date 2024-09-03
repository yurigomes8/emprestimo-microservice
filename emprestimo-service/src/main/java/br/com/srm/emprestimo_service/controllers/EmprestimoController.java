package br.com.srm.emprestimo_service.controllers;

import br.com.srm.emprestimo_service.entities.Emprestimo;
import br.com.srm.emprestimo_service.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/emprestimos")
public class EmprestimoController {
    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<Emprestimo> realizarEmprestimo(@RequestParam Long pessoaId,
                                                         @RequestParam Double valorEmprestimo,
                                                         @RequestParam Integer numeroParcelas) {
        Emprestimo emprestimo = emprestimoService.realizarEmprestimo(pessoaId, valorEmprestimo, numeroParcelas);
        return ResponseEntity.ok(emprestimo);
    }
}
