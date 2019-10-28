import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VoiceThreadSocketListener  implements Runnable {
    private static ExecutorService executeIt = Executors.newFixedThreadPool(2);

    @Override
    public void run() {

        try (ServerSocket server = new ServerSocket(3345);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Server socket created, command console reader for listen to server commands");
            Integer id = 0;
            while (!server.isClosed()) {

                if (br.ready()) {
                    System.out.println("Main Server found command");
                    String serverCommand = br.readLine();
                    if (serverCommand.equalsIgnoreCase("quit")) {
                        System.out.println("Main Server initiate exiting...");
                        server.close();
                        break;
                    }
                }

                Socket client = server.accept();
                executeIt.execute(new VoiceThreadClientHandler(client, id++));
                System.out.print("Connection accepted.");
            }

            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
