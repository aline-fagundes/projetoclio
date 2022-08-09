package br.com.passaporteclio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.passaporteclio.domain.dto.AlteraVisitanteDto;
import br.com.passaporteclio.domain.dto.RetornoVisitanteDto;
import br.com.passaporteclio.domain.dto.UserDto;
import br.com.passaporteclio.domain.dto.VisitanteDto;
import br.com.passaporteclio.domain.entity.Permission;
import br.com.passaporteclio.domain.entity.User;
import br.com.passaporteclio.domain.entity.Visitante;
import br.com.passaporteclio.repository.PermissionRepository;
import br.com.passaporteclio.repository.UserRepository;
import br.com.passaporteclio.repository.VisitanteRepository;

@ExtendWith(SpringExtension.class)
public class VisitanteServiceTest {

	@InjectMocks
	private VisitanteService visitanteService;

	@Mock
	private VisitanteRepository visitanteRepository;

	@Mock
	private PermissionRepository permissionRepository;

	@Mock
	private UserRepository userRepository;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	public VisitanteDto mockVisitanteSemId() {
		return VisitanteDto.builder().nome("Teste A").sobrenome("Teste B")
				.user(UserDto.builder().email("teste@email.com").senha("123").build()).build();
	}

	public Visitante mockVisitanteEntity() {
		return Visitante.builder().id(1L).nome("Teste A").sobrenome("Teste B")
				.user(User.builder().id(1L).email("teste@email.com").senha("123").build()).build();
	}

	public User mockUserEntity() {
		return User.builder().id(1L).email("teste@email.com").senha("123").build();
	}

	public Permission mockPermission() {
		return Permission.builder().id(1L).perfil("Visitante").build();
	}

	public Page<Visitante> mockPageVisitante() {
		return new PageImpl<>(Collections.singletonList(mockVisitanteEntity()));
	}

	public AlteraVisitanteDto mockAlteraVisitanteDto() {
		return AlteraVisitanteDto.builder().nome("Teste").sobrenome("Tes Te").build();
	}

	@Test
	public void testaInserirVisitante() {

		when(userRepository.findByEmail(any())).thenReturn(null);

		when(userRepository.save(any())).thenReturn(mockUserEntity());

		when(visitanteRepository.save(any())).thenReturn(mockVisitanteEntity());

		when(permissionRepository.findFirstByPerfil(any())).thenReturn(mockPermission());

		RetornoVisitanteDto retornoVisitanteDto = visitanteService.inserir(mockVisitanteSemId());

		assertTrue(retornoVisitanteDto != null);
	}

	@Test
	public void testaInserirVisitanteSemSucesso() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			when(userRepository.findByEmail(any())).thenReturn(mockUserEntity());

			visitanteService.inserir(mockVisitanteSemId());
		});
		assertTrue(exception.getMessage().equals("Já existe cadastro com o e-mail informado!"));
	}

	@Test
	public void testaAtualizarVisitante() {
		when(visitanteRepository.findById(1L)).thenReturn(Optional.of(mockVisitanteEntity()));

		when(visitanteRepository.save(any())).thenReturn(mockVisitanteEntity());

		RetornoVisitanteDto retornoVisitanteDto = visitanteService.atualizar(1L, mockAlteraVisitanteDto(), 1L);

		assertTrue(retornoVisitanteDto != null);

	}

	@Test
	public void testaBuscarTodosVisitantes() {
		Pageable pageable = PageRequest.of(0, 8);

		when(visitanteRepository.findAll(pageable)).thenReturn(mockPageVisitante());

		Page<RetornoVisitanteDto> retornoVisitanteDtoPage = visitanteService.buscarTodos(pageable);

		assertEquals(1L, retornoVisitanteDtoPage.getTotalElements());
	}

	@Test
	public void testaBuscarVisitantePorId() {
		when(visitanteRepository.findById(any())).thenReturn(Optional.of(mockVisitanteEntity()));

		RetornoVisitanteDto retornoVisitanteDto = visitanteService.buscarPorId(1L, 1L, "Visitante");

		assertTrue(retornoVisitanteDto != null);
	}
}
