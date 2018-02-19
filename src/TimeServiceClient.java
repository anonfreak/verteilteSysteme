import java.io.*;
import java.net.Socket;

public class TimeServiceClient {
    private static Socket socket;
    private static BufferedWriter writer;
    private static BufferedReader reader;

    public TimeServiceClient() throws IOException {

    }

    private static void init(String url) throws IOException {
        socket = new Socket(url, 75);
        OutputStream output = socket.getOutputStream();
        writer = new BufferedWriter(new OutputStreamWriter(output));
        InputStream input = socket.getInputStream();
        reader = new BufferedReader(new InputStreamReader(input));
    }

    public static String dateFromServer(String url) throws IOException {
        init(url);
        reader.readLine();
        writer.write("date");
        writer.newLine();
        writer.flush();
        String output = reader.readLine();
        socket.close();
        return output;
    }

    public static String timeFromServer(String url) throws IOException {
        init(url);
        reader.readLine();
        writer.write("time");
        writer.newLine();
        writer.flush();
        String output = reader.readLine();
        socket.close();
        return output;
    }
}
