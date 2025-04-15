package br.com.colombano.springauth.dto.response;

import java.time.ZonedDateTime;

public record StandardError(String message, int statusCode, ZonedDateTime timestamp) {
}
