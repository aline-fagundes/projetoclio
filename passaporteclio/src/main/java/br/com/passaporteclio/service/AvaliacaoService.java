package br.com.passaporteclio.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.passaporteclio.adapter.DozerConverter;
import br.com.passaporteclio.domain.entity.Avaliacao;
import br.com.passaporteclio.domain.vo.AvaliacaoVO;
import br.com.passaporteclio.repository.AvaliacaoRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AvaliacaoService {

	private AvaliacaoRepository avaliacaoRepository;

	public Page<AvaliacaoVO> getAll(Pageable pageable) {
		var page = avaliacaoRepository.findAll(pageable);
		return page.map(this::convertToAvaliacaoVO);
	}

	public AvaliacaoVO getById(Long id) {
		var entity = avaliacaoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não foi encontrado registro com esse Id"));
		return DozerConverter.parseObject(entity, AvaliacaoVO.class);
	}

	private AvaliacaoVO convertToAvaliacaoVO(Avaliacao entity) {
		return DozerConverter.parseObject(entity, AvaliacaoVO.class);
	}

}
