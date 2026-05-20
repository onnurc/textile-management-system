package com.tekstil.textile_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    private boolean success;
    private int code;
    private String message;
    private T data;

    // Başarılı response için static factory method
    public static <T> BaseResponse<T> success(int code, String message, T data) {
        return new BaseResponse<>(true, code, message, data);
    }
    // Hatalı response için static factory method
    public static <T> BaseResponse<T> error(int code, String message) {
        return new BaseResponse<>(false, code, message, null);
    }
    public static <T> BaseResponse<T> error(int code, String message, T data) {
        return new BaseResponse<>(false, code, message, data);
    }
}