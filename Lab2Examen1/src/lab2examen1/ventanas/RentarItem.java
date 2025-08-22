package lab2examen1.ventanas;

import lab2examen1.Principal;
import lab2examen1.RentItem;

import javax.swing.*;
import java.awt.*;

public class RentarItem extends JFrame {

    private final JLabel titulo = new JLabel("Consultar Renta");
    private final JLabel codigoLabel = new JLabel("Código del Item:");
    private final JTextField txtCodigo = new JTextField();

    private final JLabel nombreLabel = new JLabel("Nombre:");
    private final JTextField txtNombre = new JTextField();

    private final JLabel precioLabel = new JLabel("Precio de Renta:");
    private final JTextField txtPrecio = new JTextField();

    private final JLabel diasLabel = new JLabel("Días de Renta:");
    private final JTextField txtDias = new JTextField();

    private final JLabel totalLabel = new JLabel("Total a Pagar:");
    private final JTextField txtTotal = new JTextField();

    private final JButton btnConsultar = new JButton("Consultar");
    private final JButton btnRegresar = new JButton("Regresar"); // nuevo botón

    public RentarItem() {
        initVentana();
        initComponentes();
    }

    private void initVentana() {
        setSize(700, 550); // un poco más de altura para el botón
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

        // Código
        codigoLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        codigoLabel.setBounds(30, 80, 250, 40);
        add(codigoLabel);

        txtCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        txtCodigo.setBounds(250, 80, 200, 40);
        add(txtCodigo);

        btnConsultar.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnConsultar.setBounds(470, 80, 150, 40);
        btnConsultar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConsultar.addActionListener(e -> consultarItem());
        add(btnConsultar);

        // Nombre
        nombreLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        nombreLabel.setBounds(30, 140, 200, 30);
        add(nombreLabel);

        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        txtNombre.setBounds(250, 140, 370, 30);
        txtNombre.setEditable(false);
        add(txtNombre);

        // Precio
        precioLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        precioLabel.setBounds(30, 190, 200, 30);
        add(precioLabel);

        txtPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        txtPrecio.setBounds(250, 190, 150, 30);
        txtPrecio.setEditable(false);
        add(txtPrecio);

        // Días
        diasLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        diasLabel.setBounds(30, 240, 200, 30);
        add(diasLabel);

        txtDias.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        txtDias.setBounds(250, 240, 150, 30);
        add(txtDias);

        // Total
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        totalLabel.setBounds(30, 290, 200, 30);
        add(totalLabel);

        txtTotal.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        txtTotal.setBounds(250, 290, 150, 30);
        txtTotal.setEditable(false);
        add(txtTotal);

        // Botón Regresar
        btnRegresar.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnRegresar.setBounds(500, 400, 150, 40);
        btnRegresar.setForeground(Color.RED);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(e -> {
            dispose(); // cerrar esta ventana
            new Principal().setVisible(true); // abrir ventana Principal
        });
        add(btnRegresar);
    }

    private void consultarItem() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            RentItem encontrado = null;

            for (RentItem item : Principal.items) {
                if (item.getCodigo() == codigo) {
                    encontrado = item;
                    break;
                }
            }

            if (encontrado != null && !txtDias.getText().trim().isEmpty()) {
                // Mostrar datos
                txtNombre.setText(encontrado.getNombre());
                txtPrecio.setText(String.valueOf(encontrado.getPrecio_renta()));

                // Calcular total si días ingresados
                String diasStr = txtDias.getText();
                if (!diasStr.isEmpty()) {
                    int dias = Integer.parseInt(diasStr);
                    double total = encontrado.pagoRenta(dias);
                    txtTotal.setText(String.valueOf(total));
                } else {
                    txtTotal.setText("");
                }

            }else if (txtDias.getText().trim().isEmpty())
            {
            JOptionPane.showMessageDialog(this, "Ocupas poner la cantidad de dias a rentar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            else {
                JOptionPane.showMessageDialog(this, "Item no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                txtNombre.setText("");
                txtPrecio.setText("");
                txtDias.setText("");
                txtTotal.setText("");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Código o días inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new RentarItem().setVisible(true);
    }
}