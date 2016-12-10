import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class Translator implements Runnable {
    volatile Queue<String> messages;

    Translator() {
        this.messages = new LinkedList<String>();
    }

    public String get_message() {
        if (this.messages.isEmpty()) return null;
        return this.messages.remove();
    }

    public void run() {
    }

    public static String clean(String message) {
        if (message.length() < 5 || message.length() == 0 || message.charAt(0) != ':') // ":xx x"
            return message;
        else
            return message.substring(4);
    }

    public String go(String message) {
        if (message.length() < 5 || message.length() == 0 || message.charAt(0) != ':') { // ":xx x"
            return message;
        } else {
            this.messages.add(Translator.message(message));
            return null;
        }
    }

    static String message(String message) {
        String lang = String.valueOf(message.charAt(1)) + String.valueOf(message.charAt(2));

        message = message.substring(4);
        String command = "../trans" + " :" + lang + " -brief " + "\"" + message + "\"";
        LOG.debug(command);

        String trans_msg = message;

        try
        {
            Process process = Runtime.getRuntime().exec(command); 
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));                                          
            trans_msg = "";

            boolean exit_loop = false;
            while(!exit_loop) {
                trans_msg += reader.readLine() + " ";

                exit_loop = trans_msg.endsWith(" null ");
                trans_msg = trans_msg.replaceFirst(" ?null $", "");
            }
            trans_msg = trans_msg.replaceFirst("^\"","").replaceFirst("\"$","");
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }                   
        return trans_msg;
    }
}
