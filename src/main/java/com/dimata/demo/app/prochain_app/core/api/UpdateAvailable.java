package com.dimata.demo.app.prochain_app.core.api;

/**
 * Menandakan bahwa tabel bisa di update. <br>
 * Digunain nanti pada {@link DbHandlerBase}
 * @param <T> Kelas tabel
 */
public interface UpdateAvailable<T> {
    T update(T newData);
}
