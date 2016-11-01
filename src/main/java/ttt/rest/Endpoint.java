package ttt.rest;

import slack.data.Payload;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.Buffer;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response.Status;

/**
 * All messages involving /ttt come to this endpoint
 */
@Path("/")
public class Endpoint {

    private static final String TOKEN_FILE_PATH = "ttt-token.txt";

    private BufferedReader br;
    private URI response_uri;

    @POST
    @Path("/ttt")
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces("application/json")
    public Response processPost(Payload payload) { /*
            @QueryParam("token") String token,  //verify from correct Slack team
            @QueryParam("team_id") String team_id,
            @QueryParam("team_domain") String team_domain,
            @QueryParam("channel_id") String channel_id,
            @QueryParam("channel_name") String channel_name,
            @QueryParam("user_id") String user_id,
            @QueryParam("user_name") String user_name,
            @QueryParam("command") String command,  //e.g. /ttt
            @QueryParam("text") String text,    //argument to slash call (command)
            @QueryParam("response_url") String response_url
            ) {*/
        return Response.ok("everything is fine " + payload.getToken()).build();
        /*
        try {
            response_uri = new URI(response_url);

            br = new BufferedReader(new FileReader(TOKEN_FILE_PATH));
            String TOKEN = br.readLine();
            br.close();

            if (!TOKEN.equals(token)) { //invalid Slack token
                return Response.status(Status.UNAUTHORIZED)
                        .location(response_uri)
                        .build();
            }

            return Response.ok("everything is fine").build();
        } catch (FileNotFoundException e) { //token file does not exist

        } catch (IOException e) {   //token file is empty
            return Response.status(Status.UNAUTHORIZED)
                    .location(response_uri)
                    .build();
        } catch(URISyntaxException e) { //response url syntax is invalid
            return null;
        }

        return null;
        */
    }
}