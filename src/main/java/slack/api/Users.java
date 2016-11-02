package slack.api;

import slack.data.UserList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class Users {

    public static UserList getUsers() {
        Client client = ClientBuilder.newClient();
        UserList user_list = client.target("https://slack.com/api/users.list")
                .queryParam("token", System.getProperty("API_TEST_TOKEN"))
                .request(MediaType.APPLICATION_JSON)
                .get(UserList.class);

        return user_list;
    }
}
