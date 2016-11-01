package slack.data;

/**
 * Wraps the data coming from Slack
 */
public class Payload {

    String channel_id;
    String channel_name;
    String command;
    String response_url;
    String team_domain;
    String team_id;
    String text;
    String token;
    String user_id;
    String user_name;

    public String getChannel_id() { return channel_id; }

    public String getChannel_name() { return channel_name; }

    public String getCommand() { return command; }

    public String getResponse_url() { return response_url; }

    public String getTeam_domain() { return team_domain; }

    public String getTeam_id() { return team_id; }

    public String getText() { return text; }

    public String getToken() { return token; }

    public String getUser_id() { return user_id; }

    public String getUser_name() { return user_name; }
}
