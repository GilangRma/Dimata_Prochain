package com.dimata.demo.app.prochain_app.core.exception;

/**
 * Gunakan Exeption ini jika users tidak diizinkan masuk. Contoh : Password salah.
 */
public class UnauthorizedAccessException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 964679676956735086L;

    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
