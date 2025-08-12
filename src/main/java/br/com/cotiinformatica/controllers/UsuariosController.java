package br.com.cotiinformatica.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.dtos.AutenticarUsuarioResponse;
import br.com.cotiinformatica.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.dtos.CriarUsuarioResponse;
import br.com.cotiinformatica.services.UsuarioServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuariosController {
	
	private final UsuarioServices usuarioService;
	
	@PostMapping("criar")
	public ResponseEntity<CriarUsuarioResponse> criar(@RequestBody @Valid CriarUsuarioRequest request) {
		var response = usuarioService.criarUsuario(request);
		return ResponseEntity.status(201).body(response);
	}
	@PostMapping("autenticar")
	public ResponseEntity<AutenticarUsuarioResponse> autenticar(@RequestBody @Valid AutenticarUsuarioRequest request) {
		var response = usuarioService.autenticarUsuario(request);
		return ResponseEntity.ok().body(response);
	}


}
