package com.duoc.bibliotecapp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import com.duoc.bibliotecapp.controller.ControladorCategoria;
import com.duoc.bibliotecapp.controller.ControladorLibro;
import com.duoc.bibliotecapp.model.Categoria;
import com.duoc.bibliotecapp.model.Libro;

public class GestionLibros extends JFrame {

	private JTextField txtId;
	private JTextField txtTitulo;
	private JTextField txtAutor;
	private JTextField txtIsbn;
	private JTextField txtEditorial;
	private JTextField txtStock;
	private JComboBox<Categoria> cboCategoria;
	private JTable tablaRegistros;
	private DefaultTableModel modeloTabla;
	private final ControladorLibro controladorLibro;
	private final ControladorCategoria controladorCategoria;

	public GestionLibros(JFrame ventanaPadre, ControladorLibro ct1, ControladorCategoria ct2) {
		super("Gestión de libros");
		this.controladorLibro = ct1;
		this.controladorCategoria = ct2;
		setSize(900, 620);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		setResizable(false);
		configurarComponentes();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				ventanaPadre.setVisible(true);
			}
		});

		setVisible(true);
		txtTitulo.requestFocusInWindow();
	}

	private void configurarComponentes()
	{
		JPanel panelFormulario = new JPanel(new GridLayout(4, 4, 10, 8));
		panelFormulario.setBorder(new TitledBorder("Datos del libro"));

		txtId          = new JTextField();
		txtId.setEditable(false);
		txtTitulo      = new JTextField();
		txtAutor       = new JTextField();
		txtIsbn        = new JTextField();
		txtEditorial   = new JTextField();
		txtStock       = new JTextField();
		cboCategoria = new JComboBox<>();
		cargarCategorias();

		panelFormulario.add(new JLabel("ID:"));
		panelFormulario.add(txtId);
		panelFormulario.add(new JLabel("Título:"));
		panelFormulario.add(txtTitulo);
		panelFormulario.add(new JLabel("Autor:"));
		panelFormulario.add(txtAutor);
		panelFormulario.add(new JLabel("ISBN:"));
		panelFormulario.add(txtIsbn);
		panelFormulario.add(new JLabel("Editorial:"));
		panelFormulario.add(txtEditorial);
		panelFormulario.add(new JLabel("Stock:"));
		panelFormulario.add(txtStock);
		panelFormulario.add(new JLabel("Categoría:"));
		panelFormulario.add(cboCategoria);
		panelFormulario.add(new JLabel(""));
		panelFormulario.add(new JLabel(""));

		JButton btnGuardar = new JButton("Agregar");
		JButton btnActualizar = new JButton("Actualizar");
		JButton btnBorrar = new JButton("Borrar");
		JButton btnLimpiar = new JButton("Limpiar");
		btnGuardar.addActionListener(e -> guardarRegistro());
		btnActualizar.addActionListener(e -> actualizarRegistro());
		btnBorrar.addActionListener(e -> borrarRegistro());
		btnLimpiar.addActionListener(e -> limpiarCampos());

		JPanel panelBotones = new JPanel(new GridLayout(1, 4, 10, 0));
		panelBotones.add(btnGuardar);
		panelBotones.add(btnActualizar);
		panelBotones.add(btnBorrar);
		panelBotones.add(btnLimpiar);

		JPanel panelSuperior = new JPanel(new BorderLayout(8, 8));
		panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15));
		panelSuperior.add(panelFormulario, BorderLayout.CENTER);
		panelSuperior.add(panelBotones, BorderLayout.SOUTH);
		add(panelSuperior, BorderLayout.NORTH);

		String[] columnas = {"Id", "Titulo", "Autor", "ISBN", "Editorial", "Stock", "ID Cat.", "Categoria"};
		modeloTabla = new DefaultTableModel(columnas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaRegistros = new JTable(modeloTabla);
		tablaRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaRegistros.getSelectionModel().addListSelectionListener(e -> seleccionarLibro(e));

		JPanel panelTabla = new JPanel(new BorderLayout());
		panelTabla.setBorder(new TitledBorder("Libros registrados"));
		JScrollPane scrollPanel = new JScrollPane(tablaRegistros);
		scrollPanel.getViewport().setBackground(Color.WHITE);
		panelTabla.add(scrollPanel, BorderLayout.CENTER);
		add(panelTabla, BorderLayout.CENTER);

		cargarDatosTabla();
	}

	private void limpiarCampos() {
		txtId.setText("");
		txtTitulo.setText("");
		txtAutor.setText("");
		txtIsbn.setText("");
		txtEditorial.setText("");
		txtStock.setText("");
		if (cboCategoria.getItemCount() > 0) {
			cboCategoria.setSelectedIndex(0);
		}
		tablaRegistros.clearSelection();
		txtTitulo.requestFocusInWindow();
	}

	private void cargarDatosTabla() {
		try {
			modeloTabla.setRowCount(0);
			for (Libro libro : controladorLibro.listarLibros()) {
				modeloTabla.addRow(new Object[] {
						libro.getId(),
						libro.getTitulo(),
						libro.getAutor(),
						libro.getIsbn(),
						libro.getEditorial(),
						libro.getStock(),
						libro.getIdCategoria(),
						libro.getNombreCategoria()
				});
			}
		} catch (SQLException e) {
			mostrarError("No se pudieron cargar los libros.", e);
		}
	}

	private void seleccionarLibro(ListSelectionEvent evento) {
		if (!evento.getValueIsAdjusting() && tablaRegistros.getSelectedRow() >= 0) {
			int fila = tablaRegistros.convertRowIndexToModel(tablaRegistros.getSelectedRow());
			txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
			txtTitulo.setText(modeloTabla.getValueAt(fila, 1).toString());
			txtAutor.setText(modeloTabla.getValueAt(fila, 2).toString());
			txtIsbn.setText(modeloTabla.getValueAt(fila, 3).toString());
			txtEditorial.setText(modeloTabla.getValueAt(fila, 4).toString());
			txtStock.setText(modeloTabla.getValueAt(fila, 5).toString());
			seleccionarCategoria(Integer.parseInt(modeloTabla.getValueAt(fila, 6).toString()));
		}
	}

	private void guardarRegistro() {
		Libro libro = leerFormulario(0);
		if (libro == null) {
			return;
		}
		try {
			boolean resultado = controladorLibro.guardar(libro);
			if( resultado ){
				JOptionPane.showMessageDialog(null, "Libro agregado correctamente.");
			}
			limpiarCampos();
			cargarDatosTabla();
		} catch (SQLException e) {
			mostrarError("No se pudo guardar el libro.", e);
		}
	}

	private void actualizarRegistro() {
		if (txtId.getText().isEmpty()) {
			mostrarValidacion("Selecciona un libro para actualizar.");
			return;
		}
		Libro libro = leerFormulario(Integer.parseInt(txtId.getText()));
		if (libro == null) {
			return;
		}
		try {
			boolean resultado = controladorLibro.actualizar(libro);
			if( resultado ){
				JOptionPane.showMessageDialog(null, "Libro actualizado correctamente.");
			}
			limpiarCampos();
			cargarDatosTabla();
		} catch (SQLException e) {
			mostrarError("No se pudo actualizar el libro.", e);
		}
	}

	private void borrarRegistro() {
		if (txtId.getText().isEmpty()) {
			mostrarValidacion("Selecciona un libro para eliminar.");
			return;
		}
		try {
			boolean resultado = controladorLibro.borrar(Integer.parseInt(txtId.getText()));
			if( resultado ){
				JOptionPane.showMessageDialog(null, "Libro eliminado correctamente.");
			}
			limpiarCampos();
			cargarDatosTabla();
		} catch (SQLException e) {
			mostrarError("No se pudo eliminar el libro.", e);
		}
	}

	private Libro leerFormulario(int id) {
		String titulo     = txtTitulo.getText().trim();
		String autor      = txtAutor.getText().trim();
		String isbn       = txtIsbn.getText().trim();
		String editorial  = txtEditorial.getText().trim();
		String stockTexto = txtStock.getText().trim();
		Categoria categoria = (Categoria) cboCategoria.getSelectedItem();

		if( titulo.isEmpty() || autor.isEmpty() || isbn.isEmpty() || editorial.isEmpty() || stockTexto.isEmpty() || categoria == null ){
			mostrarValidacion("Completa todos los datos del libro.");
			return null;
		}

		try {
			int stock = Integer.parseInt(stockTexto);
			if (stock < 0) {
				mostrarValidacion("El stock no puede ser negativo.");
				return null;
			}
			return new Libro(id, titulo, autor, isbn, editorial, stock, categoria.getId());
		} catch (NumberFormatException e) {
			mostrarValidacion("El stock debe ser un número entero.");
			return null;
		}
	}

	private void cargarCategorias(){
		try {
			for (Categoria categoria : controladorCategoria.listarCategorias()) {
				cboCategoria.addItem(categoria);
			}
		} catch (SQLException e) {
			cboCategoria.setEnabled(false);
			mostrarError("No se pudieron cargar las categorías.", e);
		}
	}

	private void seleccionarCategoria(int idCategoria) {
		for (int i = 0; i < cboCategoria.getItemCount(); i++) {
			if (cboCategoria.getItemAt(i).getId() == idCategoria) {
				cboCategoria.setSelectedIndex(i);
				return;
			}
		}
		cboCategoria.setSelectedItem(null);
	}

	private void mostrarValidacion(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Datos inválidos", JOptionPane.WARNING_MESSAGE);
	}

	private void mostrarError(String mensaje, SQLException error) {
		System.err.println(mensaje + " ; " + error.getMessage());
		JOptionPane.showMessageDialog(this, mensaje + "\n" + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}
}
