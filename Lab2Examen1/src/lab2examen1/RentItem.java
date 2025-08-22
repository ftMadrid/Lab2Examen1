package lab2examen1;

import javax.swing.ImageIcon;

public abstract class RentItem {
    
    protected int codigo;
    protected String nombre;
    protected double precio_renta;
    protected int cantidadCopias = 0;
    protected ImageIcon imagen;
    String rutaI;
    
    public RentItem(int codigo, String nombre, double precio_renta,String ruta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio_renta = precio_renta;
        this.rutaI = ruta;
    }
    
    public String toString() {
        return "\n| Codigo: "+codigo
                +"\n| Nombre: "+nombre
                +String.format("\n| Precio de Renta: Lps.%.2f", precio_renta);
    }
    
    public abstract double pagoRenta(int dias);
    
    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio_renta() {
        return precio_renta;
    }
}
