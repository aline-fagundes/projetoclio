package br.com.passaporteclio.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.passaporteclio.domain.dto.EnderecoDto;
import br.com.passaporteclio.domain.dto.MuseusDto;
import br.com.passaporteclio.domain.entity.Endereco;
import br.com.passaporteclio.domain.entity.Museus;
import br.com.passaporteclio.repository.MuseusRepository;

@RunWith(SpringRunner.class)
public class MuseusServiceTest {
	
	@InjectMocks
	private MuseusService museuService;
	
	@Mock
	private MuseusRepository museuRepository;
	
	@Before
	public void setUp() { MockitoAnnotations.openMocks(this); }
	
	public MuseusDto mockMuseusDtoWithoutId() {
		return MuseusDto.builder()
				.descricaoMuseu("Museu Teste")
				.funcionamentoMuseu("funcionamento")
				.nome("Museu Teste")
				.urlFoto("urlFoto")
				.urlInstagram("url insta")
				.urlSite("url Site")
				.endereco(EnderecoDto.builder()
						.bairro("bairro 1")
						.cep("14807406")
						.cidade("Araraquara")
						.estado("SP")
						.numero(10)
						.pais("Brasil")
						.build())
				.build();
	}
	
	public Museus mockMuseusEntity() {
		return Museus.builder()
				.id(1L)
				.descricaoMuseu("Museu Teste")
				.funcionamentoMuseu("funcionamento")
				.nome("Museu Teste")
				.urlFoto("urlFoto")
				.urlInstagram("url insta")
				.urlSite("url Site")
				.endereco(Endereco.builder()
						.id(1L)
						.bairro("bairro 1")
						.cep("14807406")
						.cidade("Araraquara")
						.estado("SP")
						.numero(10)
						.pais("Brasil")
						.build())
				.build();
	}

	@Test
	public void testInserir() {
		MuseusDto museuToInsert = mockMuseusDtoWithoutId();
		
		when(museuRepository.save(any())).thenReturn(mockMuseusEntity());
		
		MuseusDto museuReturned = museuService.inserir(museuToInsert);
		
		Assert.assertTrue(museuReturned.getId() == 1L);
	}
}
