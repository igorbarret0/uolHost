package com.uolHost.uolHost_backend_challenge.exception;

import java.util.Date;

public record ExceptionResponse(
        Date timeStamp,
        String message,
        String statusCode
) {

}
