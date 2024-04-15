package br.com.murilodev.rest.app;

import br.com.murilodev.dto.DadosEntregaDTO;
import br.com.murilodev.rest.common.authentication.Protected;
import br.com.murilodev.services.EntregaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("app/v1/entrega")
public class EntregaEndpoint {

    @Inject
    EntregaService entregaService;

    @PUT
    @Path("/selecionar")
    @Protected
    public Response selecionarEntrega(List<DadosEntregaDTO> entregaDTOS){
        entregaService.selecionarAtribuirAlterarEntrega(entregaDTOS);
        return Response.ok().build();
    }

    @PUT
    @Path("/finalizar")
    @Protected
    public Response finalizarEntrega(DadosEntregaDTO entregaDTOS, @QueryParam("latitude") String latitude, @QueryParam("longitude") String longitude){
        entregaService.finalizarEntrega(entregaDTOS, latitude, longitude);
        return Response.ok().build();
    }

    @GET
    @Protected
    public Response entregasDisponiveis(){
        return Response.ok(entregaService.entregasDisponiveis()).build();
    }


    @GET
    @Path("/entregador")
    @Protected
    public Response entregasByIdEntregador(){
        return Response.ok(entregaService.entregasByIdEntregador()).build();
    }
}
