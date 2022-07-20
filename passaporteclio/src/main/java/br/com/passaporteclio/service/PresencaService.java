package br.com.passaporteclio.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.passaporteclio.adapter.DozerConverter;
import br.com.passaporteclio.domain.entity.Presenca;
import br.com.passaporteclio.domain.dto.CriacaoPresencaDto;
import br.com.passaporteclio.domain.dto.PresencaDto;
import br.com.passaporteclio.exception.ResourceNotFoundException;
import br.com.passaporteclio.repository.PresencaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PresencaService {

	private PresencaRepository presencaRepository;

	public PresencaDto buscarPorId(Long id) {
		var entity = presencaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Presença não encontrada"));
		return DozerConverter.parseObject(entity, PresencaDto.class);
	}

	public Page<PresencaDto> buscarTodos(Pageable pageable) {
		var page = presencaRepository.findAll(pageable);
		return page.map(presenca -> DozerConverter.parseObject(presenca, PresencaDto.class));
	}

	public Page<PresencaDto> buscarPorMuseuId(Long museuId, Pageable pageable) {
		var page = presencaRepository.findByMuseuId(museuId, pageable);
		return page.map(presenca -> DozerConverter.parseObject(presenca, PresencaDto.class));
	}

	public CriacaoPresencaDto inserir(CriacaoPresencaDto criacaoPresencaDto) {
		var presencaEntity = DozerConverter.parseObject(criacaoPresencaDto, Presenca.class);
		var presencaGravada = DozerConverter.parseObject(presencaRepository.save(presencaEntity),
				CriacaoPresencaDto.class);

		return presencaGravada;
	}

}
