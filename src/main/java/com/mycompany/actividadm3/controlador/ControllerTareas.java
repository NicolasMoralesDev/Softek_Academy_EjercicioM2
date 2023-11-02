package com.mycompany.actividadm3.controlador;

import com.mycompany.actividadm3.database.Conexion;
import com.mycompany.actividadm3.model.Tareas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Nico Morales
 */
public class ControllerTareas {

    private Conexion conex = new Conexion();

    public List<Tareas> traerTareas() {
        List<Tareas> tareas = new ArrayList<>();

        try {

            Connection conectar = conex.establecerConeccion();
            PreparedStatement buscar = conectar.prepareStatement("select * from tareas");
            ResultSet consulta = buscar.executeQuery();

            while (consulta.next()) {

                Tareas tarea = new Tareas();
                tarea.setId(Long.parseLong(consulta.getString("id")));
                tarea.setTitulo(consulta.getString("titulo"));
                tarea.setDescripcion(consulta.getString("descripcion"));
                tareas.add(tarea);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return tareas;
    }

    public void crearTarea(Tareas tarea) {

        try {

            Connection conectar = conex.establecerConeccion();
            PreparedStatement insert = conectar.prepareStatement("INSERT INTO tareas (titulo, descripcion) VALUES (?, ?)");
            insert.setString(1, tarea.getTitulo());
            insert.setString(2, tarea.getDescripcion());
            insert.executeUpdate();

            JOptionPane.showMessageDialog(null, "Tarea creada con Exito!!!", "Tarea", 3);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void eliminarTarea(int id) {

        try {

            Connection conectar = conex.establecerConeccion();
            PreparedStatement eliminar = conectar.prepareStatement("delete from tareas where id = ?");
            eliminar.setInt(1, id);
            eliminar.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
//       public void modificarTarea(Tareas tarea) {
//
//        try {
//
//            Connection conectar = conex.establecerConeccion();
//            PreparedStatement eliminar = conectar.prepareStatement("delete from empleados where id = ?");
//            eliminar.setInt(1, tarea.getId().intValue());
//            eliminar.setString(1, tarea.getTitulo());
//            eliminar.setString(1,  tarea.getDescripcion());
//
//            eliminar.executeUpdate();
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//    }
}
