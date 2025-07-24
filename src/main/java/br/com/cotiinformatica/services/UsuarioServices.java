package br.com.cotiinformatica.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.cotiinformatica.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.dtos.CriarUsuarioResponse;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Service
public class UsuarioServices {
	
	private final UsuarioRepository usuarioRepository;
	
	public UsuarioServices(UsuarioRepository usuarioRepository){
		this.usuarioRepository = usuarioRepository;
	}

	public CriarUsuarioResponse criarUsuario(CriarUsuarioRequest request) {
		
		if(usuarioRepository.existsByEmail(request.getEmail())) {
			throw new IllegalArgumentException("O Email informado já está cadastrado, tente outro.");
		}
		
		var usuario = new Usuario();
		usuario.setNome(request.getNome());
		usuario.setEmail(request.getEmail());
		usuario.setSenha(request.getSenha());
		
		var usuarioCriado = usuarioRepository.save(usuario);
		
		
		//Retornando os dados da resposta
				var response = new CriarUsuarioResponse();
				response.setId(usuarioCriado.getId());
				response.setNome(usuarioCriado.getNome());
				response.setEmail(usuarioCriado.getEmail());
				response.setDataHoraCadastro(LocalDateTime.now());
				
	 return response;

	}
	
	public void autenticarUsuario() {
		
	}
}
