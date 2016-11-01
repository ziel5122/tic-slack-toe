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
    @Produces(MediaType.APPLICATION_JSON)
    public Response processPost(Payload payload) {
        try {
            response_uri = new URI(payload.getResponse_url());

            br = new BufferedReader(new FileReader(TOKEN_FILE_PATH));
            String TOKEN = br.readLine();
            br.close();

            if (!TOKEN.equals(payload.getToken())) { //invalid Slack token
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
    }
}