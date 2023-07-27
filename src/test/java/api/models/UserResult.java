package api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResult<T> {
    private String jsonrpc;
    private T result;
    private String error;
    private String username;
}
