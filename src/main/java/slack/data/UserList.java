package slack.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserList {

    private List<User> members;

    public List<User> getMembers() { return members; }
}
