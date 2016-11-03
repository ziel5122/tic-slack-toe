package slack.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {

    private boolean ok;

    private String error;

    private User user;

    public boolean getDeleted() { return user.getDeleted(); }

    public String getError() { return error; }

    public String getId() { return user.getId(); }

    public boolean getIs_bot() { return user.getIs_bot(); }

    public boolean getIs_restricted() { return user.getIs_restricted(); }

    public boolean getIs_ultra_restricted() { return user.getIs_ultra_restricted(); }

    public String getName() { return user.getName(); }

    public boolean getOk() { return ok; }

    public User getUser() { return user; }

    public boolean isValid() { return user.isValid(); }
}
