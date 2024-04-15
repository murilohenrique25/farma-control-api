package br.com.murilodev.services;

import br.com.murilodev.dto.DadosCaixaEntregadorDTO;
import br.com.murilodev.dto.DadosUsuarioLogadoDTO;
import br.com.murilodev.entity.Entregador;
import br.com.murilodev.repository.EntregadorRepository;
import br.com.murilodev.rest.common.PasswordUtils;
import br.com.murilodev.rest.common.exception.ServiceException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Transactional
@ApplicationScoped
public class EntregadorService {

    @Inject
    EntregadorRepository entregadorRepository;

    public void salvar(DadosCaixaEntregadorDTO caixaDTO) throws ServiceException {
        if(entregadorRepository.verificaUsuarioJaExistente(caixaDTO.getUsuario())){
            throw new ServiceException("Já existe um Entregador com usuário ", caixaDTO.getUsuario());
        }
        Entregador entregador = new Entregador();
        entregador.setNome(caixaDTO.getNome());
        entregador.setSenha(PasswordUtils.hashPassword(caixaDTO.getSenha()));
        entregador.setUsuario(caixaDTO.getUsuario());
        entregador.setAtivo(true);
        entregadorRepository.persist(entregador);
    }

    public Response atualizar(Long idCaixa, DadosCaixaEntregadorDTO caixaDTO){
        Entregador entregador = entregadorRepository.findById(idCaixa);
        if(entregador != null){
            entregador.setNome(caixaDTO.getNome());
            entregador.setSenha(caixaDTO.getSenha());
            entregador.setUsuario(caixaDTO.getUsuario());
            entregador.setAtivo(entregador.isAtivo());
            return Response.status(200).build();
        }
        return Response.noContent().build();
    }

    public Response listarTodos() {
        List<Entregador> listEntregador = entregadorRepository.findAll().list();
        return Response.ok(listEntregador).build();
    }


    public Response remover(Long idCaixa){
        Entregador entregador = entregadorRepository.findById(idCaixa);
        if(entregador != null){
            entregadorRepository.delete(entregador);
            return Response.status(200).build();
        }
        return Response.noContent().build();
    }

    public Entregador logarFilter(String userId) {
        return entregadorRepository.logarFilter(userId);
    }

    public DadosUsuarioLogadoDTO logarApp(String userId, String password) throws ServiceException {
        boolean logado = entregadorRepository.logarApp(userId, password);
        if(!logado){
            throw new ServiceException("Erro ao realizar login, verifique os dados");
        }
        DadosUsuarioLogadoDTO dados = new DadosUsuarioLogadoDTO();
        dados.setUsuario(userId);
        dados.setQuantidadeEntregas(entregadorRepository.quantidadeEntregasFinalizadasMes(userId));
        return dados;
    }
}
