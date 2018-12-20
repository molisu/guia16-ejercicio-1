package cl.accenture.programatufuturo.ejemplo;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hola desde maven");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Miau");

            Connection conexion =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/mydb",
                            "root",
                            "sextape0223");

            System.out.println("WE DID IT!");

            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select * from vendedores");

            while(rs.next()) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString("nombre"));
            }

            Statement sentenciaInsert = conexion.createStatement();
            int resultadoInsert = sentenciaInsert.executeUpdate("INSERT INTO vendedores (idVendedor, nombre, ciudad, comisión)" +
                    "VALUES (5007, 'Pepe pepo', 'Ciudad gotica', 100000);");

            System.out.println("Resultado : " + resultadoInsert);

        } catch (ClassNotFoundException e) {
            System.out.println("El driver requerido no esta cargado.");

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
