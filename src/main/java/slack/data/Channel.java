package slack.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {

    private List<String> members;

    private String id;
    private String name;

    public String getId() { return id; }

    public List<String> getMembers() { return members; }

    public String getName() { return name; }
}
