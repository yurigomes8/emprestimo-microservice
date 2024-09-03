package br.com.srm.emprestimo_service.repositories;

import br.com.srm.emprestimo_service.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository  extends JpaRepository<Pessoa, Long> {
}
