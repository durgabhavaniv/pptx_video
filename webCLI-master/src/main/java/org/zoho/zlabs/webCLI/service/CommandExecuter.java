package org.zoho.zlabs.webCLI.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
@Log
public class CommandExecuter {

    public String processCommand(String command){
    String output = "";
    String s = "";
        try {
            log.info("command: "+command);
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            while ((s = stdInput.readLine()) != null) {
                output += s + "<br>";
            }

            if ((s = stdError.readLine()) != null) {
                System.out.println("Here is the standard error of the command (if any):\n");
                System.out.println(s);
                while ((s = stdError.readLine()) != null) {
                    log.warning(s);
                }
            }

        }catch (Exception e){
            e.getStackTrace();
        }
        return output;
    }

}
