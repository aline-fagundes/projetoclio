package br.com.passaporteclio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.passaporteclio.adapter.DozerConverter;
import br.com.passaporteclio.domain.dto.CriaPresencaDto;
import br.com.passaporteclio.domain.dto.PresencaDto;
import br.com.passaporteclio.domain.entity.Presenca;
import br.com.passaporteclio.exception.OperationNotAllowedException;
import br.com.passaporteclio.exception.ResourceNotFoundException;
import br.com.passaporteclio.repository.PresencaRepository;
import lombok.AllArgsConstructor;

@Service
public class PresencaService {

	@Autowired
	private PresencaRepository repository;

	public CriaPresencaDto inserir(CriaPresencaDto criacaoPresencaDto) {
		var presencaEntity = DozerConverter.parseObject(criacaoPresencaDto, Presenca.class);
		var presencaGravada = DozerConverter.parseObject(repository.save(presencaEntity),
				CriaPresencaDto.class);

		return presencaGravada;
	}
	
	
	public PresencaDto buscarPorId(Long id, Long idUsuarioLogado, String perfilUsuarioLogado) {
		var presencaEntity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Presença não encontrada!"));
		
		if(!perfilUsuarioLogado.equals("Administrador") && idUsuarioLogado != presencaEntity.getAutor().getId()) {
			   throw new OperationNotAllowedException("Não é possível consultar presenças de outro visitante!");
			   }
		
		return DozerConverter.parseObject(presencaEntity, PresencaDto.class);
	}
	
	
	public Page<PresencaDto> buscarTodas(Pageable paginacao) {
		var page = repository.findAll(paginacao);
		return page.map(presenca -> DozerConverter.parseObject(presenca, PresencaDto.class));
	}

	
	public Page<PresencaDto> buscarPorMuseu(Long museuId, Pageable paginacao) {
		var page = repository.findByMuseuId(museuId, paginacao);
		return page.map(presenca -> DozerConverter.parseObject(presenca, PresencaDto.class));
	}
	
	
	public Page<PresencaDto> buscarPorVisitante(Long id, Long idUsuarioLogado, String perfilUsuarioLogado, Pageable paginacao) {
		
		if(!perfilUsuarioLogado.equals("Administrador") && !idUsuarioLogado.equals(id)) {
			throw new OperationNotAllowedException("Não é possível consultar presenças de outro visitante!");
		}
		
		var page = repository.findByAutorId(id, paginacao);
		return page.map(this::convertToPresencaDto);
	}

	
	private PresencaDto convertToPresencaDto(Presenca entity) {
		return DozerConverter.parseObject(entity, PresencaDto.class);
	}
}
