package com.duoc.bibliotecapp.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

import com.duoc.bibliotecapp.controller.ControladorEstudiante;
import com.duoc.bibliotecapp.controller.ControladorLibro;
import com.duoc.bibliotecapp.controller.ControladorPrestamo;
import com.duoc.bibliotecapp.model.Estudiante;
import com.duoc.bibliotecapp.model.Libro;
import com.duoc.bibliotecapp.model.Prestamo;

public class RegistroPrestamosDevoluciones extends JFrame {

	private JTextField txtId;
	private JTextField txtFechaPrestamo;
	private JTextField txtFechaDevolucion;
	private JComboBox<Estudiante> cboEstudiante;
	private JComboBox<Libro> cboLibro;
	private JTable tablaRegistros;
	private DefaultTableModel modeloTabla;
	private final ControladorPrestamo controladorPrestamo;
	private final ControladorEstudiante controladorEstudiante;
	private final ControladorLibro controladorLibro;
	private List<Prestamo> prestamos = new ArrayList<>();

	public RegistroPrestamosDevoluciones( JFrame ventanaPadre, ControladorPrestamo ct1, ControladorEstudiante ct2, ControladorLibro ct3 ){
		super("Prestamos y Devoluciones");
		this.controladorPrestamo   = ct1;
		this.controladorEstudiante = ct2;
		this.controladorLibro      = ct3;
		setSize(850, 620);
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
		cboEstudiante.requestFocusInWindow();
	}

