package br.com.murilodev.repository;

import br.com.murilodev.entity.Caixa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CaixaRepository implements PanacheRepository<Caixa> {

    public boolean verificaUsuarioJaExistente(String usuario) {
        return find("usuario = ?1", usuario).stream().count() == 1;
    }
}
