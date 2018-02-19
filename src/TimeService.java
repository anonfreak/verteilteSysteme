import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeService {
    private OutputStream output;
    private BufferedWriter writer;
    private InputStream input;
    private BufferedReader reader;

    public TimeService() throws IOException {
        ServerSocket serverSocket = new ServerSocket(75);
        int i = 0;
        while(true) {
            TimeServiceMultithread multithread = new TimeServiceMultithread(serverSocket, i);
            i++;
            multithread.start();
        }
    }


    public void readAndProcess(Socket socket) throws IOException {
        String inputText = "";
        do {
            switch(inputText){
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
        } while(!(inputText == null || inputText.equals("exit")));
        socket.close();
    }
}
