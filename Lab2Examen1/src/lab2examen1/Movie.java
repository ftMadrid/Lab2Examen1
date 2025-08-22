package lab2examen1;

import java.util.Calendar;
import javax.swing.ImageIcon;

public class Movie extends RentItem {

    private Calendar fechaEstreno = Calendar.getInstance();

    public Movie(int codigo, String nombre, double precio_renta, String ruta) {
        super(codigo, nombre, precio_renta, ruta);
    }

    public Calendar getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(Calendar fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public String getEstado() {

        Calendar hoy = Calendar.getInstance();

        if (fechaEstreno.after(hoy)) {
            return "ESTRENO";
        }

        int years = hoy.get(Calendar.YEAR) - fechaEstreno.get(Calendar.YEAR);
        int months = hoy.get(Calendar.MONTH) - fechaEstreno.get(Calendar.MONTH);

        int diferenciaMeses = years * 12 + months;

        if (diferenciaMeses <= 3) {
            return "ESTRENO";
        } else {
            return "NORMAL";
        }
    }

    @Override
    public double pagoRenta(int dias) {

        switch (getEstado()) {

            case "ESTRENO":

                if (dias > 2) {
                    return super.getPrecio_renta() + ((dias - 2) * 50);
                } else {
                    return super.getPrecio_renta();
                }
            case "NORMAL":

                if (dias > 5) {
                    return super.getPrecio_renta() + ((dias - 5) * 30);
                } else {
                    return super.getPrecio_renta();
                }
            default:
                return 0;

        }
    }

    @Override
    public String toString() {

        return "- Movie\n"
                + "\nEstado: " + getEstado()
                + super.toString();

    }

}
