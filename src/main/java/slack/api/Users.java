package slack.api;

import slack.data.User;
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

    public static String getUserId(String username) {
        UserList user_list = getUsers();

        for (User user : user_list.getMembers()) {
            if (user.getName().equals(username))
                return user.getId();
        }

        return null;
    }

    public static String getUserName(String user_id) {
        Client client = ClientBuilder.newClient();
        User user = client.target("https://slack.com/api/users.info")
                .queryParam("token", System.getProperty("API_TEST_TOKEN"))
                .request(MediaType.APPLICATION_JSON)
                .get(User.class);

        return user.getId();
    }
}
