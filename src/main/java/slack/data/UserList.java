package slack.data;

import java.util.List;

public class UserList {

    private boolean ok;

    private int cache_ts;

    private List<User> members;

    public int getCache_ts() { return cache_ts; }

    public List<User> getMembers() { return members; }

    public boolean getOk() { return ok; }
}
