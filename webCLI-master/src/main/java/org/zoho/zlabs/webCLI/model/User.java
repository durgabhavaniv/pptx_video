package org.zoho.zlabs.webCLI.model;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    private String username;
    @NonNull
    private String password;
    @NonNull
    private int role;
}
