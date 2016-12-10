
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;

class Translator {
    public static String go(String message) {
        message = Translator.clean_message(message);
        return Translator.message_to(message);
    }


    static String clean_message(String message) {
        return message;
    }

    static String message_to(String message) {
        char node1 = message.charAt(1);
        char node2 = message.charAt(2);
        String command = "trans" + " :" + node1 + node2 + " -brief " + "'" + message.substring(4) + "'";  
        Process process = null;
        String trans_msg = null;
               
    	    try
    {
		process = Runtime.getRuntime().exec(command); 
    }
    catch(IOException ioe)
    {
        ioe.printStackTrace();
    }                   
    
    	BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));                                          
    	    
    	    try
    {
    	trans_msg = reader.readLine(); 
    }
    catch(IOException ioe)
    {
        ioe.printStackTrace();
    }     
    
        return command;
    }
                                                           

}
