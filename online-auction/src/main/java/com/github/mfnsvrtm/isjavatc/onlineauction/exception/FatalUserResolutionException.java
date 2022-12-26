package com.github.mfnsvrtm.isjavatc.onlineauction.exception;

public class FatalUserResolutionException extends RuntimeException {

    public FatalUserResolutionException() {
        super("Could not locate logged in user entity.");
    }

}
