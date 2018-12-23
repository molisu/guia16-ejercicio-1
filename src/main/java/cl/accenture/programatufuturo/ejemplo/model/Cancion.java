package cl.accenture.programatufuturo.ejemplo.model;

    //Clase Cancion
public class Cancion {

    // Con sus atributos
    private int idCancion, duracion;
    private String nombre, autor, genero;

    // Constructor con parametros
    public Cancion(int idCancion, int duracion, String nombre, String autor, String genero) {
        this.idCancion = idCancion;
        this.duracion = duracion;
        this.nombre = nombre;
        this.autor = autor;
        this.genero = genero;
    }

    // Getters y Setters para mostrar y modificar según sea necesario en la clase DAO
    public int getIdCancion() {
        return idCancion;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    // Método equals editado para comparar 2 canciones de la Class Cancion
    // según su id
    public boolean equals(Object o) {
        if(o instanceof Cancion) {
            Cancion cancioncita = (Cancion) o;
            return this.getIdCancion() == cancioncita.getIdCancion(); // Como es int se compara con ==
        }
        return false;
    }

}
