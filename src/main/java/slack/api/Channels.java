package slack.api;

import slack.data.Channel;
import slack.data.ChannelInfo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class Channels {

    public static List<String> getUserIds(String channel_id) {
        Client client = ClientBuilder.newClient();
        ChannelInfo channel = client.target("https://slack.com/api/channels.info")
                .queryParam("token", System.getProperty("API_TEST_TOKEN"))
                .queryParam("channel", channel_id)
                .request(MediaType.APPLICATION_JSON)
                .get(ChannelInfo.class);

        return channel.getMembers();
    }
}
