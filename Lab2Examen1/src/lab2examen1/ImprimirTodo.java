package lab2examen1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ImprimirTodo extends JFrame {

    int paginaActual = 0, paginas;

    JPanel panelCartelera;
    JButton btnAnterior, btnSiguiente;
    JLabel lblPagina;

    public ImprimirTodo() {
        setSize(800, 700);
        setTitle("LABORATORIO DE PROGRAMACION II | EXAMEN PRIMER PARCIAL");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // calcular cuántas páginas habrá
        if (Principal.items.size() == 0) {
            paginas = 1;
        } else {
            paginas = (int) Math.ceil(Principal.items.size() / 6.0);
        }

        // Panel central donde van los items
        panelCartelera = new JPanel(new GridLayout(2, 3, 10, 10)); // 2 filas x 3 columnas
        JScrollPane scroll = new JScrollPane(panelCartelera);
        add(scroll, BorderLayout.CENTER);

        // Panel de navegación
        JPanel panelNav = new JPanel();
        btnAnterior = new JButton("Anterior");
        btnSiguiente = new JButton("Siguiente");
        lblPagina = new JLabel("Página 1 de " + paginas);

        btnAnterior.addActionListener(e -> cambiarPagina(-1));
        btnSiguiente.addActionListener(e -> cambiarPagina(1));

        panelNav.add(btnAnterior);
        panelNav.add(lblPagina);
        panelNav.add(btnSiguiente);

        add(panelNav, BorderLayout.SOUTH);

        mostrarPagina();
    }

    private void cambiarPagina(int dir) {
        paginaActual += dir;
        if (paginaActual < 0) paginaActual = 0;
        if (paginaActual >= paginas) paginaActual = paginas - 1;
        mostrarPagina();
    }

    private void mostrarPagina() {
        panelCartelera.removeAll();

        int inicio = paginaActual * 6;
        int fin = Math.min(inicio + 6, Principal.items.size());

        // recorro directamente el ArrayList
        for (int i = inicio; i < fin; i++) {
            RentItem item = Principal.items.get(i);

            JPanel panelItem = new JPanel(new BorderLayout());

            // Imagen
            try {
                ImageIcon icon = new ImageIcon(item.getRuta());
                Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                JLabel lblImg = new JLabel(new ImageIcon(img));
                panelItem.add(lblImg, BorderLayout.CENTER);
            } catch (Exception e) {
                panelItem.add(new JLabel("Sin imagen"), BorderLayout.CENTER);
            }

            // Texto (toString de Movie o Game)
            JTextArea txtInfo = new JTextArea(item.toString());
            txtInfo.setEditable(false);
            txtInfo.setLineWrap(true);
            txtInfo.setWrapStyleWord(true);
            panelItem.add(new JScrollPane(txtInfo), BorderLayout.SOUTH);

            panelCartelera.add(panelItem);
        }

        lblPagina.setText("Página " + (paginaActual + 1) + " de " + paginas);

        panelCartelera.revalidate();
        panelCartelera.repaint();
    }

    public static void main(String[] args) {
        // Ejemplo: antes deberías cargar items en Principal.items
        Principal.items = new ArrayList<>();
        // Principal.items.add(new Movie(...));
        Principal.items.add(new Game(1,"a",50,""));
        new ImprimirTodo().setVisible(true);
    }
}
