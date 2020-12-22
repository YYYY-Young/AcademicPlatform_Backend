package result;

import lombok.Data;

/**
 * @Classname Result
 * @Description TODO
 * @Date 2020/12/10 15:33
 * @Created by lrf
 */
@Data
public class Result {

    private int code;
    private String message;
    private Object result;

    Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.result = data;
    }
}
