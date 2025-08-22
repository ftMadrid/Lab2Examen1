package lab2examen1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import lab2examen1.ventanas.AgregarItem;
import lab2examen1.ventanas.Rentar;
import lab2examen1.ventanas.RentarItem;

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
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        titulo.setFont(new Font("Segoe UI", Font.BOLD, 48));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        
        panel.add(titulo);

        JButton[] botones = {agregar, rentar, submenu, imprimir, salir};
        Font fuenteBoton = new Font("Segoe UI", Font.PLAIN, 20);
        for (JButton b : botones) {
            b.setFont(fuenteBoton);
            b.setMaximumSize(new Dimension(250, 40));
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setFocusPainted(false);
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panel.add(b);
            panel.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        agregar.addActionListener(e -> agregarAction());
        rentar.addActionListener(e -> rentarAction());
        submenu.addActionListener(e -> ejecutarMenuAction());
        imprimir.addActionListener(e -> imprimirAction());
        salir.addActionListener(e -> salirAction());
        
        add(panel);
    }
    
    private void agregarAction() {
        dispose();
        new AgregarItem().setVisible(true);
        
    }
    
    private void rentarAction() {
        dispose();
        new RentarItem().setVisible(true);
    }
    
    private void ejecutarMenuAction() {
        String input = javax.swing.JOptionPane.showInputDialog(null, "Ingrese el codigo del juego:", "Submenu Juego", javax.swing.JOptionPane.PLAIN_MESSAGE);

            if (input == null) { // Si cierra el cuadro de diálogo
                return;
            }
            Game item = null;
            try {
                int codigo = Integer.parseInt(input);
                
                for (RentItem i : items) {
                if (i.getCodigo() == codigo && i instanceof Game) {
                    item = (Game)i;
                }
            }
                
                if (item == null)
                {
                    //No encontrado
                return;
                }
                else
                {
                item.submenu();
                }
            } catch (NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(null, "Ingrese un num valido.");
            }
        
        
    }
    
    private void imprimirAction() {
        dispose();
        new ImprimirTodo().setVisible(true);
        
    }
    
    private void salirAction() {
        JOptionPane.showMessageDialog(null, "Que tenga lindo dia inge :D!", "DESPEDIDA", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private final JPanel panel = new JPanel();
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
