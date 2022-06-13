package com.hitsz.aircraftwar.application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.hitsz.aircraftwar.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class OnlineActivity extends AppCompatActivity {

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    private static int opponentScore=0;
    private GameView view;

    private static boolean gameOverFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);

        Handler handler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1 && msg.obj.equals("start")) {
                    view = new HardGame(OnlineActivity.this);
                    view.setPlayMusic(MainActivity.myBinder != null);
                    setContentView(view);

                    // 给服务端发送信息
                    new Thread(() -> {
                        while (!view.isGameOverFlag()) {
                            writer.println(view.getScore());
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        writer.println("end");
                    }).start();
                } else if (msg.what == 1 && msg.obj.equals("end")) {
                    setGameOverFlag(true);
                } else {
                    opponentScore = Integer.parseInt((String) msg.obj);
                }
            }
        };

        new Thread(new NetConn(handler)).start();

    }

    private class NetConn extends Thread {

        private final Handler handler;

        public NetConn(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress("192.168.80.129", 6666), 5000);
                writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                        socket.getOutputStream(), StandardCharsets.UTF_8
                )), true);
                reader = new BufferedReader(new InputStreamReader(
                        socket.getInputStream(), StandardCharsets.UTF_8
                ));

                // 接收服务端信息
                new Thread(() -> {
                    String msg;
                    try{
                        while ((msg = reader.readLine()) != null){
                            Message msgFromServer = new Message();
                            msgFromServer.what = 1;
                            msgFromServer.obj = msg;
                            handler.sendMessage(msgFromServer);
                        }
                    } catch(IOException e){
                        e.printStackTrace();
                    }
                }).start();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getOpponentScore() {
        return opponentScore;
    }

    public void setGameOverFlag(boolean gameOverFlag) {
        OnlineActivity.gameOverFlag = gameOverFlag;
    }

    public static boolean isGameOverFlag() {
        return gameOverFlag;
    }
}