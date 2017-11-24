import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Commands {

    private ArrayList<String> commandsList;
    private static final String FILENAME_PATH = "D:\\Sphinx\\src\\main\\resources\\commandsv2.txt";
    private HashMap<String,String> commandsMap;

    public Commands() {
        commandsList = new ArrayList<>();
        commandsMap = new HashMap<>();
        this.readCommands();
    }

    private void readCommands() {
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(FILENAME_PATH);
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                String[] command =  sCurrentLine.split(" ");
                //commandsList.add(sCurrentLine);
                commandsMap.put(command[0], command[1]);
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    public String getCommand(String rawCommand) {
        String discretCommand = rawCommand.split(" ")[0];
        if(commandsMap.containsKey(discretCommand)) {
            return commandsMap.get(discretCommand);
        }
        return "UNKNOW";
    }

    public static final Commands INSTANCE = new Commands();
}
