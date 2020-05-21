package view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import general.Constants;
import network.ServerThread;
import ro.pub.cs.systems.eim.practicaltest02.R;

public class PracticalTest02MainActivity extends AppCompatActivity {

    private EditText serverPortEditText;
    private Button connectButton;

    private EditText addressEditText;
    private EditText clientPortEditText;
    private EditText hourEditText;
    private EditText minuteEditText;
    private Button setButton;
    private Button pollButton;
    private Button resetButton;

    private ServerThread serverThread = null;

    private ConnectButtonClickListener connectButtonClickListener = new ConnectButtonClickListener();
    private class ConnectButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            String serverPort = serverPortEditText.getText().toString();
            if (serverPort == null || serverPort.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Server port should be filled!", Toast.LENGTH_SHORT).show();
                return;
            }
            serverThread = new ServerThread(Integer.parseInt(serverPort));
            if (serverThread.getServerSocket() == null) {
                Log.e(Constants.TAG, "[MAIN ACTIVITY] Could not create server thread!");
                return;
            }
            serverThread.start();
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(Constants.TAG, "[MAIN ACTIVITY] onCreate() callback method has been invoked");
        setContentView(R.layout.activity_practical_test02_main);


        serverPortEditText = (EditText)findViewById(R.id.server_port_edit_text);
        connectButton = (Button)findViewById(R.id.connect_button);
        connectButton.setOnClickListener(connectButtonClickListener);

        addressEditText = (EditText) findViewById((R.id.client_address_edit_text));
        clientPortEditText = (EditText) findViewById(R.id.client_port_edit_text);
        hourEditText = (EditText) findViewById(R.id.hour_edit_text);
        minuteEditText = (EditText) findViewById(R.id.minute_edit_text);
        setButton = (Button) findViewById(R.id.set_button);

        pollButton = (Button) findViewById(R.id.poll_button);

        resetButton = (Button) findViewById(R.id.reset_button);
    }

    @Override
    protected void onDestroy() {
        Log.i(Constants.TAG, "[MAIN ACTIVITY] onDestroy() callback method has been invoked");
        if (serverThread != null) {
            serverThread.stopThread();
        }
        super.onDestroy();
    }
}
