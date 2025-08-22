package lab2examen1.ventanas;

import lab2examen1.Movie;
import lab2examen1.Game;
import lab2examen1.RentItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import lab2examen1.Principal;

public class AgregarItem extends JFrame {

    public ArrayList<RentItem> items = Principal.items;

    public AgregarItem() {
        initVentana();
        initComponentes();
    }

    private void initVentana() {
        setSize(700, 600);
        setTitle("LAB. DE PROGRAMACIÓN II | EXAMEN PRIMER PARCIAL");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
    }

    private void initComponentes() {

        titulo.setFont(new Font("Segoe UI", Font.BOLD, 52));
        titulo.setBounds(30, 10, 500, 150);

        codigoLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        codigoLabel.setBounds(30, 150, 300, 100);

        txtCodigo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        txtCodigo.setBounds(30, 220, 300, 40);

        nombreLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        nombreLabel.setBounds(30, 240, 300, 100);

        txtNombre.setFont(new Font("Segoe UI", Font.BOLD, 22));
        txtNombre.setBounds(30, 310, 300, 40);

        precioLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        precioLabel.setBounds(30, 330, 300, 100);

        txtPrecio.setFont(new Font("Segoe UI", Font.BOLD, 22));
        txtPrecio.setBounds(30, 400, 300, 40);

        txtRuta.setFont(new Font("Segoe UI", Font.BOLD, 12));
        txtRuta.setBounds(30, 480, 300, 40);

        btnImagen.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnImagen.setBounds(360, 480, 220, 40);
        btnImagen.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnImagen.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            int seleccion = fc.showOpenDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                rutaImagen = fc.getSelectedFile().getAbsolutePath();
                txtRuta.setText(rutaImagen);
            }
        });

        btnAgregar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnAgregar.setBounds(360, 300, 220, 40);
        btnAgregar.setForeground(Color.GREEN);
        btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgregar.addActionListener(e -> {

            agregarItem();

        });

        btnRegresar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnRegresar.setBounds(360, 350, 220, 40);
        btnRegresar.setForeground(Color.red);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(e -> {

            dispose();
            new Principal().setVisible(true);

        });

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbMovie);
        grupo.add(rbGame);

        rbMovie.setFont(new Font("Segoe UI", Font.BOLD, 22));
        rbMovie.setBounds(420, 70, 120, 40);

        rbMovie.addItemListener(e -> {
            if (rbMovie.isSelected()) {
                txtPrecio.setEditable(true);
                txtPrecio.setEnabled(true);
                txtPrecio.setText("");
            }
        });

        rbGame.setFont(new Font("Segoe UI", Font.BOLD, 22));
        rbGame.setBounds(540, 70, 120, 40);

        rbGame.addItemListener(e -> {
            if (rbGame.isSelected()) {
                txtPrecio.setEditable(false);
                txtPrecio.setEnabled(false);
                txtPrecio.setText("N/A");
            }
        });

        add(titulo);
        add(codigoLabel);
        add(txtCodigo);
        add(nombreLabel);
        add(txtNombre);
        add(precioLabel);
        add(txtPrecio);
        add(txtRuta);
        add(btnImagen);
        add(btnAgregar);
        add(btnRegresar);
        add(rbMovie);
        add(rbGame);

    }

    private void agregarItem() {
        try {
            // Validar campos vacíos
            if (txtCodigo.getText().isEmpty()
                    || txtNombre.getText().isEmpty()
                    || txtRuta.getText().isEmpty()
                    || (!rbMovie.isSelected() && !rbGame.isSelected())
                    || (rbMovie.isSelected() && txtPrecio.getText().isEmpty())) {

                JOptionPane.showMessageDialog(this, "Tiene que llenar todos los campos!", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int codigo = Integer.parseInt(txtCodigo.getText());

            for (RentItem i : items) {
                if (i.getCodigo() == codigo) {
                    JOptionPane.showMessageDialog(this, "El codigo ya existe!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String nombre = txtNombre.getText();

            RentItem nuevo;
            if (rbMovie.isSelected()) {
                double precio = Double.parseDouble(txtPrecio.getText());
                nuevo = new Movie(codigo, nombre, precio, rutaImagen);
            } else {
                nuevo = new Game(codigo, nombre, rutaImagen);
            }

            items.add(nuevo);
            JOptionPane.showMessageDialog(this, "Item agregado correctamente.");
            dispose();
            new Principal().setVisible(true);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Datos invalidos.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private final JLabel titulo = new JLabel("Agregar Item");
    private final JRadioButton rbMovie = new JRadioButton("Movie");
    private final JRadioButton rbGame = new JRadioButton("Game");
    private final JLabel codigoLabel = new JLabel("Codigo:");
    private final JLabel nombreLabel = new JLabel("Nombre:");
    private final JLabel precioLabel = new JLabel("Precio de Renta:");
    private final JTextField txtCodigo = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtPrecio = new JTextField();
    private final JTextField txtRuta = new JTextField();
    private final JButton btnImagen = new JButton("Seleccionar Imagen");
    private final JButton btnAgregar = new JButton("Agregar");
    private final JButton btnRegresar = new JButton("Regresar");
    private String rutaImagen = "";

    public static void main(String[] args) {
        new AgregarItem().setVisible(true);
    }
}
