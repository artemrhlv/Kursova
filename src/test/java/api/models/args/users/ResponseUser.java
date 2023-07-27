package api.models.args.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
    private int id;
    private String username;
    private String password;
    private String role;
    private int is_ldap_user;
    private String name;
    private String email;
    private String google_id;
    private String github_id;
    private int notifications_enabled;
}
