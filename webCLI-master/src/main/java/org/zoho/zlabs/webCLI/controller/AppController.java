package org.zoho.zlabs.webCLI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zoho.zlabs.webCLI.service.CommandExecuter;

import java.util.HashMap;

@RestController
public class AppController {

    @Autowired
    private CommandExecuter commandExecuter;

    @PostMapping("/api/execute")
    public ResponseEntity<String> runCommand(@RequestBody HashMap<String,String> requestData){
        String command = requestData.get("command");

        return ResponseEntity.ok().body(commandExecuter.processCommand(command));
    }
}
