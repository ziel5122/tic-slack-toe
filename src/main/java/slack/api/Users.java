package slack.api;

import slack.data.User;
import slack.data.UserInfo;
import slack.data.UserList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class Users {

    public static UserInfo getUserInfo(String user_id) {
        Client client = ClientBuilder.newClient();
        UserInfo user = client.target("https://slack.com/api/users.info")
                .queryParam("token", System.getProperty("API_TEST_TOKEN"))
                .queryParam("user", user_id)
                .request(MediaType.APPLICATION_JSON)
                .get(UserInfo.class);

        return user;
    }

    public static List<User> getUsers() {
        Client client = ClientBuilder.newClient();
        UserList user_list = client.target("https://slack.com/api/users.list")
                .queryParam("token", System.getProperty("API_TEST_TOKEN"))
                .request(MediaType.APPLICATION_JSON)
                .get(UserList.class);

        return user_list.getMembers();
    }

    public static String getUserId(String username) {
        List<User> users = getUsers();

        for (User user : users) {
            if (user.getName().equals(username))
                return user.getId();
        }

        return null;
    }

    public static String getUserName(String user_id) {
        Client client = ClientBuilder.newClient();
        UserInfo user = client.target("https://slack.com/api/users.info")
                .queryParam("token", System.getProperty("API_TEST_TOKEN"))
                .queryParam("user", user_id)
                .request(MediaType.APPLICATION_JSON)
                .get(UserInfo.class);

        return user.getName();
    }


}
