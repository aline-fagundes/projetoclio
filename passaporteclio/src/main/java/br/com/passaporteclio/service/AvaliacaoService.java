package br.com.passaporteclio.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class AvaliacaoService {

	@Autowired
	private AvaliacaoRepository repository;
	
	public CriaAvaliacaoDto inserir(CriaAvaliacaoDto avaliacaoDto) {
		System.out.println("Iniciando método inserir...");
		
		var avaliacaoEntity = DozerConverter.parseObject(avaliacaoDto, Avaliacao.class);
		var avaliacaoGravada = DozerConverter.parseObject(repository.save(avaliacaoEntity), CriaAvaliacaoDto.class);
		
		System.out.println("Finalizando método inserir...");
		return avaliacaoGravada;
	}


	public Page<AvaliacaoDto> buscarTodas(Pageable paginacao) {
		var page = repository.findAll(paginacao);
		return page.map(this::convertToAvaliacaoDto);
	}


	public AvaliacaoDto buscarPorId(Long id) {
		var avaliacaoEntity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id!"));
		return DozerConverter.parseObject(avaliacaoEntity, AvaliacaoDto.class);
	}


	public Page<AvaliacaoDto> buscarPorMuseu(Long idMuseu, Pageable paginacao) {
		var page = repository.findByMuseuId(idMuseu, paginacao);
		return page.map(this::convertToAvaliacaoDto);
	}

	
	public Map<String, List<AvaliacaoDto>> buscarPorVisitanteSeparadoPorMuseu(Long id, Long idUsuarioLogado, String perfilUsuarioLogado) {

		if(!perfilUsuarioLogado.equals("Administrador") && !idUsuarioLogado.equals(id)) {
			throw new OperationNotAllowedException("Não é possível consultar a lista de avaliações de outro visitante!");
		}
		
		List<Avaliacao> avaliacoesEntity = repository.findByAutorId(id);
		Map<String, List<AvaliacaoDto>> avaliacoesPorMuseu = new HashMap<String, List<AvaliacaoDto>>();
		
		for (Avaliacao avaliacao : avaliacoesEntity) {
			String nomeMuseu = avaliacao.getMuseu().getNome();
			
			if(!avaliacoesPorMuseu.containsKey(nomeMuseu)) {
				avaliacoesPorMuseu.put(nomeMuseu, new ArrayList<AvaliacaoDto>());
			}
			List<AvaliacaoDto> avalicoesDoMuseu = avaliacoesPorMuseu.get(nomeMuseu);
			
			AvaliacaoDto avaliacaoDto = DozerConverter.parseObject(avaliacao, AvaliacaoDto.class);						
			avalicoesDoMuseu.add(avaliacaoDto);
		}
		
		return avaliacoesPorMuseu;
	}


	public AtualizaAvaliacaoDto atualizar(Long id, AtualizaAvaliacaoDto avaliacao, Long idUsuarioLogado) {
		System.out.println("Iniciando método atualizar...");
		
		var avaliacaoEntity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id!"));
		
		if(idUsuarioLogado != avaliacaoEntity.getAutor().getId()) {
			throw new OperationNotAllowedException("Não é possível alterar uma avaliação de outro visitante!");
		}
		
		avaliacaoEntity.setNota(avaliacao.getNota());
		avaliacaoEntity.setAvaliacao(avaliacao.getAvaliacao());
	
		var avaliacaoAlterada = DozerConverter.parseObject(repository.save(avaliacaoEntity), AtualizaAvaliacaoDto.class);
		
		System.out.println("Finalizando método atualizar...");
		return avaliacaoAlterada;
	}


	public void deletar(Long id, Long idUsuarioLogado, String perfilUsuarioLogado) {
		System.out.println("Iniciando método deletar...");
		
		var avaliacaoEntity = repository.findById(id)
					.orElseThrow(() ->
					new ResourceNotFoundException("Não foi encontrado registro com esse Id!"));
			
		if(!perfilUsuarioLogado.equals("Administrador") && idUsuarioLogado != avaliacaoEntity.getAutor().getId()) {
			throw new OperationNotAllowedException("Não é possível deletar uma avaliação de outro visitante!");
		}
		
		repository.delete(avaliacaoEntity);
			
			System.out.println("Finalizando método deletar...");
		}


	public void denunciar(Long id) {
		var avaliacaoEntity = repository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException("Não foi encontrado registro com esse Id!"));
		
		avaliacaoEntity.setDenunciada(true);
		repository.save(avaliacaoEntity);
	}		


	public Page<AvaliacaoDto> buscarAvaliacoesDenunciadas(Pageable paginacao) {
		var page = repository.findByDenunciada(true, paginacao);
		return page.map(this::convertToAvaliacaoDto);
	}


	private AvaliacaoDto convertToAvaliacaoDto(Avaliacao entity) {
		return DozerConverter.parseObject(entity, AvaliacaoDto.class);
	}
}
