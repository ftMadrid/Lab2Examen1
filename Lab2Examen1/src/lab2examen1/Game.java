/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2examen1;

/**
 *
 * @author user
 */
public class Game extends RentItem implements MenuActions {

    public Game(int codigo, String nombre, double precio_renta,String rutaI) {
        super(codigo, nombre, 50,rutaI);//Fijo en 50
        //RutaI - rutaImagen no se como se cnosigue pero ahi esta
    }

    @Override
    public double pagoRenta(int dias) {
        return dias * precio_renta;
    }

    @Override
    public void submenu() {

    }

    @Override
    public void ejecutarOpcion(int opcion) {

    }
}
