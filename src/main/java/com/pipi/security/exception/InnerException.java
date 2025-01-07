/**
 * @Time: 2025/1/6 16:10
 * @Author: guoxun
 * @File: InnerException
 * @Description:
 */

package com.pipi.security.exception;

import com.pipi.security.constant.CustomHttpStatus;
import lombok.Getter;


@Getter
public class InnerException extends RuntimeException {

    private CustomHttpStatus customHttpStatus;


    public InnerException(CustomHttpStatus customHttpStatus) {
        super(customHttpStatus.getMessage());
        this.customHttpStatus = customHttpStatus;
    }
}
