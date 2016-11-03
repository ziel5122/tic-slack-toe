package ttt.game;

import slack.api.Channels;
import slack.api.Users;
import slack.data.Channel;
import slack.data.User;
import slack.data.UserList;
import ttt.messages.Info;

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
    private static HashMap<String, Integer[]> VALUES = new HashMap<String, Integer[]>();

    public static Response challenge(MultivaluedMap<String, String> payload) {
        String channel_id = payload.getFirst("channel_id");
        if (USERS.containsKey(channel_id)) {
            return Response.ok(Info.jsonWrap("ERROR: Game in progress." +
                    ".\nEnter /ttt to see the current state or /ttt help for a list of commands.")).build();
        }

        String challenger_id = payload.getFirst("user_id");
        String challenged_user = payload.getFirst("text");
        String challenged_id = Users.getUserId(challenged_user);
        if (challenged_id != null && validOpponent(challenged_id, channel_id)) {
            USERS.put(channel_id, new String[]{challenger_id, challenged_id});
            Board board = new Board();
            BOARDS.put(channel_id, board);
            VALUES.put(channel_id, new Integer[]{1, -1});
            String response_string = "NEW GAME\n";
            response_string += board;
            response_string += "\n" + payload.getFirst("user_name") + " will go first! (" + Board.getLetter(1) + ")";
            return Response.ok(Info.jsonWrap(response_string)).build();
        }

        return Response.ok(Info.jsonWrap("ERROR: Invalid user.\n" +
            "Enter @ for a list of users.")).build();
    }

    public static Response concede(MultivaluedMap<String, String> payload) {
        String channel_id = payload.getFirst("channel_id");
        String[] user_ids = USERS.get(channel_id);

        if (user_ids == null) {
            return Response.ok().build();
        }

        String user_id = payload.getFirst("user_id");
        String winner;
        if (user_ids[0].equalsIgnoreCase(user_id)) {
            winner = user_ids[1];
        } else if (user_ids[1].equalsIgnoreCase(user_id)) {
            winner = user_ids[0];
        } else {
            return Response.ok().build();
        }

        Board board = BOARDS.get(channel_id);

        endGame(channel_id);

        return Response.ok(Info.wrapForfeit(board.toString(), winner)).build();
    }

    public static Response displayCurrentState(MultivaluedMap<String, String> payload) {
        String channel_id = payload.getFirst("channel_id");
        String[] user_ids = USERS.get(channel_id);
        Integer[] values = VALUES.get(channel_id);

        if (user_ids == null) {
            return Response.ok(Info.jsonWrap("No active board in this channel.\n"
                    + "Enter /ttt help for a list of commands.")).build();
        }

        Board board = BOARDS.get(channel_id);

        return Response.ok(Info.wrapBoard(board.toString(), user_ids, values[0]).toString()).build();
    }

    public static void endGame(String channel_id) {
        BOARDS.remove(channel_id);
        USERS.remove(channel_id);
        VALUES.remove(channel_id);
    }

    public static Response move(MultivaluedMap<String, String> payload) {
        String channel_id = payload.getFirst("channel_id");
        Board board = BOARDS.get(channel_id);

        if (board == null) {
            return Response.ok(Info.jsonWrap("No active board in this channel.\n"
                    + "Enter /ttt help for a list of commands.")).build();
        }

        String user_id = payload.getFirst("user_id");
        int value = VALUES.get(channel_id)[0];

        Response response;
        if(board.move(payload.getFirst("text"), value)) {
            if ((response = board.checkEnd(user_id, value)) != null) {
                endGame(channel_id);
                return response;
            }
            nextTurn(channel_id);
            return displayCurrentState(payload);
        }

        return Response.ok(Info.jsonWrap("Invalid move")).build();
    }

    private static void nextTurn(String channel_id) {
        String[] user_ids = USERS.get(channel_id);
        String temp = user_ids[0];
        user_ids[0] = user_ids[1];
        user_ids[1] = temp;
        USERS.put(channel_id, user_ids);

        Integer[] values = VALUES.get(channel_id);
        int temp_num = values[0];
        values[0] = values[1];
        values[1] = temp_num;
        VALUES.put(channel_id, values);
    }

    // check to make sure user belongs to team and is not same as challenger
    public static boolean validOpponent(String challenged_id, String channel_id) {
        List<String> user_ids = Channels.getUserIds(channel_id);

        if (Users.getUserInfo(challenged_id).isValid()) {
            for (String user_id : user_ids) {
                if (user_id.equals(challenged_id))
                    return true;
            }
        }

        return false;
    }
}
