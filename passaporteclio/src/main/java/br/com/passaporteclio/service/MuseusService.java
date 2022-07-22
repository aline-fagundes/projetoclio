package br.com.passaporteclio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.passaporteclio.adapter.DozerConverter;
import br.com.passaporteclio.domain.dto.MuseusDto;
import br.com.passaporteclio.domain.dto.NotaMediaMuseuDto;
import br.com.passaporteclio.domain.entity.Museus;
import br.com.passaporteclio.exception.ResourceNotFoundException;
import br.com.passaporteclio.repository.MuseusRepository;

@Service
public class MuseusService {

	@Autowired
	MuseusRepository repository;

	public MuseusDto inserir(MuseusDto museuDto) {
		System.out.println("Iniciando método inserir...");

		var museuEntity = DozerConverter.parseObject(museuDto, Museus.class);
		var enderecoEntity = museuEntity.getEndereco();
		enderecoEntity.setMuseu(museuEntity);

		var museuGravado = DozerConverter.parseObject(repository.save(museuEntity), MuseusDto.class);

		System.out.println("Finalizando método inserir...");
		return museuGravado;
	}
	

	public Page<MuseusDto> buscarTodos(Pageable paginacao) {
		var page = repository.findAll(paginacao);
		return page.map(this::convertToMuseusDto);
	}

	
	public MuseusDto buscarPorId(Long id) {
		var museuEntity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id!"));
		return DozerConverter.parseObject(museuEntity, MuseusDto.class);
	}

	
	public Page<MuseusDto> findByName(String nome, Pageable paginacao) {
		var page = repository.findByNome(nome, paginacao);
		return page.map(this::convertToMuseusDto);
	}
	
	
	public MuseusDto atualizar(Long id, MuseusDto museus) {
		System.out.println("Iniciando método atualizar...");

		var entityMuseu = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id!"));
		entityMuseu.setNome(museus.getNome());
		entityMuseu.setDescricaoMuseu(museus.getDescricaoMuseu());
		entityMuseu.setFuncionamentoMuseu(museus.getFuncionamentoMuseu());
		entityMuseu.setUrlFoto(museus.getUrlFoto());
		entityMuseu.setUrlSite(museus.getUrlSite());
	    entityMuseu.setUrlInstagram(museus.getUrlInstagram());		
		entityMuseu.getEndereco().setBairro(museus.getEndereco().getBairro());
		entityMuseu.getEndereco().setCep(museus.getEndereco().getCep());
		entityMuseu.getEndereco().setCidade(museus.getEndereco().getCidade());
		entityMuseu.getEndereco().setEstado(museus.getEndereco().getEstado());
		entityMuseu.getEndereco().setNumero(museus.getEndereco().getNumero());
		entityMuseu.getEndereco().setRua(museus.getEndereco().getRua());
		entityMuseu.getEndereco().setPais(museus.getEndereco().getPais());

		var museuAlterado = DozerConverter.parseObject(repository.save(entityMuseu), MuseusDto.class);

		System.out.println("Finalizando método atualizar...");
		return museuAlterado;
	}
	

	public void deletar(Long id) {
		System.out.println("Iniciando método deletar...");
		
		var museuEntity = repository.findById(id)
				.orElseThrow(() -> 
				new ResourceNotFoundException("Não foi encontrado registro com esse Id!"));
		repository.delete(museuEntity);
		
		System.out.println("Finalizando método atualizar...");
	}


	public NotaMediaMuseuDto calculaMedia(Long id) {
		repository.findById(id)
				.orElseThrow(() -> 
				new ResourceNotFoundException("Não foi encontrado registro com esse Id!"));
		
		double notaMedia = repository.getNotaMedia(id);
		
		NotaMediaMuseuDto notaMediaMuseuDto = new NotaMediaMuseuDto();
		notaMediaMuseuDto.setNotaMedia(notaMedia);
		
		return notaMediaMuseuDto;
	}
	
	
	private MuseusDto convertToMuseusDto(Museus entity) {
		return DozerConverter.parseObject(entity, MuseusDto.class);
	}
}
