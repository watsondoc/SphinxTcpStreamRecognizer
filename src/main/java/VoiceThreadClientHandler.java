import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class VoiceThreadClientHandler implements Runnable {

    private static Socket clientDialog;
    private ShpinxConfiguration configuration;
    private Integer id;
    private HttpSnakeClient httpClient;
    public VoiceThreadClientHandler(Socket client, Integer id) {
        this.id = id;
        VoiceThreadClientHandler.clientDialog = client;
        configuration = new ShpinxConfiguration(LangType.English);
        httpClient = new HttpSnakeClient();
    }

    @Override
    public void run() {

        try {
            DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());
            DataInputStream in = new DataInputStream(clientDialog.getInputStream());
            System.out.println("DataInputStream created");
            System.out.println("DataOutputStream  created");
            SpeechResult result;
            StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration.getConfiguration());
            recognizer.startRecognition(in);
            while (!clientDialog.isClosed() && (result = recognizer.getResult()) != null) {
                System.out.println("Server reading from channel");
                String rawCommand = result.getHypothesis();
                System.out.println(rawCommand);
                String command = Commands.INSTANCE.getCommand(rawCommand);
                System.out.println(command);
                if (command.equalsIgnoreCase("STOP")) {

                }
                else if (!command.equalsIgnoreCase("UNKNOW")){
                    httpClient.sendPut(command.toLowerCase());
                }
            }
            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");
            in.close();
            out.close();
            clientDialog.close();

            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}