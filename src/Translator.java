import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

class Translator implements Runnable {
    volatile Queue<String> to_translate;
    volatile Queue<String> translated;

    Translator() {
        this.to_translate = new LinkedList<String>();
        this.translated = new LinkedList<String>();
    }

    public String get_translated() {
        if (this.translated.isEmpty()) return null;
        return this.translated.remove();
    }

    public void run() {
        while(true) {
            if (!this.to_translate.isEmpty()) {
                String message = this.to_translate.remove();
                this.translate(message);
            }
        }
    }

    public static String clean(String message) {
        return message.replaceFirst("^:[a-z]{2} ","");
    }

    public String go(String message) {
        if (!message.matches("^:[a-z]{2} .*"))
            return message;

        this.to_translate.add(message);
        return null;
    }

    void translate(String message) {
        String lang = String.valueOf(message.charAt(1)) + String.valueOf(message.charAt(2));

        message = Translator.clean(message);

        String trans_msg = "";
        String[] command = new String[] {"../trans", ":" + lang, "-brief", message};

        try
        {
            Process process = Runtime.getRuntime().exec(command) ; 
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));                                          

            String line;
            while((line = reader.readLine()) != null) {
                trans_msg += line + " ";
            }
            reader.close();
            trans_msg = trans_msg.replaceFirst("^\"","").replaceFirst("\" $","");
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        } 

        this.translated.add(trans_msg);
    }
}