	private void configurarComponentes() {
		JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

		JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 8));
		panelFormulario.setBorder(new TitledBorder("Registro de préstamo"));

		txtId              = new JTextField();
		txtId.setEditable(false);
		cboEstudiante      = new JComboBox<>();
		cboLibro           = new JComboBox<>();
		txtFechaPrestamo   = new JTextField(LocalDate.now().toString());
		txtFechaDevolucion = new JTextField();

		cargarOpcionesEstudiantesYLibros();

		panelFormulario.add(new JLabel("ID:"));
		panelFormulario.add(txtId);
		panelFormulario.add(new JLabel("Estudiante:"));
		panelFormulario.add(cboEstudiante);
		panelFormulario.add(new JLabel("Libro:"));
		panelFormulario.add(cboLibro);
		panelFormulario.add(new JLabel("Fecha préstamo (AAAA-MM-DD):"));
		panelFormulario.add(txtFechaPrestamo);
		panelFormulario.add(new JLabel("Fecha devolución (AAAA-MM-DD):"));
		panelFormulario.add(txtFechaDevolucion);

		JButton btnPedir = new JButton("Pedir libro");
		JButton btnDevolver = new JButton("Devolver libro");
		//JButton btnBorrar = new JButton("Borrar");
		JButton btnLimpiar = new JButton("Limpiar");
		btnPedir.addActionListener(e -> pedirLibro());
		btnDevolver.addActionListener(e -> devolverLibro());
		//btnBorrar.addActionListener(e -> borrarRegistro());
		btnLimpiar.addActionListener(e -> limpiarCampos());

		JPanel panelBotones = new JPanel(new GridLayout(1, 4, 8, 0));
		panelBotones.add(btnPedir);
		panelBotones.add(btnDevolver);
		//panelBotones.add(btnBorrar);
		panelBotones.add(btnLimpiar);

		JPanel panelSuperior = new JPanel(new BorderLayout(8, 8));
		panelSuperior.add(panelFormulario, BorderLayout.CENTER);
		panelSuperior.add(panelBotones, BorderLayout.SOUTH);
		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

		String[] columnas = {
				"Id", "Estudiante", "Libro", "Fecha prestamo", "Fecha devolucion", "Devuelto?", "Stock"
		};
		modeloTabla = new DefaultTableModel(columnas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaRegistros = new JTable(modeloTabla);
		tablaRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaRegistros.getSelectionModel().addListSelectionListener(e -> seleccionarPrestamo(e));

		JPanel panelTabla = new JPanel(new BorderLayout());
		panelTabla.setBorder(new TitledBorder("Préstamos registrados"));
		panelTabla.add(new JScrollPane(tablaRegistros), BorderLayout.CENTER);
		panelPrincipal.add(panelTabla, BorderLayout.CENTER);
		add(panelPrincipal, BorderLayout.CENTER);

		cargarPrestamos();
	}

	private void cargarOpcionesEstudiantesYLibros() {
		try {
			for (Estudiante estudiante : controladorEstudiante.listarEstudiantes()) {
				cboEstudiante.addItem(estudiante);
			}
			for (Libro libro : controladorLibro.listarLibros()) {
				cboLibro.addItem(libro);
			}
		} catch (SQLException e) {
			mostrarError("No se pudieron cargar estudiantes y libros.", e);
		}
	}

	private void cargarPrestamos() {
		try {
			prestamos = controladorPrestamo.listarPrestamos();
			modeloTabla.setRowCount(0);
			for (Prestamo prestamo : prestamos) {
				modeloTabla.addRow(new Object[] {
						prestamo.getId(),
						textoCeldaEstudiante(prestamo.getIdEstudiante()),
						textoCeldaLibro(prestamo.getIdLibro()),
						formatearFecha(prestamo.getFechaPrestamo()),
						formatearFecha(prestamo.getFechaDevolucion()),
						prestamo.isDevuelto() ? "Sí" : "No"
				});
			}
		} catch (SQLException e) {
			mostrarError("No se pudieron cargar los préstamos.", e);
		}
	}

	private String textoCeldaEstudiante(int idEstudiante) {
		for (int i = 0; i < cboEstudiante.getItemCount(); i++) {
			Estudiante estudiante = cboEstudiante.getItemAt(i);
			if (estudiante.getId() == idEstudiante) {
				return estudiante.getId() + " - " + estudiante.getNombre();
			}
		}
		return Integer.toString(idEstudiante);
	}

	private String textoCeldaLibro(int idLibro) {
		for (int i = 0; i < cboLibro.getItemCount(); i++) {
			Libro libro = cboLibro.getItemAt(i);
			if (libro.getId() == idLibro) {
				return libro.getId() + " - " + libro.getTitulo();
			}
		}
		return Integer.toString(idLibro);
	}

	private String formatearFecha(LocalDate fecha) {
		return fecha == null ? "" : fecha.toString();
	}

	private void seleccionarPrestamo(ListSelectionEvent evento)
	{
		if (evento.getValueIsAdjusting() || tablaRegistros.getSelectedRow() < 0) {
			return;
		}

		int fila = tablaRegistros.convertRowIndexToModel(tablaRegistros.getSelectedRow());
		int idPrestamo = (int) modeloTabla.getValueAt(fila, 0);
		for (Prestamo prestamo : prestamos) {
			if (prestamo.getId() == idPrestamo) {
				txtId.setText(Integer.toString(prestamo.getId()));
				seleccionarEstudiante(prestamo.getIdEstudiante());
				seleccionarLibro(prestamo.getIdLibro());
				txtFechaPrestamo.setText(formatearFecha(prestamo.getFechaPrestamo()));
				txtFechaDevolucion.setText(formatearFecha(prestamo.getFechaDevolucion()));
				cboEstudiante.setEnabled(false);
				cboLibro.setEnabled(false);
				txtFechaPrestamo.setEditable(false);
				txtFechaDevolucion.setEditable(false);
				break;
			}
		}
	}

	private void seleccionarEstudiante(int idEstudiante) {
		for (int i = 0; i < cboEstudiante.getItemCount(); i++) {
			if (cboEstudiante.getItemAt(i).getId() == idEstudiante) {
				cboEstudiante.setSelectedIndex(i);
				return;
			}
		}
	}

	private void seleccionarLibro(int idLibro) {
		for (int i = 0; i < cboLibro.getItemCount(); i++) {
			if (cboLibro.getItemAt(i).getId() == idLibro) {
				cboLibro.setSelectedIndex(i);
				return;
			}
		}
	}

	private void pedirLibro() {
		try {
			boolean resultado = controladorPrestamo.guardar( crearPrestamo( 0, false));
			if( resultado ){
				Thread hilo = new Thread(new Runnable() {
					@Override
					public void run(){
						try {
							Libro libro = (Libro) cboLibro.getSelectedItem();
							controladorLibro.actualizaStock( libro.getId(), -1);
						} catch (SQLException e) {
							mostrarError("No se pudo actualizar el Stock.", e);
						}
					}
				});
				hilo.start();
			}
			limpiarCampos();
			cargarPrestamos();
			JOptionPane.showMessageDialog(this, "El Prestamo ha sido registrado.", "Prestamos",JOptionPane.INFORMATION_MESSAGE);
		} catch (IllegalArgumentException e) {
			mostrarValidacion(e.getMessage());
		} catch (SQLException e) {
			mostrarError("No se pudo guardar el préstamo.", e);
		}/* catch (InterruptedException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
		}*/
	}

	private void devolverLibro() {
		if (txtId.getText().isEmpty()) {
			mostrarValidacion("Selecciona un préstamo para devolver.");
			return;
		}
		try {
			Prestamo devolucion = crearPrestamo(Integer.parseInt(txtId.getText()), true);
			devolucion.setFechaDevolucion(LocalDate.now());
			boolean resultado = controladorPrestamo.actualizar(devolucion);
			if( resultado ){
				Thread hilo = new Thread(new Runnable() {
					@Override
					public void run(){
						try {
							Libro libro = (Libro) cboLibro.getSelectedItem();
							controladorLibro.actualizaStock( libro.getId(), 1);
						} catch (SQLException e) {
							mostrarError("No se pudo actualizar el Stock.", e);
						}
					}
				});
				hilo.start();
			}
			limpiarCampos();
			cargarPrestamos();
			JOptionPane.showMessageDialog(this, "El Libro ha sido devuelto.", "Prestamos",JOptionPane.INFORMATION_MESSAGE);
		} catch (IllegalArgumentException e) {
			mostrarValidacion(e.getMessage());
		} catch (SQLException e) {
			mostrarError("No se pudo actualizar el préstamo.", e);
		}
	}

	private void borrarRegistro(){
		if (txtId.getText().isEmpty()) {
			mostrarValidacion("Selecciona un préstamo para eliminar.");
			return;
		}
		try {
			controladorPrestamo.borrar(Integer.parseInt(txtId.getText()));
			limpiarCampos();
			cargarPrestamos();
		} catch (SQLException e) {
			mostrarError("No se pudo eliminar el préstamo.", e);
		}
	}

	private Prestamo crearPrestamo(int id, boolean esDevolucion)
	{
		Estudiante estudiante = (Estudiante) cboEstudiante.getSelectedItem();
		Libro libro = (Libro) cboLibro.getSelectedItem();
		if (estudiante == null || libro == null) {
			throw new IllegalArgumentException("Registra al menos un estudiante y un libro antes de crear préstamos.");
		}

		String fechaPrestamoTexto = txtFechaPrestamo.getText().trim();
		if (fechaPrestamoTexto.isEmpty()) {
			throw new IllegalArgumentException("Ingresa la fecha del préstamo.");
		}
		try {
			LocalDate fechaPrestamo = LocalDate.parse(fechaPrestamoTexto);
			String fechaDevolucionTexto = txtFechaDevolucion.getText().trim();
			LocalDate fechaDevolucion = fechaDevolucionTexto.isEmpty() ? null : LocalDate.parse(fechaDevolucionTexto);
			return new Prestamo(
				id,
				estudiante.getId(),
				libro.getId(),
				fechaPrestamo,
				fechaDevolucion,
				esDevolucion
			);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Las fechas deben tener el formato AAAA-MM-DD.");
		}
	}

	private void limpiarCampos() {
		txtId.setText("");
		txtFechaPrestamo.setText(LocalDate.now().toString());
		txtFechaDevolucion.setText("");
		if (cboEstudiante.getItemCount() > 0) {
			cboEstudiante.setSelectedIndex(0);
		}
		if (cboLibro.getItemCount() > 0) {
			cboLibro.setSelectedIndex(0);
		}
		tablaRegistros.clearSelection();
		cboEstudiante.requestFocusInWindow();
		cboEstudiante.setEnabled(true);
		cboLibro.setEnabled(true);
		txtFechaPrestamo.setEditable(true);
		txtFechaDevolucion.setEditable(true);
	}

	private void mostrarValidacion(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Datos inválidos", JOptionPane.WARNING_MESSAGE);
	}

	private void mostrarError(String mensaje, SQLException error) {
		System.err.println(mensaje + " " + error.getMessage());
		JOptionPane.showMessageDialog(this, mensaje + "\n" + error.getMessage(), "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}
