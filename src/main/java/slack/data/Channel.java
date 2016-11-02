package slack.data;

import java.util.List;

public class Channel {

    private boolean is_archived;
    private boolean is_channel;
    private boolean is_general;
    private boolean is_member;
    private boolean is_read_only;

    private double last_read;

    private int created;
    private int unread_count;
    private int unread_count_display;

    private List<String> members;

    private Object latest;
    private Object purpose;
    private Object topic;

    private String creator;
    private String id;
    private String name;

    public int getCreated() { return created; }

    public String getCreator() { return creator; }

    public String getId() { return id; }

    public boolean getIs_archived() { return is_archived; }

    public boolean getIs_channel() { return is_channel; }

    public boolean getIs_general() { return is_general; }

    public boolean getIs_member() { return is_member; }

    public boolean getIs_read_only() { return is_read_only; }

    public double getLast_read() { return last_read; }

    public Object getLatest() { return latest; }

    public List<String> getMembers() { return members; }

    public String getName() { return name; }

    public Object getPurpose() { return purpose; }

    public Object getTopic() { return topic; }

    public int getUnread_count() { return unread_count; }

    public int getUnread_count_display() { return unread_count_display; }
}
