package de.dhbw.verteiltesysteme;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServiceMultithread extends Thread {
    private OutputStream output;
    private BufferedWriter writer;
    private InputStream input;
    private BufferedReader reader;
    private Socket socket;
    private String threadName;

    public TimeServiceMultithread(ServerSocket serverSocket, int i) throws IOException {
            threadName = "connection" + i;
            socket = serverSocket.accept();
            output = socket.getOutputStream();
            writer = new BufferedWriter(new OutputStreamWriter(output));
            input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
    }


    public void run() {
        String inputText = "";
        try {
            do {
                switch (inputText) {
                    case "date":
                        writer.write(Clock.date());
                        break;
                    case "time":
                        writer.write(Clock.time());
                        break;
                    default:
                        writer.write("Bitte geben sie den Befehl time, date oder exit ein");
                        break;
                }
                writer.newLine();
                writer.flush();
                inputText = reader.readLine();
            } while (!(inputText == null || inputText.equals("exit")));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
