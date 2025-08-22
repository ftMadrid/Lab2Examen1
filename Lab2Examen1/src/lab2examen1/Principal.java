package lab2examen1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Principal extends JFrame {

    public static ArrayList<RentItem> items = new ArrayList<>();

    public Principal() {

        initVentana();
        initComponentes();

    }

    private void initVentana() {
        setSize(700, 600);
        setTitle("LAB. DE PROGRAMACIÓN II | EXAMEN PRIMER PARCIAL");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
    }

    private void initComponentes() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        titulo.setFont(new Font("Arial", Font.BOLD, 48));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        panel.add(titulo);

        JButton[] botones = {agregar, rentar, submenu, imprimir, salir};
        Font fuenteBoton = new Font("Arial", Font.PLAIN, 20);
        for (JButton b : botones) {
            b.setFont(fuenteBoton);
            b.setMaximumSize(new Dimension(250, 40));
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setFocusPainted(false);
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panel.add(b);
            panel.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        salir.addActionListener(e -> System.exit(0));

        add(panel);
    }

    private final JLabel titulo = new JLabel("BlockBuster");
    private final JButton agregar = new JButton("Agregar Item");
    private final JButton rentar = new JButton("Rentar");
    private final JButton submenu = new JButton("Ejecutar SubMenu");
    private final JButton imprimir = new JButton("Imprimir Todo");
    private final JButton salir = new JButton("Salir");

    public static void main(String[] args) {

        new Principal().setVisible(true);

    }

}
