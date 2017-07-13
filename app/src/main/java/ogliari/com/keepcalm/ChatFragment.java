package ogliari.com.keepcalm;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment{

    View rootView;
    RelativeLayout ll;
    URI uri = new URI("ws://keepcalmserver.mybluemix.net/KeepCalmServer/ws");
    WebSocketClient ws;
    int id = 1;

    float scale;
    int dp_6_5, dp_10, dp_minus_15, dp_16, dp_30, dp_40;

    public ChatFragment() throws URISyntaxException {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_chat, container, false);

        ll = (RelativeLayout) rootView.findViewById(R.id.list_mensagens);

        scale = getResources().getDisplayMetrics().density;
        dp_6_5 = (int) (6.5 * scale + 0.5f);
        dp_10 = (int) (10 * scale + 0.5f);
        dp_minus_15 = (int) (-15 * scale + 0.5f);
        dp_16 = (int) (16 * scale + 0.5f);
        dp_30 = (int) (30 * scale + 0.5f);
        dp_40 = (int) (40 * scale + 0.5f);

        ws = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Open");
            }

            @Override
            public void onMessage(String s) {
                final String message = s;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        LinearLayout parent = new LinearLayout(rootView.getContext());

                        parent.setLayoutParams(new LinearLayout.LayoutParams(AppBarLayout.LayoutParams.MATCH_PARENT, AppBarLayout.LayoutParams.WRAP_CONTENT));
                        parent.setOrientation(LinearLayout.HORIZONTAL);

                        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(AppBarLayout.LayoutParams.MATCH_PARENT,
                                AppBarLayout.LayoutParams.WRAP_CONTENT);

                        p.addRule(RelativeLayout.BELOW, id);
                        parent.setLayoutParams(p);

                        id = id + 1;

                        parent.setId(id);

                        ImageView imageView = new ImageView(rootView.getContext());

                        imageView.setImageResource(R.drawable.ic_chat_medico);

                        LinearLayout.LayoutParams imageParams = new AppBarLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );

                        imageParams.weight = 3f;
                        imageParams.width = dp_40;
                        imageParams.height = dp_40;
                        imageParams.setMargins(0,0,0,0);

                        imageView.setLayoutParams(imageParams);

                        parent.addView(imageView);

                        ImageView imageView2 = new ImageView(rootView.getContext());

                        imageView2.setImageResource(R.drawable.arrow_bg2);

                        LinearLayout.LayoutParams imageParams2 = new AppBarLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );

                        imageParams2.weight = 1f;
                        imageParams2.width = dp_30;
                        imageParams2.height = dp_30;
                        imageParams2.setMargins(0,dp_6_5,dp_minus_15,0);

                        imageView2.setLayoutParams(imageParams2);

                        parent.addView(imageView2);

                        TextView textView = new TextView(rootView.getContext());

                        textView.setText(message);
                        textView.setBackgroundResource(R.drawable.rounded_corner1);
                        textView.setPadding(dp_16,dp_16,dp_16,dp_16);

                        LinearLayout.LayoutParams textParams = new AppBarLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );

                        textParams.setMargins(0,dp_10,0,0);
                        textParams.weight = 6f;
                        textView.setLayoutParams(textParams);
                        textView.setTextColor(new Color().parseColor("#000000"));

                        parent.addView(textView);

                        ll.addView(parent);
                    }
                });

            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };

        ws.connect();

        return rootView;
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

        EditText edtMsg = (EditText) rootView.findViewById(R.id.mensagem);
        ws.send(edtMsg.getText().toString());

        LinearLayout parent = new LinearLayout(rootView.getContext());

        parent.setLayoutParams(new LinearLayout.LayoutParams(AppBarLayout.LayoutParams.MATCH_PARENT, AppBarLayout.LayoutParams.WRAP_CONTENT));
        parent.setOrientation(LinearLayout.HORIZONTAL);

        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(AppBarLayout.LayoutParams.MATCH_PARENT,
                AppBarLayout.LayoutParams.WRAP_CONTENT);

        p.addRule(RelativeLayout.BELOW, id);
        parent.setLayoutParams(p);

        id = id + 1;

        parent.setId(id);

        TextView textView = new TextView(rootView.getContext());

        textView.setText(edtMsg.getText().toString());
        textView.setBackgroundResource(R.drawable.rounded_corner);
        textView.setPadding(dp_16,dp_16,dp_16,dp_16);

        LinearLayout.LayoutParams textParams = new AppBarLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        textParams.setMargins(0,dp_10,0,0);
        textParams.weight = 6f;
        textView.setLayoutParams(textParams);
        textView.setTextColor(new Color().parseColor("#000000"));

        parent.addView(textView);

        ImageView imageView = new ImageView(rootView.getContext());

        imageView.setImageResource(R.drawable.arrow_bg1);

        LinearLayout.LayoutParams imageParams = new AppBarLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        imageParams.weight = 1f;
        imageParams.width = dp_30;
        imageParams.height = dp_30;
        imageParams.setMargins(dp_minus_15,dp_6_5,0,0);

        imageView.setLayoutParams(imageParams);

        parent.addView(imageView);

        ImageView imageView2 = new ImageView(rootView.getContext());

        imageView2.setImageResource(R.drawable.ic_perfil);

        LinearLayout.LayoutParams imageParams2 = new AppBarLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        imageParams2.weight = 3f;
        imageParams2.width = dp_40;
        imageParams2.height = dp_40;
        imageParams2.setMargins(0,0,0,0);

        imageView2.setLayoutParams(imageParams2);

        parent.addView(imageView2);

        ll.addView(parent);

        edtMsg.setText("");

    }

}
