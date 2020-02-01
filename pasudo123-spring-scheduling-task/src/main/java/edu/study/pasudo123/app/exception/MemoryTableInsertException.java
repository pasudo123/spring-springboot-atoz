package edu.study.pasudo123.app.exception;

import org.springframework.dao.DataAccessException;

public class MemoryTableInsertException extends DataAccessException {

    public MemoryTableInsertException(String message) {
        super(message);
    }
}
