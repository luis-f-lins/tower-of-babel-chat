
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.*;

class Translator {
    public static String go(String message) throws Exception {
        message = Translator.clean_message(message);
        return Translator.message_to(message);
    }


    static String clean_message(String message) {
        return message;
    }

    static String message_to(String message) throws Exception {
        char node1 = message.charAt(1);
        char node2 = message.charAt(2);
        String command = "trans" + " :" + node1 + node2 + " -brief " + message.substring(4);  
        Process process = null;
               
//     	    try
//     {
		process = Runtime.getRuntime().exec(command); 
//     }
//     catch(IOException ioe)
//     {
//         ioe.printStackTrace();
//     }                   
//                                             
//     	    
//     	    try
//     {
    String line;
    String output = "";
    try {
        Process p = Runtime.getRuntime().exec(command);
        BufferedReader input = new BufferedReader
            (new InputStreamReader(p.getInputStream()));
        while ((line = input.readLine()) != null) {
            output += (line + " ");
        }
        input.close();
        }
    catch (Exception ex) {
        ex.printStackTrace();
    }
    return output;
//     }
//     catch(IOException ioe)
//     {
//         ioe.printStackTrace();
//     }     
//     
    }
                                                           

}
