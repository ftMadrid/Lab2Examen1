package lab2examen1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ImprimirTodo extends JFrame {
    //You are my sunshine , My only sunshine

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
        setLayout(null);

        if (Principal.items.isEmpty()) {
            paginas = 1;
        } else {
            paginas = (int) Math.ceil(Principal.items.size() / 6.0);
        }

        panelCartelera = new JPanel(null);
        panelCartelera.setBounds(20, 20, 740, 550);
        add(panelCartelera);

        JPanel panel = new JPanel();
        panel.setBounds(250, 590, 300, 40);
        btnAnterior = new JButton("Anterior");
        btnSiguiente = new JButton("Siguiente");
        lblPagina = new JLabel("Pagina 1 de " + paginas);

        btnAnterior.addActionListener(e -> cambiarPagina(-1));
        btnSiguiente.addActionListener(e -> cambiarPagina(1));

        panel.add(btnAnterior);
        panel.add(lblPagina);
        panel.add(btnSiguiente);

        add(panel);

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(348, 630, 100, 30);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.setForeground(Color.red);
        btnRegresar.addActionListener(e -> {
            dispose();
            new Principal().setVisible(true);
        });
        add(btnRegresar);

        mostrarPagina();
        System.out.println("A1");
    }

    private void cambiarPagina(int dir) {
        paginaActual += dir;
        if (paginaActual < 0) {
            paginaActual = 0;
        }
        if (paginaActual >= paginas) {
            paginaActual = paginas - 1;
        }
        mostrarPagina();
    }

    private void mostrarPagina() {
        panelCartelera.removeAll();

        int inicio = paginaActual * 6;
        int fin = Math.min(inicio + 6, Principal.items.size());

        int x = 20, y = 20; //pos ini
        int ancho = 220, alto = 260; // tamano cartel
        int contador = 0;//Contador

        for (int i = inicio; i < fin; i++) {
            RentItem item = Principal.items.get(i);
            System.out.println("A");

            JPanel panelItem = new JPanel(null);
            panelItem.setBounds(x, y, ancho, alto);

            String ruta = item.getRuta();
            JLabel lblImg;
            if (ruta != null && !ruta.isEmpty()) {
                ImageIcon icon = new ImageIcon(ruta);
                Image img = icon.getImage().getScaledInstance(200, 180, Image.SCALE_SMOOTH);
                lblImg = new JLabel(new ImageIcon(img));
            } else {
                lblImg = new JLabel("Sin imagen");
                lblImg.setHorizontalAlignment(SwingConstants.CENTER);
            }
            lblImg.setBounds(10, 10, 200, 180);
            panelItem.add(lblImg);
            JTextArea txtInfo;
            String str = "";
            if (item instanceof Game) {
                str = ((Game) item).toString();
            } else if (item instanceof Movie) {
                str = ((Movie) item).toString();
            }
            txtInfo = new JTextArea(str);
            txtInfo.setEditable(false);
            txtInfo.setLineWrap(true);
            txtInfo.setWrapStyleWord(true);
            JScrollPane scroll = new JScrollPane(txtInfo);
            scroll.setBounds(10, 195, 200, 80);
            panelItem.add(scroll);

            panelCartelera.add(panelItem);

            contador++;
            if (contador % 3 == 0) { // pasa a la siguiente fila
                x = 20;
                y += alto + 10;
            } else {
                x += ancho + 10;
            }
        }

        lblPagina.setText("Página " + (paginaActual + 1) + " de " + paginas);

        panelCartelera.revalidate();
        panelCartelera.repaint();
    }

    public static void main(String[] args) {
        Principal.items = new ArrayList<>();
        new ImprimirTodo().setVisible(true);
    }
}
