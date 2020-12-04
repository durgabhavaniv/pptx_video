package org.zoho.zlabs.webCLI.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.zoho.zlabs.webCLI.model.User;
import org.zoho.zlabs.webCLI.service.UserService;

import javax.annotation.PostConstruct;

@Log
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "admin")
@Component
public class DBConfig {
    @Autowired
    private UserService userService;

    private String username;
    private String password;

    @PostConstruct
    private void loadAdmin(){
        User admin = new User(username,password,1);
        userService.saveUser(admin);
        log.info("admin added successfully");
    }
}
