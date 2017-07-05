package ogliari.com.keepcalm;

import android.util.Log;


import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by guilhermeogliari on 16/05/17.
 */

public class WSConnection extends WebSocketClient {

    ChatFragment chatFragment;

    public WSConnection() throws URISyntaxException {
        super(new URI("ws://10.0.2.2:9080/KeepCalmServer/ws"));
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        Log.d("Websocket", "Opened");
    }

    @Override
    public void onMessage(String s) {

    }

    @Override
    public void onClose(int i, String s, boolean b) {
        Log.d("Websocket", "Closed");
    }

    @Override
    public void onError(Exception e) {

    }

}
