package huice.accompaniment.pojo.DTO;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/3 10:48
 */
@Data
public class LoginDTO {

    String username;
    @Length(min = 8, max = 16, message = "密码长度必须在8到16之间")
    String password;
}
