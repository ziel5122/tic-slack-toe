package ttt.messages;

import slack.api.Users;
import ttt.game.Board;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class Info {

    public static final JsonObject HELP = Json.createObjectBuilder()
            .add("response_type", "in_channel")
            .add("text", "The following are commands for tic-Slack-toe")
            .add("attachments", Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                    .add("title", "Current state of board")
                    .add("text", "/ttt")
                    .add("color", "good"))
                .add(Json.createObjectBuilder()
                    .add("title", "Challenge another user to a game")
                    .add("text", "/ttt @<username> (e.g. /ttt @austin)")
                    .add("color", "warning"))
                .add(Json.createObjectBuilder()
                    .add("title", "Make a move")
                    .add("text", "/ttt <row><column> (e.g. /ttt 22 for middle square)")
                    .add("color", "danger"))
                .add(Json.createObjectBuilder()
                    .add("title", "Concede")
                    .add("text", "/ttt gg")
                    .add("color", "#439FE0"))
            ).build();

    public static String jsonWrap(String string) {
        return Json.createObjectBuilder()
                .add("response_type", "in_channel")
                .add("text", string).build().toString();
    }

    public static Response scratch(Board board) {
        String response_string = "Game ends in a scratch!\n";
        response_string += "Final board state:\n";
        response_string += board.toString();

        return Response.ok(jsonWrap(response_string)).build();
    }

    public static Response win(String user_id, Board board) {
        String user_name = Users.getUserName(user_id);

        String response_string = user_name + " wins!\n";
        response_string += "Final board state:\n";
        response_string += board.toString();

        return Response.ok(jsonWrap(response_string)).build();
    }

    public static String wrapBoard(String board, String[] users, int value) {
        String user_name1 = Users.getUserName(users[0]);
        String user_name2 = Users.getUserName(users[1]);

        return jsonWrap("Current Board State:\n" +
                user_name1 + " vs. " + user_name2 + "\n" +
                "Next move: " + user_name1 + " (" + Board.getLetter(value) + ") \n" +
                board.toString());
    }

    public static String wrapForfeit(String board, String winner) {
        String user_name = Users.getUserName(winner);

        return jsonWrap(user_name + " wins! (forfeit)\n" +
                        "Last state of board:\n" +
                        board.toString());
    }
}
