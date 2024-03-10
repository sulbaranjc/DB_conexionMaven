package org.example;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerNum = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String[] args) {
        boolean salir = false;
        int opcion;
        do {
            menuPrincipal();
            opcion = scannerNum.nextInt();
            try {
                switch (opcion) {
                    case 1:
                        agregarAlumno();
                        break;
                    case 2:
                        mostrarAlumnos();
                        break;
                    case 3:
                        editarAlumno();
                        break;
                    case 4:
                        eliminarAlumno();
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            } catch (ClassNotFoundException e) {
                System.out.println("No se encontró la clase al leer los objetos: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error de entrada/salida: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        } while (!salir);
        System.out.println("Fin del programa");
    }
    private static void menuPrincipal() {
        System.out.println("Menu de Opciones");
        System.out.println("================");
        System.out.println("1. Agregar Alumno");
        System.out.println("2. Listar Alumnos");
        System.out.println("3. Modifier Alumno");
        System.out.println("4. Eliminar Alumno");
        System.out.println("0. Salir");
        System.out.println();
        System.out.print("Ingrese una opcion: ");
    }
    private static void agregarAlumno() throws IOException, ClassNotFoundException, SQLException {
        System.out.print("Nombre: ");
        String nombre = scannerStr.nextLine();
        System.out.print("Apellido: ");
        String apellido = scannerStr.nextLine();
        System.out.print("Nota 1: ");
        double nota1 = scannerNum.nextDouble();
        System.out.print("Nota 2: ");
        double nota2 = scannerNum.nextDouble();
        System.out.print("Nota 3: ");
        double nota3 = scannerNum.nextDouble();

        Alumno alumno = new Alumno(0,nombre, apellido, nota1, nota2, nota3);
        GestorAlumno gestor = new GestorAlumno();
        gestor.alta(alumno); // Llamada al método alta del gestor para agregar el alumno
        System.out.println("Alumno agregado exitosamente.");
    }
    private static void editarAlumno() throws IOException, ClassNotFoundException, SQLException {
        cabeceraListarAlumnos();
        listarAlumnos();
        pieDePagina();
        System.out.print("Ingrese el ID del alumno a modificar: ");
        int id = scannerNum.nextInt();
        System.out.print("Nuevo nombre: ");
        String nombre = scannerStr.nextLine();
        System.out.print("Nuevo apellido: ");
        String apellido = scannerStr.nextLine();
        System.out.print("Nueva nota 1: ");
        double nota1 = scannerNum.nextDouble();
        System.out.print("Nueva nota 2: ");
        double nota2 = scannerNum.nextDouble();
        System.out.print("Nueva nota 3: ");
        double nota3 = scannerNum.nextDouble();
        Alumno alumno = new Alumno(id, nombre, apellido, nota1, nota2, nota3);
        GestorAlumno.modificarAlumno(alumno);
        System.out.println("Alumno actualizado exitosamente.");
    }
    private static void eliminarAlumno() throws IOException, ClassNotFoundException, SQLException {
        cabeceraListarAlumnos();
        listarAlumnos();
        pieDePagina();
        System.out.print("Ingrese el ID del alumno a eliminar: ");
        int id = scannerNum.nextInt();
        GestorAlumno.eliminarAlumno(id); // Llamada al método eliminar del gestor para eliminar el alumno
        System.out.println("Alumno eliminado exitosamente.");
    }
    private static void listarAlumnos() throws SQLException {
        GestorAlumno gestor = new GestorAlumno();
        List<Alumno> alumnos = gestor.listar(); // Llamada al método listar del gestor para obtener la lista de alumnos
        for (Alumno alumno : alumnos) {
            System.out.printf("%-4d%-20s%-20s%-8.2f%-8.2f%-8.2f%-8.2f%n",
                    alumno.getId(), alumno.getNombre(), alumno.getApellido(), alumno.getNota1(), alumno.getNota2(), alumno.getNota3(), alumno.getPromedio());
        }
    }

    private static void mostrarAlumnos() throws IOException, ClassNotFoundException, SQLException {
        cabeceraListarAlumnos();
        listarAlumnos();
        pieDePagina();
        pausa();
    }
    private static void cabeceraListarAlumnos() {
        // Salida de datos con variable local
        System.out.println("\n\n                                Listado de Alumnos");
        System.out.println("====================================================================================");
        System.out.println("ID  Nombre                Apellido         Nota 1   Nota 2  Nota 3  Promedio");
        System.out.println("====================================================================================");
    }
    private static void pieDePagina() {
        System.out.println("====================================================================================");
        System.out.println("====================================================================================");
    }
    private static void pausa() {
        System.out.println();
        System.out.println("Presione ENTER para continuar...");
        scannerStr.nextLine();
    }

}