package ogliari.com.keepcalm;

import android.util.Log;


import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * Created by guilhermeogliari on 16/05/17.
 */

public class WSConnection extends WebSocketClient {

    Mensagem messagens = Mensagem.getInstance();

    public WSConnection(URI uri) {
        super(uri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        Log.d("Websocket", "Opened");
    }

    @Override
    public void onMessage(String s) {
        messagens.setMensagem(s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        Log.d("Websocket", "Closed");
    }

    @Override
    public void onError(Exception e) {

    }

}
