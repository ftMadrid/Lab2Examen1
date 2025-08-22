package lab2examen1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Principal extends JFrame {
    
    public static ArrayList<String> items = new ArrayList<>();
    
    public Principal() {
        
        initVentana();
        initComponentes();
        
    }
    
    private void initVentana() {
        
        setSize(700, 600);
        setTitle("LABORATORIO DE PROGRAMACION II | EXAMEN PRIMER PARCIAL");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        
    }
    
    private void initComponentes() {
        
    }
    
    
}
