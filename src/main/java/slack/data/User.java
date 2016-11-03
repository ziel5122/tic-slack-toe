package slack.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private boolean deleted;
    private boolean is_bot;
    private boolean is_restricted;
    private boolean is_ultra_restricted;

    private String id;
    private String name;

    public boolean getDeleted() { return deleted; }

    public String getId() { return id; }

    public boolean getIs_bot() { return is_bot; }

    public boolean getIs_restricted() { return is_restricted; }

    public boolean getIs_ultra_restricted() { return is_ultra_restricted; }

    public String getName() { return name; }

    public boolean isValid() {
        return !deleted && !is_bot && !is_restricted && !is_ultra_restricted;
    }
}
