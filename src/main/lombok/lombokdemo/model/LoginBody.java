package lombokdemo.model;


import lombok.Data;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter
@Getter
public class LoginBody {
    private String email;
    private String password;
}
