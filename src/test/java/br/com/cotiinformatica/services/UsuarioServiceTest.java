package br.com.cotiinformatica.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.github.javafaker.Faker;

import br.com.cotiinformatica.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.UsuarioRepository;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioServiceTest {
	//Atributos que são mockados no teste
	private UsuarioRepository usuarioRepository;
	
	@BeforeEach
	public void setUp() {
		//Método para configurar e preparar os testes
		//Criando os MOCKS (simulações)
		usuarioRepository = mock(UsuarioRepository.class);
	}
	
	@Test
	@Order(1)
	@DisplayName("Deve criar um usuário com sucesso.")
	public void testCriarUsuarioComSucesso() {
		
		//Arrange
		var usuarioService = new UsuarioServices(usuarioRepository);
		
		var faker = new Faker();
		
		//Dados de entrada (requisição)
		var request = new CriarUsuarioRequest();
		request.setNome(faker.name().fullName());
		request.setEmail(faker.internet().emailAddress());
		request.setSenha(faker.internet().password());
		
		//Dados do usuário que será gravado no banco de dados
		var usuario = new Usuario();
		usuario.setId(UUID.randomUUID());
		usuario.setNome(request.getNome());
		usuario.setEmail(request.getEmail());
		usuario.setSenha(request.getSenha());
		
		//Definindo o comportamento esperado do repositório (mock)
		//Quando o email informado for verificado no banco entao ele não está cadastrado
		when(usuarioRepository.existsByEmail(request.getEmail())).thenReturn(false);
		//Qualquer usuário gravado no banco deve retornar os dados do objeto 'usuario'
		when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
		
		//Act (Ação)
		var response = usuarioService.criarUsuario(request);
		
		//Arrange (Verificações / Asserções)
		assertNotNull(response);
		
		//Verificando se os dados do usuário gravado no banco foram retornados
		assertEquals(usuario.getId(), (response).getId());
		assertEquals(usuario.getNome(), ( response).getNome());
		assertEquals(usuario.getEmail(), ( response).getEmail());		
	}
	
	@Test
	@Order(2)
	@DisplayName("Deve retornar erro quando tenta criar um usuário com email já existente.")
	public void testCriarUsuarioComEmailJaExistente() {
		
		var usuarioService = new UsuarioServices(usuarioRepository);
		
		//Gerando um usuário para teste
		var faker = new Faker();
		
		//Dados de entrada (requisição)
		var request = new CriarUsuarioRequest();
		request.setNome(faker.name().fullName());
		request.setEmail(faker.internet().emailAddress());
		request.setSenha(faker.internet().password());
		
		//Definindo o comportamento esperado do repositório (mock)
		//Quando o email informado for verificado ele já deverá estar cadastrado no banco
		when(usuarioRepository.existsByEmail(request.getEmail())).thenReturn(true);
		//Executando a criação do usuário e verificar se foi retornado uma exceção
				var exception = assertThrows(
						IllegalArgumentException.class, () -> {
							//Ação que deverá retornar a exceção
							usuarioService.criarUsuario(request);
						}
					);
				
				//Verificar a mensagem de erro obtida
				assertEquals("O Email informado já está cadastrado, tente outro.", exception.getMessage());
			}


	
	@Test
	@Order(3)
	@DisplayName("Deve autenticar um usuário com sucesso.")
	public void testAutenticarUsuarioComSucesso() {
		fail("Não implementado.");
	}
	
	@Test
	@Order(4)
	@DisplayName("Deve retornar acesso negado quando tenta autenticar um usuário inválido.")
	public void testAutenticarUsuarioComAcessoNegado() {
		fail("Não implementado.");
	}
}
