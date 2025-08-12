package br.com.cotiinformatica.services;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import br.com.cotiinformatica.components.JwtBearerComponent;
import br.com.cotiinformatica.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.dtos.AutenticarUsuarioResponse;
import br.com.cotiinformatica.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.dtos.CriarUsuarioResponse;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.exceptions.AcessoNegadoException;
import br.com.cotiinformatica.exceptions.EmailJaCadastradoException;
import br.com.cotiinformatica.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServices {
	
	private final UsuarioRepository usuarioRepository;
	
	private final JwtBearerComponent jwtBearerComponent;

	public CriarUsuarioResponse criarUsuario(CriarUsuarioRequest request) {
		
		if(usuarioRepository.existsByEmail(request.getEmail())) {
			throw new EmailJaCadastradoException(request.getEmail());
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
	
	public AutenticarUsuarioResponse autenticarUsuario(AutenticarUsuarioRequest request) {
		
		var usuario = usuarioRepository.findByEmailAndSenha(request.getEmail(), request.getSenha())
				.orElseThrow(() -> new AcessoNegadoException());
		
		var expiration = jwtBearerComponent.getExpiration();
		LocalDateTime dataHoraExpiracao = expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		
		var accessToken = jwtBearerComponent.getAccessToken(usuario.getId().toString(), "USER");
		
		//Retornando os dados do usuário
				var response = new AutenticarUsuarioResponse();
				response.setId(usuario.getId());
				response.setNome(usuario.getNome());
				response.setEmail(usuario.getEmail());
				response.setDataHoraAcesso(LocalDateTime.now());
				response.setDataHoraExpiracao(dataHoraExpiracao); 
				response.setAccessToken(accessToken); 
				
				return response;

		
	}
}
