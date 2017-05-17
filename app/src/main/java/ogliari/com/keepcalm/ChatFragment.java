package ogliari.com.keepcalm;


import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    EditText edtMsg;
    View rootView;

    WSConnection ws = new WSConnection(new URI("ws://10.0.2.2:9080/KeepCalmServer/ws"));

    public ChatFragment() throws URISyntaxException {
        // Required empty public constructor

        ws.connect();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_chat, container, false);

        edtMsg = (EditText) rootView.findViewById(R.id.mensagem);

        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ws.close();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void addMyMessage() {
        ws.send(edtMsg.getText().toString());
    }

}
