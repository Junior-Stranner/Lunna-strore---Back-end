package br.com.judev.lunna.service;

import br.com.judev.lunna.dto.EnderecoDtoRequest;
import br.com.judev.lunna.dto.EnderecoDtoResponse;
import br.com.judev.lunna.entity.Endereco;
import br.com.judev.lunna.entity.Usuario;
import br.com.judev.lunna.mapper.EnderecoMapper;
import br.com.judev.lunna.repositories.EnderecoRepository;
import br.com.judev.lunna.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;

    public EnderecoDtoResponse salvarOuAtualizar(Long usuarioId, EnderecoDtoRequest dto) {

        Usuario usuario = usuarioRepository.findById(Math.toIntExact(usuarioId))
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Endereco endereco = usuario.getEndereco();

        if (endereco == null) {
            endereco = new Endereco();
            endereco.setUsuario(usuario);
        }

        if (dto.getRua() != null) endereco.setRua(dto.getRua());
        if (dto.getCidade() != null) endereco.setCidade(dto.getCidade());
        if (dto.getEstado() != null) endereco.setEstado(dto.getEstado());
        if (dto.getCep() != null) endereco.setCep(dto.getCep());
        if (dto.getPais() != null) endereco.setPais(dto.getPais());

        Endereco salvo = enderecoRepository.save(endereco);

        return EnderecoMapper.toDto(salvo);
    }
}
