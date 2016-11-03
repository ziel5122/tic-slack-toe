package ttt.messages;

import slack.api.Users;
import ttt.game.Board;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class Info {

    public static final JsonObject HELP = Json.createObjectBuilder()
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

    public static Response scratch(Board board) {
        String response_string = "Game ends in a scratch!\n";
        response_string += "Final board state:\n";
        response_string += board.toString();

        return Response.ok(response_string).build();
    }

    public static Response win(String user_id, Board board) {
        String user_name = Users.getUserName(user_id);

        String response_string = user_name + " wins!\n";
        response_string += "Final board state:\n";
        response_string += board.toString();

        return Response.ok(response_string).build();
    }

    public static String wrapBoard(String board, String[] users) {
        String user_name1 = Users.getUserName(users[0]);
        String user_name2 = Users.getUserName(users[1]);

        return "Current Board State:\n" +
                user_name1 + " vs. " + user_name2 + "\n" +
                "Next move: " + user_name1 + "\n" +
                board.toString();
    }

    public static JsonObject wrapForfeit(String board, String winner) {
        String user_name = Users.getUserName(winner);

        JsonObject jsonBoard = Json.createObjectBuilder()
                .add("text", user_name + " wins! (forfeit)\n" +
                        "Last state of board:\n")
                .add("text", board).build();

        return jsonBoard;
    }
}
