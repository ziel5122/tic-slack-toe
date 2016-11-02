package ttt.rest;

import slack.data.User;
import slack.data.UserList;
import ttt.game.Core;
import ttt.messages.Info;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

/**
 * All messages involving /ttt come to this endpoint
 */
@Path("/")
public class Endpoint {

    private String API_TEST_TOKEN;
    private String TOKEN;

    private static final String API_TOKEN_PATH = "api-test-token.txt";
    private static final String USER_INFO_URL = "https://slack.com/api/users.info";
    private static final String TOKEN_FILE_PATH = "ttt-token.txt";

    private BufferedReader br;

    private HashSet<String> channels_with_ganme;

    @POST
    @Path("/ttt")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response processPost(
            @FormParam("channel_id") String channel_id,
            @FormParam("team_domain") String team_domain,
            @FormParam("text") String text,
            @FormParam("token") String token,
            @FormParam("user_id") String user_id) {

        // authorize post
        if (!TOKEN.equals(System.getProperty("TTT_TOKEN"))) {   //post from invalid sender
            return Response.status(Status.UNAUTHORIZED).build();
        }

        // check if help
        if (text.equalsIgnoreCase("help")) {    //show user list of commands
            return Response.ok(Info.HELP.toString()).build();
        }

        // check if "text" is valid username before searching for it
        if (text.matches("@[A-Za-z0-9]+")) {
            return Core.challenge(user_id, text.substring(1), channel_id);
        }

        return Response.ok("two conditionals not triggered " + team_domain).build();
/*
            Client client = ClientBuilder.newClient();
            UserList entity = client.target("https://slack.com/api/users.list")
                    .queryParam("token", API_TEST_TOKEN)
                    .request(MediaType.APPLICATION_JSON)
                    .get(UserList.class);
        */
    }
}