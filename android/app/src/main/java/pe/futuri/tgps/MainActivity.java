package pe.futuri.tgps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;


import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    private Socket socketio;
    {
        try {
            socketio = IO.socket("http://192.168.1.9:8080");
        }catch(URISyntaxException e) {}
    }
    TextView accion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        socketio.on("command",  new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                //accion = (TextView)findViewById(R.id.accion);

                try {

                    if (data.get("action").equals("tracking")) {
                        System.out.println("TRANKING");
                        //accion.setText("TRANKING");
                    }else if(data.get("action").equals("monitoring")){
                        //accion.setText("MONITORING");
                        System.out.println("MONITORING");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        socketio.connect();
    }



}
