package org.aibles.quanlysinhvien.constant;

public enum ResponseCode {
    SUCCESS("success"),
    NOT_FOUND("not_found"),
    BAD_REQUEST("bad_request"),
    CONFLICT("conflict"),
    FORBIDDEN("forbidden"),
    UNAUTHORIZED("unauthorized"),
    INTERNAL_SERVER_ERROR("internal_server_error"),

    INVALID_REQUEST("invalid_request"),
    EMAIL_NOT_FOUND("email_not_found"),
    ACCOUNT_NOT_FOUND("account_not_found"),
    INVALID_OTP("invalid_otp"),
    OTP_EXPIRED("otp_expired"),
    ACCOUNT_ALREADY_ACTIVATED("account_already_activated"),
    PASSWORDS_DO_NOT_MATCH("Passwords do not match"),
    EMAIL_ALREADY_EXISTS("Email already exists"),
    USERNAME_ALREADY_EXISTS("Username already exists"),
    ACCOUNT_PERMANENTLY_LOCKED("account_permanently_locked"),
    ACCOUNT_TEMPORARILY_LOCKED("account_temporarily_locked"),
    INVALID_PASSWORD("invalid_password"),
    ACCOUNT_NOT_ACTIVATED("account_not_activated"),
    UNEXPECTED_ERROR("unexpected_error"),
    INVALID_AMOUNT("invalid_amount"),
    UNKNOWN_ERROR("unknown_error");



    private final String value;

    ResponseCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
