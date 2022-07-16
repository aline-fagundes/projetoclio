package br.com.passaporteclio.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.passaporteclio.adapter.DozerConverter;
import br.com.passaporteclio.domain.entity.Avaliacao;
import br.com.passaporteclio.domain.vo.AtualizarAvaliavaoVO;
import br.com.passaporteclio.domain.vo.AvaliacaoVO;
import br.com.passaporteclio.domain.vo.CriacaoAvaliacaoVO;
import br.com.passaporteclio.exception.ResourceNotFoundException;
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

	public CriacaoAvaliacaoVO inserir(@Valid CriacaoAvaliacaoVO avaliacaoVO) {
	
		var avaliacaoEntity = DozerConverter.parseObject(avaliacaoVO, Avaliacao.class);
		var avaliacaoGravada = DozerConverter.parseObject(avaliacaoRepository.save(avaliacaoEntity),CriacaoAvaliacaoVO.class);
		return avaliacaoGravada;
	}

	public AtualizarAvaliavaoVO atualizar(@Valid AtualizarAvaliavaoVO avaliacao) {
		
		var avaliacaoEntity = avaliacaoRepository.findById(avaliacao.getId())
				.orElseThrow(()-> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		avaliacaoEntity.setNota(avaliacao.getNota());
		avaliacaoEntity.setAvaliacao(avaliacao.getAvaliacao());
		
		var avaliacaoAlterada = DozerConverter.parseObject(avaliacaoRepository.save(avaliacaoEntity),AtualizarAvaliavaoVO.class);
		
		return avaliacaoAlterada;
	}

	public void delete(Long id) {
		var entity = avaliacaoRepository.findById(id)
					.orElseThrow(() ->
					new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
			avaliacaoRepository.delete(entity);
		}
		
	}
