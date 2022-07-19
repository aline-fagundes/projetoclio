package br.com.passaporteclio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.passaporteclio.adapter.DozerConverter;
import br.com.passaporteclio.domain.dto.AtualizaAvaliacaoDto;
import br.com.passaporteclio.domain.dto.AvaliacaoDto;
import br.com.passaporteclio.domain.dto.CriaAvaliacaoDto;
import br.com.passaporteclio.domain.entity.Avaliacao;
import br.com.passaporteclio.exception.OperationNotAllowedException;
import br.com.passaporteclio.exception.ResourceNotFoundException;
import br.com.passaporteclio.repository.AvaliacaoRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AvaliacaoService {

	private AvaliacaoRepository repository;
	
	public CriaAvaliacaoDto inserir(CriaAvaliacaoDto avaliacaoDto) {
		System.out.println("Iniciando método inserir...");
		
		var avaliacaoEntity = DozerConverter.parseObject(avaliacaoDto, Avaliacao.class);
		var avaliacaoGravada = DozerConverter.parseObject(repository.save(avaliacaoEntity), CriaAvaliacaoDto.class);
		
		System.out.println("Finalizando método inserir...");
		return avaliacaoGravada;
	}

	public Page<AvaliacaoDto> buscarTodas(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToAvaliacaoDto);
	}

	public AvaliacaoDto buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id!"));
		return DozerConverter.parseObject(entity, AvaliacaoDto.class);
	}
	
	public Page<AvaliacaoDto> buscarPorMuseu(Long idMuseu, Pageable paginacao) {
		var page = repository.findByMuseuId(idMuseu, paginacao);
		return page.map(this::convertToAvaliacaoDto);
	}

	public AtualizaAvaliacaoDto atualizar(Long id, AtualizaAvaliacaoDto avaliacao, Long idUsuarioLogado) {
		System.out.println("Iniciando método atualizar...");
		
		var entityAvaliacao = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id!"));
		
		if(idUsuarioLogado != entityAvaliacao.getAutor().getId()) {
			throw new OperationNotAllowedException("Não é possível alterar uma avaliação de outro visitante!");
		}
		
		entityAvaliacao.setNota(avaliacao.getNota());
		entityAvaliacao.setAvaliacao(avaliacao.getAvaliacao());
	
		var avaliacaoAlterada = DozerConverter.parseObject(repository.save(entityAvaliacao), AtualizaAvaliacaoDto.class);
		
		System.out.println("Finalizando método atualizar...");
		return avaliacaoAlterada;
	}

	public void deletar(Long id, Long idUsuarioLogado, String perfilUsuarioLogado) {
		System.out.println("Iniciando método deletar...");
		
		var entityAvaliacao = repository.findById(id)
					.orElseThrow(() ->
					new ResourceNotFoundException("Não foi encontrado registro com esse Id!"));
			
		if(!perfilUsuarioLogado.equals("Administrador") && idUsuarioLogado != entityAvaliacao.getAutor().getId()) {
			throw new OperationNotAllowedException("Não é possível deletar uma avaliação de outro visitante!");
		}
		
		repository.delete(entityAvaliacao);
			
			System.out.println("Finalizando método deletar...");
		}
	
	private AvaliacaoDto convertToAvaliacaoDto(Avaliacao entity) {
		return DozerConverter.parseObject(entity, AvaliacaoDto.class);
	}		
}
