package com.swetha;

import com.google.gson.Gson;
import com.swetha.domain.Console;
import fi.iki.elonen.NanoHTTPD;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static fi.iki.elonen.NanoHTTPD.MIME_PLAINTEXT;
import static fi.iki.elonen.NanoHTTPD.newFixedLengthResponse;


public final class CurlInput {

    private static final String NO_RESOURCE = "Does not exist";

    private CurlInput() {
    }

    public static NanoHTTPD.Response performGet(JDBCConnection connection, NanoHTTPD.IHTTPSession session) {
        String jsonResp = null;
        String param = getIndex(session.getUri());
        Gson gson = new Gson();

        if (param != null && !param.equals("")) {
            Console console = connection.getConsole(param);
            if (console == null) {
                return failedAttempt();
            }
            jsonResp = gson.toJson(console);
        } else {
            List<Console> consoles = connection.getConsoles();
            if (consoles.isEmpty()) {
                return failedAttempt();
            }
            jsonResp = gson.toJson(consoles);
        }

        return newFixedLengthResponse(jsonResp);
    }

    public static NanoHTTPD.Response performPost(JDBCConnection connection, NanoHTTPD.IHTTPSession session) {
        try {
            session.parseBody(new HashMap<>());
            String result = connection.addConsole(session.getQueryParameterString());
            return newFixedLengthResponse(result);
        } catch (IOException | NanoHTTPD.ResponseException e) {
            return failedAttempt();
        }
    }

    public static NanoHTTPD.Response performDelete(JDBCConnection connection, NanoHTTPD.IHTTPSession session) {
        String result = connection.deleteConsole(getIndex(session.getUri()));
        return newFixedLengthResponse(result);
    }

    public static NanoHTTPD.Response failedAttempt() {
        return newFixedLengthResponse(NanoHTTPD.Response.Status.NOT_FOUND, MIME_PLAINTEXT,
                NO_RESOURCE);
    }

    private static String getIndex(String param) {
        return param.replaceAll("[^0-9]", "");
    }
}
