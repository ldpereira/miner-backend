/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miner.minerws;

import com.miner.minerws.miners.minerj48.MinerJ48;
import com.miner.minerws.model.Database;
import com.miner.minerws.model.Group;
import com.miner.minerws.model.Message;
import com.miner.minerws.model.Node;
import com.miner.minerws.model.ReportMensagem;
import com.miner.minerws.model.ReportUserGroup;
import com.miner.minerws.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Lucas Pereira
 */
@Path("service")
public class WebService {

    @GET
    @Path("/minerJ48/{texto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response minerJ48(@PathParam("texto") String texto) {
        Node node = new MinerJ48().minerJ48();
        return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .entity(node).build();
        /*} else {
            return Response.status(400)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .entity("Usuário ou senha inválidos!").build();
        }*/
    }

    @GET
    @Path("/login/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@PathParam("username") String username, @PathParam("password") String password) {
        User user = Database.getInstance().getUser(username, password);
        if (user != null) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .entity(user).build();
        } else {
            return Response.status(400)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .entity("Usuário ou senha inválidos!").build();
        }
    }

    @GET
    @Path("/postUser/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response singup(@PathParam("username") String username, @PathParam("password") String password) {
        try {
            User user = Database.getInstance().createUser(username, password, "");
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .entity(user).build();
        } catch (IllegalArgumentException e) {
            return Response.status(400)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        List<User> list = new ArrayList<User>(Database.getInstance().getUsers().values());
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(new GenericEntity<List<User>>(list) {
                }).build();
    }

    @GET
    @Path("/getGroups")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroups() {
        List<Group> list = new ArrayList<Group>(Database.getInstance().getGroups().values());
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(new GenericEntity<List<Group>>(list) {
                }).build();
    }

    @GET
    @Path("/getMyGroups/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroups(@PathParam("username") String username) {
        List<Group> list = new ArrayList<Group>(Database.getInstance().getGroupsUser(username).values());
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(new GenericEntity<List<Group>>(list) {
                }).build();
    }

    @GET
    @Path("/postGroup/{name}/{username}/{description}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postGroup(@PathParam("name") String title, @PathParam("username") String username, @PathParam("description") String description) {
        int idGroup = Database.getInstance().addGroup(title, username, description);

        if (idGroup > 0) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .entity(Database.getInstance().getGroups().get(idGroup)).build();
        }
        return Response.status(400)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity("Não foi possível criar o grupo").build();
    }

    @GET
    @Path("/deleteGroup/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteGroup(@PathParam("id") String id) {
        if (Database.getInstance().getGroups().remove(Integer.valueOf(id)) != null) {

            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .entity("Grupo excluído com sucesso").build();
        } else {
            return Response.status(400)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .entity("Grupo inexistente").build();
        }
    }

    /**
     * Recebe as mensagens.
     *
     * @param group ID do grupo.
     * @param username Nome do usuário.
     * @param message Conteúdo da mensagem.
     * @return boolean indicando sucesso no envio da mensagem.
     */
    @GET
    @Path("/sendMessage/{group}/{username}/{message}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendMessage(@PathParam("group") String group, @PathParam("username") String username, @PathParam("message") String message) {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(Database.getInstance().sendMessage(Integer.valueOf(group), username, message)).build();
    }

    /**
     * Obtém as mensagens no banco de dados.
     *
     * @param group ID do grupo.
     * @return Mensagens do grupo.
     */
    @GET
    @Path("/getMessages/{group}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessages(@PathParam("group") String group) {
        List<Message> list = Database.getInstance().getMessages(Integer.parseInt(group));
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(new GenericEntity<List<Message>>(list) {
                }).build();
    }

    @GET
    @Path("/getUsuariosGrupos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuariosGrupos() {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(new GenericEntity<List<ReportUserGroup>>(Database.getInstance().getUsuariosGrupos()) {
                }).build();
    }

    @GET
    @Path("/getMensagensGrupos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMensagensGrupos() {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(new GenericEntity<List<ReportMensagem>>(Database.getInstance().getMensagensGrupos()) {
                }).build();
    }

    @GET
    @Path("/getMensagensUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMensagensUsuarios() {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(new GenericEntity<List<ReportMensagem>>(Database.getInstance().getMensagensUsuarios()) {
                }).build();
    }
}
