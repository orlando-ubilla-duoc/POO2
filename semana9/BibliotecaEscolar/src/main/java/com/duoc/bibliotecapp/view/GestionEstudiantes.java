package com.duoc.bibliotecapp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import com.duoc.bibliotecapp.controller.ControladorEstudiante;
import com.duoc.bibliotecapp.controller.ControladorUsuario;
import com.duoc.bibliotecapp.model.Estudiante;
import com.duoc.bibliotecapp.model.Rol;
import com.duoc.bibliotecapp.model.Usuario;

public class GestionEstudiantes extends JFrame {

	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtRut;
	private JTextField txtCurso;
	private JTextField txtCorreo;
	private JPasswordField txtContrasena;
	private JTable tablaRegistros;
	private DefaultTableModel modeloTabla;
	private int idUsuarioSelected;
	private final ControladorEstudiante controladorEstudiante;
	private final ControladorUsuario controladorUsuario;

	public GestionEstudiantes(JFrame ventanaPadre, ControladorEstudiante ctr1, ControladorUsuario ctr2) {
		super("Gestión de estudiantes");
		this.controladorEstudiante = ctr1;
		this.controladorUsuario = ctr2;
		this.idUsuarioSelected = 0;
		setSize(720, 540);
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
		txtNombre.requestFocusInWindow();
	}

