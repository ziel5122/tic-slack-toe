package slack.data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Wraps the data coming from Slack
 */
@XmlRootElement
public class Payload {

    String token;
    /*String team_id;
    String team_domain;
    String channel_id;
    String channel_name;
    String user_id;
    String user_name;
    String command;
    String text;
    String response_url;*/

    public String getToken() { return token; }
}
