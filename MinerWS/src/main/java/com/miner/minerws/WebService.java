/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miner.minerws;

import com.miner.minerws.miners.minerj48.MinerJ48;
import com.miner.minerws.model.Node;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Lucas Pereira
 */
@Path("service")
public class WebService {

    @GET
    @Path("/minerJ48/{file}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response minerJ48(@PathParam("file") String file) {
        Node node = new MinerJ48().minerJ48(file);
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(node).build();
    }

    @GET
    @Path("/minerJ48/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response minerJ48() {
        Node node = new MinerJ48().minerJ48(null);
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(node).build();
    }
}
