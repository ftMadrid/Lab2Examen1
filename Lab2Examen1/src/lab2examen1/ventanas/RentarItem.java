package lab2examen1.ventanas;

import lab2examen1.Principal;
import lab2examen1.RentItem;

import javax.swing.*;
import java.awt.*;

public class RentarItem extends JFrame {

    private final JLabel titulo = new JLabel("Consultar Renta");
    private final JLabel codigoLabel = new JLabel("Código del Item:");
    private final JTextField txtCodigo = new JTextField();
    private final JButton btnConsultar = new JButton("Consultar");
    private final JTextArea txtResultado = new JTextArea();
    private final JScrollPane scrollResultado = new JScrollPane(txtResultado);

    public RentarItem() {
        initVentana();
        initComponentes();
    }

    private void initVentana() {
        setSize(700, 500);
        setTitle("LAB. DE PROGRAMACIÓN II | CONSULTAR RENTA");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
    }

    private void initComponentes() {
        // Titulo
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 42));
        titulo.setBounds(30, 10, 500, 60);
        add(titulo);

        // Etiqueta y campo código
        codigoLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        codigoLabel.setBounds(30, 80, 250, 40);
        add(codigoLabel);

        txtCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        txtCodigo.setBounds(30, 130, 250, 40);
        add(txtCodigo);

        // Botón consultar
        btnConsultar.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnConsultar.setBounds(300, 130, 200, 40);
        btnConsultar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConsultar.addActionListener(e -> consultarItem());
        add(btnConsultar);

        // Area de resultado
        txtResultado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtResultado.setEditable(false);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);

        scrollResultado.setBounds(30, 200, 640, 230);
        add(scrollResultado);
    }

    private void consultarItem() {
        txtResultado.setText(""); // limpiar resultado

        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            RentItem encontrado = null;

            for (RentItem item : Principal.items) {
                if (item.getCodigo() == codigo) {
                    encontrado = item;
                    break;
                }
            }

            if (encontrado != null) {
                // Mostrar info del item
                txtResultado.setText("Datos del Item:\n" + encontrado.toString());

                // Pedir días de renta
                String diasStr = JOptionPane.showInputDialog(this, "Ingrese cantidad de días para renta:");
                if (diasStr != null) {
                    int dias = Integer.parseInt(diasStr);
                    double total = encontrado.pagoRenta(dias);
                    txtResultado.append("\n\nTotal a pagar por " + dias + " días: " + total);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Item no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Código o días inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ConsultarRentaGUI().setVisible(true);
    }
}