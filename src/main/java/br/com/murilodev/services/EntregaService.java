package br.com.murilodev.services;

import br.com.murilodev.dto.DadosEntregaDTO;
import br.com.murilodev.entity.Caixa;
import br.com.murilodev.entity.Entrega;
import br.com.murilodev.entity.Entregador;
import br.com.murilodev.repository.CaixaRepository;
import br.com.murilodev.repository.EntregaRepository;
import br.com.murilodev.repository.EntregadorRepository;
import br.com.murilodev.rest.common.dto.UserIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Qualifier;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@ApplicationScoped
public class EntregaService {

    @Inject
    EntregaRepository entregaRepository;

    @Inject
    CaixaRepository caixaRepository;

    @Inject
    EntregadorRepository entregadorRepository;

    @Inject
    UserIdentity authenticatedUser;

    public void salvarEntrega(DadosEntregaDTO entregaDTO){
        Caixa caixa = caixaRepository.findById(entregaDTO.getIdCaixa());
        Entregador entregador = entregadorRepository.findById(entregaDTO.getIdEntregador());
        Entrega entrega = new Entrega();
        entrega.setTipoEntrega(entregaDTO.getTipoEntrega());
        entrega.setDataCadastroEntrega(LocalDateTime.now());
        entrega.setCupomOrcamento(entregaDTO.getCupomOrcamento());
        entrega.setFormaPagamento(entregaDTO.getFormaPagamento());
        entrega.setValorTotal(entregaDTO.getValorTotal());
        entrega.setTroco(entregaDTO.getTroco());
        entrega.setValorReceber(entregaDTO.getValorReceber());
        entrega.setObservacao(entregaDTO.getObservacao());
        entrega.setCaixa(caixa);
        entrega.setEntregador(entregador);
        entregaRepository.persist(entrega);
    }

    @Transactional
    public void selecionarAtribuirAlterarEntrega(List<DadosEntregaDTO> entregasDTO){
        Long idEntregador = entregadorRepository.findEntregadorByUsuario(authenticatedUser.getUserId());
        List<Entrega> entregas = entregaRepository.buscarEntregasPorId(entregasDTO.stream().map(DadosEntregaDTO::getIdEntrega).collect(Collectors.toList()));
        LocalDateTime dataAgora = LocalDateTime.now();
        Entregador entregador = entregadorRepository.findById(idEntregador);
        for(Entrega entrega : entregas){
            entrega.setDataSelecaoEntrega(dataAgora);
            entrega.setEntregador(entregador);
        }
    }

    public void assinarEntregaCaixa(List<DadosEntregaDTO> entregasDTO, Long idCaixa){
        List<Entrega> entregas = entregaRepository.buscarEntregasPorId(entregasDTO.stream().map(DadosEntregaDTO::getIdEntrega).collect(Collectors.toList()));
        LocalDateTime dataAgora = LocalDateTime.now();
        Caixa caixa = caixaRepository.findById(idCaixa);
        for(Entrega entrega : entregas){
            entrega.setDataAssinaturaEntrega(dataAgora);
            entrega.setCaixa(caixa);
        }
    }

    public void finalizarEntrega(DadosEntregaDTO entregaDTO, String latitude, String longitude) {
        Entrega entrega = entregaRepository.findById(entregaDTO.getIdEntrega());
        LocalDateTime dataAgora = LocalDateTime.now();
        entrega.setDataFinalizacaoEntrega(dataAgora);
        entrega.setLatitude(latitude);
        entrega.setLongitude(longitude);
    }

    public List<DadosEntregaDTO> entregasDisponiveis(){
        return entregaRepository.buscarEntregasDisponiveis();
    }

    public List<DadosEntregaDTO> entregasByIdEntregador(){
        Long idEntregador = entregadorRepository.findEntregadorByUsuario(authenticatedUser.getUserId());
        return entregaRepository.buscarEntregasByIdEntregador(idEntregador);
    }
}
