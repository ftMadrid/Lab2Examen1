/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2examen1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class Game extends RentItem implements MenuActions {

    Calendar fechaPublicacion;
    ArrayList<String> Especificaciones;
    String rutaI;

    public Game(int codigo, String nombre, String rutaI) {
        super(codigo, nombre, 20, rutaI);//Fijo en 20
        //RutaI - rutaImagen no se como se cnosigue pero ahi esta
        fechaPublicacion = Calendar.getInstance();
        Especificaciones = new ArrayList<>();
    }

    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return super.toString()
                + "\nFecha Publicacion:" + dateFormat.format(fechaPublicacion.getTime()) + "\n-PS3 Game";
    }

    public void setFechaPublicacion(Calendar fecha) {
        this.fechaPublicacion = fecha;
    }

    public Calendar getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String listEspecificaciones() {
        return listEspecificacionesRec(0, "");
    }

    public String listEspecificacionesRec(int contador, String stringsito) {
        if (contador < Especificaciones.size()) {
            return listEspecificacionesRec((contador + 1), (stringsito + Especificaciones.get(contador) + "\n"));
        }
        return stringsito;
    }

    @Override
    public double pagoRenta(int dias) {
        return dias * precio_renta;
    }

    @Override
    public void submenu() {
        int opcion = 0;

        do {
            String menu = """
        --- Submenu Juego ---
        1. Actualizar fecha publicación
        2. Agregar especificación
        3. Ver especificaciones
        4. Salir
        """;

            String input = javax.swing.JOptionPane.showInputDialog(null, menu, "Submenu Juego", javax.swing.JOptionPane.PLAIN_MESSAGE);

            if (input == null) { // Si cierra el cuadro de diálogo
                break;
            }

            try {
                opcion = Integer.parseInt(input);
                ejecutarOpcion(opcion);
            } catch (NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
            }

        } while (opcion != 4);
    }

    @Override
public void ejecutarOpcion(int opcion) {
    switch (opcion) {
        case 1 -> {
            // Actualizar fecha con JDateChooser
            JDateChooser dateChooser = new JDateChooser();
            dateChooser.setDateFormatString("yyyy-MM-dd");

            int result = JOptionPane.showConfirmDialog(null, dateChooser, "Seleccione fecha de publicación",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                Date selectedDate = dateChooser.getDate();
                if (selectedDate != null) {
                    Calendar nuevaFecha = Calendar.getInstance();
                    nuevaFecha.setTime(selectedDate);
                    setFechaPublicacion(nuevaFecha);
                    JOptionPane.showMessageDialog(null, "Fecha actualizada con éxito.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se selecciono ninguna fecha.");
                }
            }
        }
        case 2 -> {
            // Agregar especificación
            String esp = JOptionPane.showInputDialog("Ingrese Especificacion:");
            if (esp != null && !esp.trim().isEmpty()) {
                Especificaciones.add(esp);
                JOptionPane.showMessageDialog(null, "Especificacion agregada.");
            }
        }
        case 3 -> {
            // Ver especificaciones
            if (Especificaciones.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay especificaciones registradas.");
            } else {
                JOptionPane.showMessageDialog(null, "Especificaciones:\n" + listEspecificaciones());
            }
        }
        case 4 -> JOptionPane.showMessageDialog(null, "Saliendo del submenu...");
        default -> JOptionPane.showMessageDialog(null, "Opcion invalid.");
    }
}
}

