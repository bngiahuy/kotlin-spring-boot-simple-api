package org.example.simpleapi.common.models

import org.springframework.http.HttpStatus

data class ApiResponse<T>(
    var success: Boolean = true,
    var message: String? = null,
    var payload: T? = null
) {
    constructor(success: Boolean, message: String) : this(success, message, null)
    constructor(message: String, payload: T) : this(true, message, payload)
    constructor(payload: T) : this(true, "Success", payload)
}
