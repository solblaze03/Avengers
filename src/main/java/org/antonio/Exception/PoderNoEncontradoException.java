package org.antonio.Exception;

public class PoderNoEncontradoException extends Exception {
    public PoderNoEncontradoException(String nombre) {
        super("No se encontró el superpoder con nombre " + nombre);
    }
}
