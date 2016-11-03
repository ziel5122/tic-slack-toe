package slack.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelInfo {

    private boolean ok;

    private Channel channel;

    private String error;

    public Channel getChannel() { return channel; }

    public String getError() { return error; }

    public String getId() { return channel.getId(); }

    public List<String> getMembers() { return channel.getMembers(); }

    public String getName() { return channel.getName(); }

    public boolean getOk() { return ok; }

}
