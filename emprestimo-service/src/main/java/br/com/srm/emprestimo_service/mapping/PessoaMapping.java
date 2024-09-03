package br.com.srm.emprestimo_service.mapping;

import br.com.srm.emprestimo_service.entities.Pessoa;
import br.com.srm.emprestimo_service.model.PessoaDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PessoaMapping {

    Pessoa toPessoa(PessoaDTO pessoaDTO);

    PessoaDTO toPessoaDTO(Pessoa pessoa);
}
