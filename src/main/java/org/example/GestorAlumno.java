package org.example;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class GestorAlumno {
    Conexion c = new Conexion();

    public void alta(Alumno p) throws SQLException {
        Statement consulta = c.conectar().createStatement();
        String cadena = "INSERT INTO alumno(nombre, apellido, nota1, nota2, nota3, promedio) VALUES ('"
                + p.getNombre() + "','"
                + p.getApellido() + "',"
                + p.getNota1() + ","
                + p.getNota2() + ","
                + p.getNota3() + ","
                + p.calcularPromedio() + ")";
        consulta.executeUpdate(cadena);
        consulta.close();
    }
    public List<Alumno> listar() throws SQLException {
        Statement consulta = c.conectar().createStatement();
        ResultSet rs = consulta.executeQuery("SELECT * FROM alumno");
        List<Alumno> lista = new ArrayList<>();
        while (rs.next()) {
            Alumno p = new Alumno(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getDouble("nota1"),
                    rs.getDouble("nota2"),
                    rs.getDouble("nota3")
            );
            lista.add(p);
        }
        rs.close();
        consulta.close();
        return lista;
    }
    public static void eliminarAlumno(int id) throws SQLException {
        Conexion c = new Conexion();
        Statement consulta = c.conectar().createStatement();
        String cadena = "DELETE FROM alumno WHERE id = " + id;
        consulta.executeUpdate(cadena);
        consulta.close();

    }
    public static void modificarAlumno(Alumno p) throws SQLException {
        Conexion c = new Conexion();
        Statement consulta = c.conectar().createStatement();
        String cadena = "UPDATE alumno SET nombre = '" + p.getNombre() + "', apellido = '" + p.getApellido() +
                        "', nota1 = " + p.getNota1() + ", nota2 = " + p.getNota2() +
                        ", nota3 = " + p.getNota3() + ", promedio = " + p.calcularPromedio() + " WHERE id = " + p.getId();
        consulta.executeUpdate(cadena);
        consulta.close();
    }

}
