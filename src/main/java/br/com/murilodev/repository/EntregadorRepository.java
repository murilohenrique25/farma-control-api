package br.com.murilodev.repository;

import br.com.murilodev.entity.Entregador;
import br.com.murilodev.rest.common.PasswordUtils;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import org.mindrot.jbcrypt.BCrypt;

@ApplicationScoped
public class EntregadorRepository implements PanacheRepository<Entregador> {

    public Entregador logarFilter(String userId) {
        return find("usuario = ?1", userId).singleResult();
    }

    public boolean verificaUsuarioJaExistente(String usuario) {
        return find("usuario = ?1", usuario).stream().count() == 1;
    }

    public Long findEntregadorByUsuario(String userId) {
        return find("usuario = ?1", userId).singleResult().getIdEntregador();
    }

    public boolean logarApp(String userId, String password) {
        String sql = "SELECT COUNT(*) FROM entregador WHERE usuario = :userId";
        Number count = (Number) getEntityManager().createNativeQuery(sql)
                .setParameter("userId", userId)
                .getSingleResult();

        if (count.intValue() == 0) {
            return false;
        }

        sql = "SELECT senha FROM entregador WHERE usuario = :userId";
        String hashedPassword = (String) getEntityManager().createNativeQuery(sql)
                .setParameter("userId", userId)
                .getSingleResult();

        PasswordUtils.verifyPassword(password, hashedPassword);
        return BCrypt.checkpw(password, hashedPassword);
    }

    public int quantidadeEntregasFinalizadasMes(String userId) {

        try {
            String sql = "SELECT COUNT(*) AS quantidade_entregas " +
                    "FROM entrega JOIN entregador ON entrega.id_entregador = entregador.id_entregador " +
                    "WHERE EXTRACT(MONTH FROM data_finalizacao_entrega) = EXTRACT(MONTH FROM CURRENT_DATE) " +
                    "AND entregador.usuario = :usuario";
            Long valor = (Long) getEntityManager().createNativeQuery(sql).setParameter("usuario", userId).getSingleResult();
            return valor.intValue();
        } catch (NoResultException e){
            return 0;
        }

    }
}
