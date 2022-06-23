package br.com.passaporteclio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.passaporteclio.adapter.DozerConverter;
import br.com.passaporteclio.domain.entity.Endereco;
import br.com.passaporteclio.domain.vo.EnderecoVO;
import br.com.passaporteclio.exception.ResourceNotFoundException;
import br.com.passaporteclio.repository.EnderecoRepository;


@Service
public class EnderecoService {
	
	@Autowired
	EnderecoRepository repository;

	public EnderecoVO inserir(EnderecoVO endereco) {
		var entity = DozerConverter.parseObject(endereco, Endereco.class);
		var vo = DozerConverter.parseObject(repository.save(entity),EnderecoVO.class);
	return vo;
	}
	
	public Page<EnderecoVO> buscarTodos(Pageable pageable) {
		var page = 	repository.findAll(pageable);
		return page.map(this::convertToEnderecoVO);
	}

	
	public EnderecoVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		return DozerConverter.parseObject(entity,EnderecoVO.class);
	}
	
	public void delete(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		repository.delete(entity);
	}

	public EnderecoVO atualizar (EnderecoVO endereco) {
		var entity = repository.findById(endereco.getKey())
			.orElseThrow(()-> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		entity.setCep(endereco.getCep());
		entity.setRua(endereco.getRua());
		entity.setNumero(endereco.getNumero());
		entity.setBairro(endereco.getBairro());
		entity.setCidade(endereco.getCidade());
		entity.setEstado(endereco.getEstado());
		entity.setPais(endereco.getPais());
		var vo = DozerConverter.parseObject(repository.save(entity),EnderecoVO.class);
		return vo;
	}
	
	public Page<EnderecoVO> findByCep(String cep, Pageable pageable){
		var page = repository.findByCep(cep, pageable);
		return page.map(this::convertToEnderecoVO);
		
	}

	
	private EnderecoVO convertToEnderecoVO(Endereco entity) {
		return DozerConverter.parseObject(entity, EnderecoVO.class);
	}

	
}