	private void configurarComponentes() {
		JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
		panelFormulario.setBorder(new TitledBorder("Datos del estudiante"));

		txtId     = new JTextField();
		txtId.setEditable(false);
		txtNombre = new JTextField();
		txtRut    = new JTextField();
		txtCurso  = new JTextField();
		txtCorreo = new JTextField();
		txtContrasena = new JPasswordField();

		panelFormulario.add(new JLabel("ID:"));
		panelFormulario.add(txtId);
		panelFormulario.add(new JLabel("Nombre:"));
		panelFormulario.add(txtNombre);
		panelFormulario.add(new JLabel("RUT:"));
		panelFormulario.add(txtRut);
		panelFormulario.add(new JLabel("Curso:"));
		panelFormulario.add(txtCurso);
		panelFormulario.add(new JLabel("Correo:"));
		panelFormulario.add(txtCorreo);
		panelFormulario.add(new JLabel("Contraseña:"));
		panelFormulario.add(txtContrasena);

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

		JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
		panelSuperior.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 5, 15));
		panelSuperior.add(panelFormulario, BorderLayout.CENTER);
		panelSuperior.add(panelBotones, BorderLayout.SOUTH);
		add(panelSuperior, BorderLayout.NORTH);

		JPanel panelTabla = new JPanel(new BorderLayout());
		panelTabla.setBorder(new TitledBorder("Estudiantes registrados"));
		String[] columnas = {"Id", "Nombre", "RUT", "Curso", "Correo"};
		modeloTabla = new DefaultTableModel(columnas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaRegistros = new JTable(modeloTabla);
		tablaRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaRegistros.getSelectionModel().addListSelectionListener(e -> seleccionarEstudiante(e));
		JScrollPane scrollPanel = new JScrollPane(tablaRegistros);
		scrollPanel.getViewport().setBackground(Color.WHITE);
		panelTabla.add(scrollPanel, BorderLayout.CENTER);
		add(panelTabla, BorderLayout.CENTER);

		cargarDatosTabla();
	}

	private void limpiarCampos() {
		txtId.setText("");
		txtNombre.setText("");
		txtRut.setText("");
		txtCurso.setText("");
		txtCorreo.setText("");
		tablaRegistros.clearSelection();
		txtNombre.requestFocusInWindow();
		txtContrasena.setText("");
		idUsuarioSelected = 0;
	}

	private void cargarDatosTabla() {
		try {
			modeloTabla.setRowCount(0);
			for (Estudiante estudiante : controladorEstudiante.listarEstudiantes()) {
				modeloTabla.addRow(new Object[] {
						estudiante.getId(),
						estudiante.getNombre(),
						estudiante.getRut(),
						estudiante.getCurso(),
						estudiante.getCorreo()
				});
			}
		} catch (SQLException e) {
			mostrarError("No se pudieron cargar los estudiantes.", e);
		}
	}

	private void seleccionarEstudiante(ListSelectionEvent evento) {
		if (!evento.getValueIsAdjusting() && tablaRegistros.getSelectedRow() >= 0) {
			int fila = tablaRegistros.convertRowIndexToModel(tablaRegistros.getSelectedRow());
			txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
			txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
			txtRut.setText(modeloTabla.getValueAt(fila, 2).toString());
			txtCurso.setText(modeloTabla.getValueAt(fila, 3).toString());
			txtCorreo.setText(modeloTabla.getValueAt(fila, 4).toString());
			try {
				Usuario usuario = this.controladorUsuario.obtenerPorRutYCorreo( txtRut.getText().trim(), txtCorreo.getText().trim());
				idUsuarioSelected = usuario.getId();
				txtContrasena.setText(usuario.getContrasena());
			} catch (Exception e) {
				idUsuarioSelected = 0;
				txtContrasena.setText("");
				JOptionPane.showMessageDialog(this, e.getMessage(),"Usuario no encontrado", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private void guardarRegistro() {
		if (!validarFormulario()) {
			return;
		}

		try {
			Estudiante newEstudiante = crearEstudiante(0);
			boolean resultado = controladorEstudiante.guardar(newEstudiante);
			if( resultado ){
				resultado = controladorUsuario.guardar(crearUsuario(newEstudiante));
				JOptionPane.showMessageDialog(null, "Estudiante agregado correctamente.");
			}
			limpiarCampos();
			cargarDatosTabla();
		} catch (SQLException e) {
			mostrarError("No se pudo guardar el estudiante.", e);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actualizarRegistro() {
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Selecciona un estudiante para actualizar.","Estudiante no seleccionado", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (!validarFormulario()) {
			return;
		}

		try {
			Estudiante editaEstudiante = crearEstudiante(Integer.parseInt(txtId.getText()));
			boolean resultado = controladorEstudiante.actualizar(editaEstudiante);
			if( resultado ){
				if( idUsuarioSelected!=0 ){
					controladorUsuario.actualizar(crearUsuario(editaEstudiante));
				} else {
					controladorUsuario.guardar(crearUsuario(editaEstudiante));
				}
				JOptionPane.showMessageDialog(null, "Estudiante actualizado correctamente.");
			}
			limpiarCampos();
			cargarDatosTabla();
		} catch (SQLException e) {
			mostrarError("No se pudo actualizar el estudiante.", e);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void borrarRegistro() {
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Selecciona un estudiante para eliminar.", "Estudiante no seleccionado", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			boolean resultado = controladorEstudiante.borrar(Integer.parseInt(txtId.getText()));
			if( resultado ){
				JOptionPane.showMessageDialog(null, "Estudiante eliminado correctamente.");
			}
			limpiarCampos();
			cargarDatosTabla();
		} catch (SQLException e) {
			mostrarError("No se pudo eliminar el estudiante.", e);
		}
	}

	private boolean validarFormulario() {
		if (txtNombre.getText().trim().isEmpty() || txtRut.getText().trim().isEmpty() || txtCurso.getText().trim().isEmpty() || txtCorreo.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Completa nombre, RUT, curso y correo.","Datos incompletos", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	private Estudiante crearEstudiante(int id) {
		return new Estudiante(
			id,
			txtNombre.getText().trim(),
			txtRut.getText().trim(),
			txtCurso.getText().trim(),
			txtCorreo.getText().trim()
		);
	}

	private Usuario crearUsuario(Estudiante estudiante) throws Exception {
		char[] contrasenaIngresada = txtContrasena.getPassword();
		if( contrasenaIngresada.length == 0 ){
			throw new Exception("Falto completar contraseña.");
		}
		return new Usuario(
			idUsuarioSelected,
			estudiante.getNombre(),
			estudiante.getRut(),
			estudiante.getCorreo(),
			new String(contrasenaIngresada),
			Rol.ESTUDIANTE
		);
	}

	private void mostrarError(String mensaje, SQLException error) {
		System.err.println(mensaje + " " + error.getMessage());
		JOptionPane.showMessageDialog(this, mensaje + "\n" + error.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
	}
}
