package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

/**
 * Created by lsu on 6/27/17.
 */
@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String login;
    private String name;
    private String password;

    public User(Consumer<User> builder) {
        builder.accept(this);
    }
}
