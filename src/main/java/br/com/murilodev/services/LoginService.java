package br.com.murilodev.services;

import br.com.murilodev.dto.DadosUsuarioLoginDTO;
import br.com.murilodev.repository.CaixaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LoginService {

    @Inject
    CaixaRepository caixaRepository;

    public boolean loginWeb(DadosUsuarioLoginDTO dados){
        return caixaRepository.loginWeb(dados);
    }
}
