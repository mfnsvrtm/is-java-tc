package com.github.mfnsvrtm.isjavatc.onlineauction.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> entityClass, String explanationPostfix) {
        super("Could not locate %s %s.".formatted(entityClass.getSimpleName().toLowerCase(), explanationPostfix));
    }

    public EntityNotFoundException(Class<?> entityClass, int id) {
        this(entityClass, "with id %d".formatted(id));
    }

}
