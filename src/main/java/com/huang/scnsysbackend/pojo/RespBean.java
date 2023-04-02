package com.huang.scnsysbackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object result;

    /**
     * 成功返回结果
     * @param message
     * @return
     */
    public static RespBean success(String message) {
        return new RespBean(0, message, null);
    }

    /**
     * 成功返回结果
     * @param message
     * @param object
     * @return
     */
    public static RespBean success(String message, Object object) {
        return new RespBean(0, message, object);
    }

    /**
     * 失败返回结果
     * @param message
     * @return
     */
    public static RespBean error(String message) {
        return new RespBean(500, message, null);
    }

    /**
     * 失败返回结果
     * @param message
     * @param object
     * @return
     */
    public static RespBean error(String message, Object object) {
        return new RespBean(500, message, object);
    }
}
