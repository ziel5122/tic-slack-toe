package ttt.game;

import slack.api.Channels;
import slack.api.Users;
import slack.data.Channel;
import slack.data.User;
import slack.data.UserList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;

public class Core {

    private static HashMap<String, Board> BOARDS = new HashMap<String, Board>();
    private static HashMap<String, String[]> USERS = new HashMap<String, String[]>();

    public static Response challenge(MultivaluedMap<String, String> payload) {
        String channel_id = payload.getFirst("channel_id");
        if (USERS.containsKey(channel_id)) {
            return Response.ok("ERROR: Game in progress." +
                    ".\nEnter /ttt to see the current state or /ttt help for a list of commands.").build();
        }

        String challenger_id = payload.getFirst("user_id");
        String challenged_user = payload.getFirst("text");
        String challenged_id = Users.getUserId(challenged_user);
        if (challenged_id != null && validOpponent(challenged_id, channel_id)) {
            USERS.put(channel_id, new String[]{challenger_id, challenged_id});
            Board board = new Board();
            BOARDS.put(channel_id, board);
            String response_string = "NEW GAME\n";
            response_string += board;
            response_string += "\n" + payload.getFirst("user_name") + " will go first!";
            return Response.ok(response_string).build();
        }

        return Response.ok("ERROR: Invalid user.\n" +
            "Enter @ for a list of users.").build();
    }

    public static Response displayCurrentState(MultivaluedMap<String, String> payload) {
        String channel_id = payload.getFirst("channel_id");
        String response_string = "Next move: " + Users.getUserName(USERS.get(channel_id)[0]) + "\n";
        response_string += BOARDS.get(channel_id);
        return Response.ok(response_string).build();
    }

    public static Response move(MultivaluedMap<String, String> payload) {
        //make random move for testing
        if(BOARDS.get(payload.getFirst("channel_id")).move(payload.getFirst("text"))) {
            //nextTurn();
            return displayCurrentState(payload);
        }

        return Response.ok("Invalid square").build();
    }

    // check to make sure user belongs to team and is not same as challenger
    public static boolean validOpponent(String challenged_id, String channel_id) {
        List<String> user_ids = Channels.getUserIds(channel_id);

        for (String user_id : user_ids) {
            if (user_id.equals(challenged_id))
                return true;
        }

        return false;
    }

    public static Channel getChannelInfo(String channel_id) {
        return null;
    }
}
