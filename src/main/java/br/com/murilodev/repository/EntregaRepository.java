package br.com.murilodev.repository;

import br.com.murilodev.dto.DadosEntregaDTO;
import br.com.murilodev.entity.Entrega;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Tuple;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EntregaRepository implements PanacheRepository<Entrega> {

    public List<Entrega> buscarEntregasPorId(List<Long> dados) {
        PanacheQuery<Entrega> entregas = find("idEntrega IN ?1", dados);
        return entregas.list();
    }

    public List<DadosEntregaDTO> buscarEntregasDisponiveis() {
        String sql = "SELECT id_entrega, data_cadastro_entrega, data_assinatura_entrega, data_selecao_entrega, " +
                "       data_finalizacao_entrega, id_caixa, latitude, longitude, " +
                "       valor_total, valor_receber, troco, cupom_orcamento, tipo_entrega, forma_pagamento " +
                "FROM entrega WHERE id_entregador IS NULL";

        return parseEntregasDisponiveis(getEntityManager().createNativeQuery(sql, Tuple.class).getResultList());
    }

    private List<DadosEntregaDTO> parseEntregasDisponiveis(List<?> resultList) {
        List<DadosEntregaDTO> entregaDTOS = new ArrayList<>();

        for(Object object : resultList){
            DadosEntregaDTO dados = new DadosEntregaDTO();
            Tuple tuple = (Tuple) object;
            dados.setIdEntrega((Long) tuple.get("id_entrega"));
            dados.setTipoEntrega((String) tuple.get("tipo_entrega"));
            dados.setCupomOrcamento((String) tuple.get("cupom_orcamento"));
            dados.setFormaPagamento((String) tuple.get("forma_pagamento"));
            dados.setIdCaixa((Long) tuple.get("id_caixa"));
            dados.setTroco((Double) tuple.get("troco"));
            dados.setValorReceber((Double) tuple.get("valor_receber"));
            dados.setValorTotal((Double) tuple.get("valor_total"));
            dados.setDataCadastroEntrega(((Timestamp) tuple.get("data_cadastro_entrega")).toLocalDateTime());
            entregaDTOS.add(dados);
        }
    return entregaDTOS;
    }

    public List<DadosEntregaDTO> buscarEntregasByIdEntregador(Long idEntregador) {
        String sql = "SELECT id_entrega, data_cadastro_entrega, data_assinatura_entrega, data_selecao_entrega, " +
                "       data_finalizacao_entrega, id_caixa, latitude, longitude, id_entregador, " +
                "       valor_total, valor_receber, troco, cupom_orcamento, tipo_entrega, forma_pagamento " +
                "FROM entrega WHERE id_entregador =:idEntregador" +
                " AND data_finalizacao_entrega IS NULL";

        return parseEntregasEntregador(getEntityManager()
                .createNativeQuery(sql, Tuple.class)
                .setParameter("idEntregador", idEntregador)
                .getResultList());
    }

    private List<DadosEntregaDTO> parseEntregasEntregador(List<?> resultList) {
        List<DadosEntregaDTO> entregaDTOS = new ArrayList<>();

        for(Object object : resultList){
            DadosEntregaDTO dados = new DadosEntregaDTO();
            Tuple tuple = (Tuple) object;
            dados.setIdEntrega((Long) tuple.get("id_entrega"));
            dados.setIdEntregador((Long) tuple.get("id_entregador"));
            dados.setTipoEntrega((String) tuple.get("tipo_entrega"));
            dados.setCupomOrcamento((String) tuple.get("cupom_orcamento"));
            dados.setFormaPagamento((String) tuple.get("forma_pagamento"));
            dados.setIdCaixa((Long) tuple.get("id_caixa"));
            dados.setTroco((Double) tuple.get("troco"));
            dados.setValorReceber((Double) tuple.get("valor_receber"));
            dados.setValorTotal((Double) tuple.get("valor_total"));
            dados.setDataSelecaoEntrega(((Timestamp) tuple.get("data_selecao_entrega")).toLocalDateTime());
            dados.setDataCadastroEntrega(((Timestamp) tuple.get("data_cadastro_entrega")).toLocalDateTime());
            entregaDTOS.add(dados);
        }
        return entregaDTOS;
    }
}
