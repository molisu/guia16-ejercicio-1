package cl.accenture.programatufuturo.ejemplo.exception;

// Exception creada para indicar que no hay conexion
public class SinConexionException extends Exception {
    public SinConexionException (String x) {
        super(x);
    }
}
