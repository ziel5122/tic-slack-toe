package ttt.game;

import slack.data.Channel;
import slack.data.User;
import slack.data.UserList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

public class Core {

    private static HashMap<String, String> TURN = new HashMap<String, String>();
    private static HashMap<String, Board> BOARDS = new HashMap<String, Board>();

    public static Response challenge(String challenger_id, String username, String channel_id) {
        if (TURN.containsKey(channel_id)) {
            return Response.ok("ERROR: Game in progress." +
                    ".\nEnter /ttt to see the current state or /ttt help for a list of commands.").build();
        }

        if (validOpponent(username)) {
            TURN.put(channel_id, challenger_id);
            Board board = new Board();
            BOARDS.put(channel_id, board);
            String response_string = "NEW GAME\n";
            response_string += board;
            response_string += "\n" + challenger_id + " will go first!";
            return Response.ok(response_string).build();
        }

        return Response.ok("ERROR: Invalid user.\n" +
            "Enter @ for a list of users.").build();
    }

    // check to make sure user belongs to team and is not same as challenger
    public static boolean validOpponent(String username) {
        return true;
    }

    public static Channel getChannelInfo(String channel_id) {
        return null;
    }
}
