package com.swetha;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import java.io.IOException;

/**
 * Base application class for setting up NanoHTTPD server
 
 */
public class SwethaServer extends NanoHTTPD {

    private JDBCConnection connection;

    public SwethaServer() throws IOException {
        super(8080);
        connection = new JDBCConnection();
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    }

    public static void main(String[] args) {
        try {

            // create new instance of server
            new SwethaServer();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }
    }

    @Override
    public Response serve(IHTTPSession session) {
        if (session.getMethod() == Method.GET) {
            return CurlInput.performGet(connection, session);
        } else if (session.getMethod() == Method.POST) {
            return CurlInput.performPost(connection, session);
        } else if (session.getMethod() == Method.PUT) {
            return CurlInput.performPost(connection, session);
        } else if (session.getMethod() == Method.DELETE) {
            return CurlInput.performDelete(connection, session);
        }

        return CurlInput.failedAttempt();
    }
}
