package com.duoc.bibliotecapp.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.duoc.bibliotecapp.controller.ControladorEstudiante;
import com.duoc.bibliotecapp.controller.ControladorPrestamo;
import com.duoc.bibliotecapp.model.Estudiante;
import com.duoc.bibliotecapp.model.LibroMasPrestado;
import com.duoc.bibliotecapp.model.Prestamo;

public class VistaReportes extends JFrame {

	private static final String REPORTE_LIBROS_MAS_PRESTADOS = "Libros más prestados";
	private static final String REPORTE_POR_ESTUDIANTE = "Préstamos por estudiante";
	private static final String REPORTE_ACTUALES = "Préstamos actuales";

	private final ControladorPrestamo controladorPrestamo;
	private final ControladorEstudiante controladorEstudiante;
	private JComboBox<String> cboTipoReporte;
	private JComboBox<Estudiante> cboEstudiante;
	private JButton btnBuscar;
	private JTable tablaResultados;
	private DefaultTableModel modeloTabla;

	public VistaReportes( JFrame ventanaPadre, ControladorPrestamo ct1, ControladorEstudiante ct2 ){
        super("Reportes");
		this.controladorPrestamo   = ct1;
		this.controladorEstudiante = ct2;
		setSize(850, 520);
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
	}

	private void configurarComponentes() {
		JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
		panelFiltros.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10));
		panelFiltros.add(new JLabel("Tipo de búsqueda:"));

		cboTipoReporte = new JComboBox<>(new String[] {
				REPORTE_LIBROS_MAS_PRESTADOS,
				REPORTE_POR_ESTUDIANTE,
				REPORTE_ACTUALES
		});
		panelFiltros.add(cboTipoReporte);

		panelFiltros.add(new JLabel("Estudiante:"));
		cboEstudiante = new JComboBox<>();
		cargarEstudiantes();
		panelFiltros.add(cboEstudiante);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(e -> buscarReporte());
		panelFiltros.add(btnBuscar);
		cboTipoReporte.addActionListener(e -> actualizarFiltroEstudiante());
		actualizarFiltroEstudiante();

		add(panelFiltros, BorderLayout.NORTH);

		modeloTabla = crearModeloTabla("Seleccione un reporte y presione Buscar");
		tablaResultados = new JTable(modeloTabla);
		tablaResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JPanel panelResultados = new JPanel(new BorderLayout());
		panelResultados.setBorder(new TitledBorder("Resultados"));
		panelResultados.add(new JScrollPane(tablaResultados), BorderLayout.CENTER);
		add(panelResultados, BorderLayout.CENTER);
	}

	private void cargarEstudiantes() {
		try {
			for (Estudiante estudiante : controladorEstudiante.listarEstudiantes()) {
				cboEstudiante.addItem(estudiante);
			}
		} catch (SQLException e) {
			mostrarError("No se pudo cargar la lista de estudiantes.", e);
		}
	}

	private void actualizarFiltroEstudiante() {
		boolean requiereEstudiante = REPORTE_POR_ESTUDIANTE.equals(cboTipoReporte.getSelectedItem());
		cboEstudiante.setEnabled(requiereEstudiante);
	}

	private void buscarReporte() {
		String tipoReporte = (String) cboTipoReporte.getSelectedItem();
		try {
			if (REPORTE_LIBROS_MAS_PRESTADOS.equals(tipoReporte)) {
				mostrarLibrosMasPrestados(controladorPrestamo.listarLibrosMasPrestados());
			} else if (REPORTE_POR_ESTUDIANTE.equals(tipoReporte)) {
				Estudiante estudiante = (Estudiante) cboEstudiante.getSelectedItem();
				if (estudiante == null) {
					JOptionPane.showMessageDialog(this, "No hay estudiantes disponibles para consultar.","Sin estudiantes", JOptionPane.WARNING_MESSAGE);
					return;
				}
				mostrarPrestamos(controladorPrestamo.listarPrestamosPorEstudiante(estudiante.getId()));
			} else {
				mostrarPrestamos(controladorPrestamo.listarPrestamosActuales());
			}
		} catch (SQLException e) {
			mostrarError("No se pudo ejecutar la búsqueda.", e);
		}
	}

	private void mostrarLibrosMasPrestados(List<LibroMasPrestado> libros) {
		modeloTabla = crearModeloTabla("Id libro", "Titulo", "Autor", "Cantidad Prestamos");
		for (LibroMasPrestado libro : libros) {
			modeloTabla.addRow(new Object[] {
				libro.getIdLibro(),
				libro.getTitulo(),
				libro.getAutor(),
				libro.getCantidadPrestamos()
			});
		}
		actualizarTabla();
	}

	private void mostrarPrestamos(List<Prestamo> prestamos) {
		modeloTabla = crearModeloTabla(
				"Id prestamo",
				"Estudiante",
				"RUT",
				"Curso",
				"Libro",
				"Fecha prestamo",
				"Fecha devolucion",
				"Devuelto?");
		for (Prestamo prestamo : prestamos) {
			modeloTabla.addRow(new Object[] {
					prestamo.getId(),
					prestamo.getNombreEstudiante(),
					prestamo.getRutEstudiante(),
					prestamo.getCursoEstudiante(),
					prestamo.getTituloLibro(),
					formatearFecha(prestamo.getFechaPrestamo()),
					formatearFecha(prestamo.getFechaDevolucion()),
					prestamo.isDevuelto() ? "Sí" : "No"
			});
		}
		actualizarTabla();
	}

	private DefaultTableModel crearModeloTabla(String... columnas) {
		return new DefaultTableModel(columnas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}

	private void actualizarTabla() {
		tablaResultados.setModel(modeloTabla);
		tablaResultados.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}

	private String formatearFecha(java.time.LocalDate fecha) {
		return fecha == null ? "" : fecha.toString();
	}

	private void mostrarError(String mensaje, SQLException error) {
		System.err.println(mensaje + " " + error.getMessage());
		JOptionPane.showMessageDialog(this, mensaje + "\n" + error.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
	}
}
