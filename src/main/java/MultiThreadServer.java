import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mercenery
 *
 */
public class MultiThreadServer {

    static ExecutorService executeIt = Executors.newFixedThreadPool(2);

    /**
     * @param args
     */
    public static void main(String[] args) {
        executeIt.execute(new VoiceThreadSocketListener());
        executeIt.shutdown();
    }
}