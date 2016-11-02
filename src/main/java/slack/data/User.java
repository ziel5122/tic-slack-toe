package slack.data;

import java.util.Map;

public class User {

    private boolean deleted;
    private boolean has_2fa;
    private boolean is_admin;
    private boolean is_bot;
    private boolean is_owner;
    private boolean is_primary_owner;
    private boolean is_restricted;
    private boolean is_ultra_restricted;

    private Map<String, Object> profile;

    private String color;
    private String id;
    private String name;
    private String real_name;
    private String status;
    private String team_id;
    private String tz;
    private String tz_label;
    private String tz_offset;

    public String getColor() { return color; }
    
    public boolean getDeleted() { return deleted; }

    public boolean getHas_2fa() { return has_2fa; }

    public String getId() { return id; }
    
    public boolean getIs_admin() { return is_admin; }

    public boolean getIs_bot() { return is_bot; }

    public boolean getIs_owner() { return is_owner; }
    
    public boolean getIs_primary_owner() { return is_primary_owner; }
    
    public boolean getIs_restricted() { return is_restricted; }
    
    public boolean getIs_ultra_restricted() { return is_ultra_restricted; }
    
    public String getName() { return name; }
    
    public Map<String, Object> getProfile() { return profile; }
    
    public String getReal_name() { return real_name; }

    public String getStatus() { return status; }

    public String getTeam_id() { return team_id; }

    public String getTz() { return tz; }

    public String getTz_label() { return tz_label; }

    public String getTz_offset() { return tz_offset; }
}
