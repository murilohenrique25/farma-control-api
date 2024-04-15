package br.com.murilodev.rest;

import br.com.murilodev.dto.DadosEntregaDTO;
import br.com.murilodev.services.EntregaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("entrega")

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntregaEndpoint {

    @Inject
    EntregaService entregaService;

    @POST
    public Response salvarEntrega(DadosEntregaDTO dadosEntregaDTO){
        entregaService.salvarEntrega(dadosEntregaDTO);
        return Response.ok().build();
    }

    @PUT
    @Path("/assinar/{idCaixa}")
    public Response assinarEntregas(List<DadosEntregaDTO> entregaDTOList, @PathParam("idCaixa") Long idCaixa){
        entregaService.assinarEntregaCaixa(entregaDTOList, idCaixa);
        return Response.ok().build();
    }

    @PUT
    @Path("/atribuir")
    public Response selecionarEntrega(List<DadosEntregaDTO> entregaDTOS){
        entregaService.selecionarAtribuirAlterarEntrega(entregaDTOS);
        return Response.ok().build();
    }

}
