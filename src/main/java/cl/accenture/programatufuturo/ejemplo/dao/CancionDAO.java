package cl.accenture.programatufuturo.ejemplo.dao;

import cl.accenture.programatufuturo.ejemplo.exception.SinConexionException;
import cl.accenture.programatufuturo.ejemplo.model.Cancion;
import com.sun.org.apache.xml.internal.security.c14n.implementations.Canonicalizer11_OmitComments;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CancionDAO {

    private Conexion conexion;
    public CancionDAO() {
        this.conexion = new Conexion("com.mysql.jdbc.Driver","localhost","mydb","root","sextape0223",3306);
    }

    public void almacenarCancion (Cancion song) throws SinConexionException {

        try {
            final String SQL = "INSERT INTO cancion(idCancion, nombre, autor, genero, duracion) VALUES (?,?,?,?,?)";

            PreparedStatement ps = conexion.getConexion().prepareStatement(SQL);
            ps.setInt(1, song.getIdCancion());
            ps.setString(2, song.getNombre());
            ps.setString(3, song.getAutor());
            ps.setString(4, song.getGenero());
            ps.setInt(5, song.getDuracion());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cancion> obtenerAll() throws SinConexionException {
        List<Cancion> canciones = new ArrayList<Cancion>();
        try {
            final String SQL = "SELECT * FROM canciones";
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Cancion cancion = new Cancion();
                cancion.setIdCancion(rs.getInt(1));
                cancion.setNombre(rs.getString(2));
                cancion.setAutor(rs.getString(3));
                cancion.setGenero(rs.getString(4));
                cancion.setDuracion(rs.getInt(5));
                canciones.add(cancion);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return canciones;
    }

    public List<Cancion> buscarCancionPorNombre (String cancion) throws SinConexionException {
        List<Cancion> canciones = new LinkedList<Cancion>();
        try {
            final String SQL = "SELECT * FROM canciones WHERE nombre = ?";
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(SQL);
            ps.setString(1, cancion);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cancion song = new Cancion();
                song.setIdCancion(rs.getInt(1));
                song.setNombre(rs.getString(2));
                song.setAutor(rs.getString(3));
                song.setGenero(rs.getString(4));
                song.setDuracion(rs.getInt(5));
                canciones.add(song);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return canciones;
    }
}
