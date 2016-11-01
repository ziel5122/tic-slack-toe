package ttt.rest;

import org.restlet.data.Form;
import org.restlet.resource.ClientResource;
import slack.data.Payload;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response.Status;

/**
 * All messages involving /ttt come to this endpoint
 */
@Path("/")
public class Endpoint {

    private String API_TEST_TOKEN;
    private String TOKEN;

    private static final String API_TOKEN_PATH = "api-test-token.txt";
    private static final String USER_INFO_URL = "https://slack.com/api/users.info"
    private static final String TOKEN_FILE_PATH = "ttt-token.txt";

    private BufferedReader br;

    private HashSet<String> channels_with_ganme;

    @POST
    @Path("/ttt")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response processPost(
            @FormParam("channel_id") String channel_id,
            @FormParam("text") String text,
            @FormParam("token") String token) {
        try {
            br = new BufferedReader(new FileReader(TOKEN_FILE_PATH));
            TOKEN = br.readLine();

            br = new BufferedReader(new FileReader(API_TOKEN_PATH));
            API_TEST_TOKEN = br.readLine();
            br.close();

            if (TOKEN.equals(token)) { //invalid Slack token
                return Response.ok("Token authorized from file").build();
            } else {
                return Response.status(Status.UNAUTHORIZED)
                        .build();
            }

        } catch (FileNotFoundException e) { //token file does not exist
            TOKEN = System.getProperty("TTT_TOKEN");
            if (!TOKEN.equals(token)) { //invalid Slack token
                return Response.status(Status.UNAUTHORIZED)
                        .build();
            }
            API_TEST_TOKEN = System.getProperty("API_TEST_TOKEN");
        } catch (IOException e) {   //token file is empty
            return Response.status(Status.UNAUTHORIZED)
                    .build();
        }

        if (text.charAt(0) == '@') {
            Form form = new Form();
            form.add("token", API_TEST_TOKEN);

            ClientResource resource = new ClientResource("https://slack.com/api/users.list");
            Response response = resource.post(form.getWebRepresentation());

            HttpURLConnection user_info = new H
        }
    }
}