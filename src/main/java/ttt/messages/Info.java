package ttt.messages;

import javax.json.Json;
import javax.json.JsonObject;

public class Info {

    public static final JsonObject HELP = Json.createObjectBuilder()
            .add("text", "The following are commands for tic-Slack-toe")
            .add("attachments", Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                    .add("title", "Current state of board")
                    .add("text", "/ttt"))
                .add(Json.createObjectBuilder()
                    .add("title", "Challenge another user to a game")
                    .add("text", "/ttt @<username> (e.g. /ttt @austin)"))
                .add(Json.createObjectBuilder()
                    .add("title", "Make a move")
                    .add("text", "/ttt <row><column> (e.g. /ttt 22 for middle square)"))
                .add(Json.createObjectBuilder()
                    .add("title", "Concede")
                    .add("text", "/ttt gg"))
            ).build();
}
