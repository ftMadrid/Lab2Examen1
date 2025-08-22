/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2examen1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author user
 */
public class Game extends RentItem implements MenuActions {

    Calendar fechaPublicacion;
    ArrayList<String> Especificaciones;

    public Game(int codigo, String nombre, String rutaI) {
        super(codigo, nombre, 20, rutaI);//Fijo en 20
        //RutaI - rutaImagen no se como se cnosigue pero ahi esta
        fechaPublicacion = Calendar.getInstance();
        Especificaciones = new ArrayList<>();
    }

    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return super.toString()
                + "\nFecha Publicacion:" + dateFormat.format(fechaPublicacion.getTime())
                + "\n-PS3 Game";
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
        //Submenu con opciones
        /*
        Actualizar fecha
        Agregar Especificacion
        Ver Especificaciones
         */
    }

    @Override
    public void ejecutarOpcion(int opcion) {

    }
}
