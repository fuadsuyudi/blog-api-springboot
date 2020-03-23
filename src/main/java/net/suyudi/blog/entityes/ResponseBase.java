package net.suyudi.blog.entityes;

import lombok.Getter;
import lombok.Setter;

/**
 * ResponseBase
 */
@Getter
@Setter
public class ResponseBase<Any> {

    private Boolean status = true;
    private Integer code = 200;
    private String message = "Success";
    private Any data;

    @Override
    public String toString() {
        return "{\"status\": " + status + ", \"code\": " + code + ", \"message\": \"" + message + "\", \"data\": " + data + "}";
    }
}