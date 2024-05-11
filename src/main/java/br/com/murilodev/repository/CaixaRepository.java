package br.com.murilodev.repository;

import br.com.murilodev.dto.DadosUsuarioLoginDTO;
import br.com.murilodev.entity.Caixa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class CaixaRepository implements PanacheRepository<Caixa> {

    public boolean verificaUsuarioJaExistente(String usuario) {
        return find("usuario = ?1", usuario).stream().count() == 1;
    }

    public boolean loginWeb(DadosUsuarioLoginDTO dados){
        try {
            Caixa caixa = find("usuario = ?1 AND senha =?2", dados.getUsuario(), dados.getSenha()).singleResult();
            return caixa != null;
        }catch (NoResultException e){
            return false;
        }

    }
}
