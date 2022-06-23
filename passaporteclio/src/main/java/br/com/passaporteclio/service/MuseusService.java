package br.com.passaporteclio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.passaporteclio.adapter.DozerConverter;
import br.com.passaporteclio.domain.entity.Endereco;
import br.com.passaporteclio.domain.entity.Museus;
import br.com.passaporteclio.domain.vo.EnderecoVO;
import br.com.passaporteclio.domain.vo.MuseusVO;
import br.com.passaporteclio.repository.MuseusRepository;
import br.com.passaporteclio.exception.ResourceNotFoundException;



@Service
public class MuseusService {


@Autowired
	MuseusRepository repository;

	public MuseusVO inserir(MuseusVO museus) {
		var entity = DozerConverter.parseObject(museus, Museus.class);
		var vo = DozerConverter.parseObject(repository.save(entity),MuseusVO.class);
	return vo;
	}
	
	public Page<MuseusVO> buscarTodos(Pageable pageable) {
		var page = 	repository.findAll(pageable);
		return page.map(this::convertToMuseusVO);
	}

	
	public MuseusVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		return DozerConverter.parseObject(entity,MuseusVO.class);
	}
	
	public void delete(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		repository.delete(entity);
	}

	public MuseusVO atualizar (MuseusVO museus) {
		var entity = repository.findById(museus.getKey())
			.orElseThrow(()-> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		entity.setNome(museus.getNome());
		entity.setDescricaoMuseu(museus.getDescricaoMuseu());
		entity.setFuncionamentoMuseu(museus.getFuncionamentoMuseu());
		entity.setFotoMuseu(museus.getFotoMuseu());
		entity.setEndereco(DozerConverter.parseObject(museus.getEndereco(),Endereco.class));
		var vo = DozerConverter.parseObject(repository.save(entity),MuseusVO.class);
		return vo;
	}
	
	public Page<MuseusVO> findByName(String nome, Pageable pageable){
		var page = repository.findByNome(nome, pageable);
		return page.map(this::convertToMuseusVO);
		
	}

	
	private MuseusVO convertToMuseusVO(Museus entity) {
		return DozerConverter.parseObject(entity, MuseusVO.class);
	}

}
