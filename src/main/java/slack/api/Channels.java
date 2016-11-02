package slack.api;

import slack.data.Channel;
import slack.data.UserList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class Channels {

    public static List<String> getUserIds(String channel_id) {
        Client client = ClientBuilder.newClient();
        Channel channel = client.target("https://slack.com/api/channels.info")
                .queryParam("token", System.getProperty("API_TEST_TOKEN"))
                .request(MediaType.APPLICATION_JSON)
                .get(Channel.class);

        return channel.getMembers();
    }
}
