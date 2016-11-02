package slack.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserList {

    private List<UserInfo> members;

    public List<UserInfo> getMembers() { return members; }
}
