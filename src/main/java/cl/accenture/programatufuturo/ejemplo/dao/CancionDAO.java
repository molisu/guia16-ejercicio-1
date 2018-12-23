package cl.accenture.programatufuturo.ejemplo.dao;

import cl.accenture.programatufuturo.ejemplo.exception.SinConexionException;
import cl.accenture.programatufuturo.ejemplo.model.Cancion;
import com.sun.org.apache.xml.internal.security.c14n.implementations.Canonicalizer11_OmitComments;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

    // Clase DAO de cancion
public class CancionDAO {

    // Su único atributo es la conexion que previamente creamos
    private Conexion conexion;

    // Para inciar la conexion, en el constructor creo una new Conexion e ingreso los parametros de mi conección.
    public CancionDAO() {
        this.conexion = new Conexion("com.mysql.jdbc.Driver","localhost","mydb","root","sextape0223",3306);
    }

    // Método para almacenar una Cancion, que arroja un error en caso de no haber conexion, pero no lo capura
    public void almacenarCancion (Cancion song) throws SinConexionException {

        try {
            // Ingreso mi comentario SQL utilizando INSERT INTO que es el comando para insertar, seguido de los
            // VALUES que serían los valores, con signos de interrogación, para posteriormente ser rellenados.
            final String SQL = "INSERT INTO cancion(idCancion, nombre, autor, genero, duracion) VALUES (?,?,?,?,?)";

            // Iniciamos un PreparedStatement donde iniciamos una conexion con el prepareStatement que recibe nuestra SQL.
            PreparedStatement ps = conexion.getConexion().prepareStatement(SQL);

            // de aquí en adelante, según el tipo de parametro de la columna vamos ingresando lo que corresponde
            ps.setInt(1, song.getIdCancion());

            // El numero nos indica la posición del signo de interrogación que estamos rellenando, y luego de la coma
            // ingresamos el parametro que corresponde de la Cancion que recibimos llamada song.
            ps.setString(2, song.getNombre());
            ps.setString(3, song.getAutor());
            ps.setString(4, song.getGenero());
            ps.setInt(5, song.getDuracion());

            // Con este comando ejecutamos la creación de este objeto
            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtener todas las canciones, mismo error lanzado que arriba
    // Vamos a retornar una List de Cancion ya que debemos mostrar varias canciones.
    public List<Cancion> obtenerAll() throws SinConexionException {

        // Creamos nuestra List, en este caso use ArrayList
        List<Cancion> canciones = new ArrayList<Cancion>();

        try {

            // Selecciono todas las columnas, de la tabla cancion
            final String SQL = "SELECT * FROM cancion";
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(SQL);

            // Aquí creamos un ResultSet llamado rs (que será el resultado de la Query (consulta)
            // que hicimos con el PreparedStatement. ejecutamos la Query
            ResultSet rs = ps.executeQuery();


            // Mientras sigan habíendo canciones
            while (rs.next()){

                // creare un nuevo objeto Cancion con los datos que me entrega el rs
                Cancion cancion = new Cancion();

                // al nuevo objeto cancion, le cambio el valor por defecto de sus columnas
                // con un set, y dentro de eso relleno con un rs (respuesta de la query)
                // seguido de un get con el tipo que corresponda, y el numero que indica el
                // numero de la columna.
                cancion.setIdCancion(rs.getInt(1));
                cancion.setNombre(rs.getString(2));
                cancion.setAutor(rs.getString(3));
                cancion.setGenero(rs.getString(4));
                cancion.setDuracion(rs.getInt(5));

                // añado a la lista canciones la cancion (nuevo objeto cancion)
                canciones.add(cancion);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return canciones;
    }


    // Buscar una cancion por su nombre, retorno una Lista ya que más de una cancion puede llamarse igual
    // recibo un String que será el nombre de la cancion
    public List<Cancion> buscarCancionPorNombre (String cancion) throws SinConexionException {

        // Creo mi lista, de tipo linked porque quiero >:)
        List<Cancion> canciones = new LinkedList<Cancion>();

        try {
            // Selecciono todas las columnas de la tabla cancion, donde su nombre sea
            // equivalente a un valor que entregaré a continuacion
            final String SQL = "SELECT * FROM cancion WHERE nombre = ?";
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(SQL);

            // aqui ingreso el valor de mi signo de interrogacion, es decir '?'
            // será igual a cancion que es el String que me ingresan
            ps.setString(1, cancion);

            // respuesta almacenada en una variable, de la Query ejecutada en ps.
            ResultSet rs = ps.executeQuery();


            // Mientras sigan habíendo respuestas
            while (rs.next()) {

                // Creo objeto cancion
                Cancion song = new Cancion();

                // y le entrego los valores que corresponden a sus atributos
                song.setIdCancion(rs.getInt(1));
                song.setNombre(rs.getString(2));
                song.setAutor(rs.getString(3));
                song.setGenero(rs.getString(4));
                song.setDuracion(rs.getInt(5));

                // añado la cancion a mi list
                canciones.add(song);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return canciones;
    }
}
