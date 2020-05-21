package model;

import androidx.annotation.NonNull;

public class TimerNetwork {

    private String serverInfo;

    public TimerNetwork() {
        this.serverInfo = null;
    }

    public TimerNetwork(String response) {
        this.serverInfo = response;
    }

    public String getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(String serverInfo) {
        this.serverInfo = serverInfo;
    }

    @NonNull
    @Override
    public String toString() {
        return "Timer status is: " + this.serverInfo;
    }
}
