package ttt.rest;

import slack.data.Payload;
import slack.data.User;
import slack.data.UserList;
import ttt.game.Core;
import ttt.messages.Info;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
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

    @POST
    @Path("/ttt")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response processPost(MultivaluedMap<String, String> payload) {
        // see https://api.slack.com/slash-commands for payload description

        // authorize post
        if (!payload.getFirst("token").equals(System.getProperty("TTT_TOKEN"))) {   //post from invalid sender
            return Response.status(Status.UNAUTHORIZED).build();
        }

        String text = payload.getFirst("text");

        // display current state if no arguments ("text" is empty)
        if (text.isEmpty()) {
            return Core.displayCurrentState(payload);
        }

        // check if help
        if (text.equalsIgnoreCase("help")) {    //show user list of commands
            return Response.ok(Info.HELP.toString()).build();
        }

        // check if "text" is valid username before searching for it
        if (text.matches("@[A-Za-z0-9]+")) {
            payload.putSingle("text", text.substring(1));
            return Core.challenge(payload);
        }

        // check for two digit number (desired move)
        if (text.matches("[0-9][0-9]")) {
            return Core.move(payload);
        }

        return Response.ok("two conditionals not triggered " + payload.getFirst("team_domain")).build();
    }
}