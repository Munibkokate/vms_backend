package net.vehicle.vmsbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class Resourcenotfound extends RuntimeException {
    public Resourcenotfound(String message) {
        super(message);
    }
}