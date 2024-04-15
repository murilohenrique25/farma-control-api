package br.com.murilodev.services;

import br.com.murilodev.dto.DadosCaixaEntregadorDTO;
import br.com.murilodev.entity.Caixa;
import br.com.murilodev.repository.CaixaRepository;
import br.com.murilodev.rest.common.PasswordUtils;
import br.com.murilodev.rest.common.exception.ServiceException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Transactional
@ApplicationScoped
public class CaixaService {

    @Inject
    CaixaRepository caixaRepository;

    public void salvar(DadosCaixaEntregadorDTO caixaDTO) throws ServiceException {
        if(caixaRepository.verificaUsuarioJaExistente(caixaDTO.getUsuario())) {
            throw new ServiceException("Já existe Caixa com o usuário informado: ", caixaDTO.getUsuario());
        }

        Caixa caixa = new Caixa();
        caixa.setNome(caixaDTO.getNome());
        caixa.setSenha(PasswordUtils.hashPassword(caixaDTO.getSenha()));
        caixa.setUsuario(caixaDTO.getUsuario());
        caixa.setAtivo(true);
        caixaRepository.persist(caixa);
    }

    public Response atualizar(Long idCaixa, DadosCaixaEntregadorDTO caixaDTO){
        Caixa caixa = caixaRepository.findById(idCaixa);
        if(caixa != null){
            caixa.setNome(caixaDTO.getNome());
            caixa.setSenha(caixaDTO.getSenha());
            caixa.setUsuario(caixaDTO.getUsuario());
            caixa.setAtivo(caixa.isAtivo());
            return Response.status(200).build();
        }
        return Response.noContent().build();
    }

    public Response listarTodos() {
        List<Caixa> listCaixas = caixaRepository.findAll().list();
        return Response.ok(listCaixas).build();
    }


    public Response remover(Long idCaixa){
        Caixa caixa = caixaRepository.findById(idCaixa);
        if(caixa != null){
            caixaRepository.delete(caixa);
            return Response.status(200).build();
        }
        return Response.noContent().build();
    }
}
