package br.com.cotiinformatica.controllers;

import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuariosControllersTest {

	
	@BeforeEach
	public void setUp() {
		
	}
	
	@Test
	@Order(1)
	@DisplayName("Deve executar um post [/api/v1/usuarios/criar] com retorno 201([CREATED]")
	public void testPostCriarUsuario_Sucesso() {
		fail("Não Implementado");
	}
	
	@Test
	@Order(2)
	@DisplayName("Deve executar um post [/api/v1/usuarios/criar] com retorno 400([BADREQUEST]")
	public void testPostCriarUsuario_RequisiçãoInvalida() {
		fail("Não Implementado");
	}
	
	@Test
	@Order(3)
	@DisplayName("Deve executar um post [/api/v1/usuarios/criar] com retorno 422([UNPROCESSABLE ENTITY]")
	public void testPostCriarUsuario_EntidadeInvalida() {
		fail("Não Implementado");
	}
	
	@Test
	@Order(4)
	@DisplayName("Deve executar um post [/api/v1/usuarios/criar] com retorno 200([OK]")
	public void testPostAutenticarUsuario_Sucesso() {
		fail("Não Implementado");
	}
	
	@Test
	@Order(5)
	@DisplayName("Deve executar um post [/api/v1/usuarios/criar] com retorno 400([BADREQUEST]")
	public void testPostAutenticarUsuario_RequisicaoInvalida() {
		fail("Não Implementado");
	}
	
	
	@Test
	@Order(6)
	@DisplayName("Deve executar um post [/api/v1/usuarios/criar] com retorno 401([UNAUTHORIZED]")
	public void testPostAutenticarUsuario_AcessoNaoAutorizado() {
		fail("Não Implementado");
	}
}
