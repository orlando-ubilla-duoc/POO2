package com.duoc.speedfastapp.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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

import com.duoc.speedfastapp.controller.ControladorRepartidor;
import com.duoc.speedfastapp.model.Repartidor;

public class VentanaRegistroRepartidor extends JFrame {

	private JTextField txtId;
	private JTextField txtNombre;
	private JButton btnGuardar;
	private JTable tablaRegistros;
	private DefaultTableModel modeloTabla;
	private ControladorRepartidor controlador;

	public VentanaRegistroRepartidor(String titulo, JFrame ventanaPadre, ControladorRepartidor ctr)
	{
		super(titulo);
		this.controlador = ctr;
		setSize(600,480);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		setResizable(false);
		ConfigurarComponentes();

		// registro evento al cerrar esta ventana
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				ventanaPadre.setVisible(true);
			}

			@Override
			public void windowClosed(WindowEvent e){
				ventanaPadre.setVisible(true);
			}
		});

		setVisible(true);

	}

	private void ConfigurarComponentes(){
		// Panel para campos de texto
		JPanel panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridLayout( 3, 4, 10, 10));
		//panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelFormulario.setBorder(new TitledBorder("Registro"));

		// row 1
		txtId = new JTextField();
		txtId.setEditable(false);
		panelFormulario.add(new JLabel("Id repartidor:"));
		panelFormulario.add(txtId);
		panelFormulario.add(new JLabel(""));
		panelFormulario.add(new JLabel(""));

		// row 2
		txtNombre = new JTextField();
		panelFormulario.add(new JLabel("Nombre repartidor:"));
		panelFormulario.add(txtNombre);
		panelFormulario.add(new JLabel(""));
		panelFormulario.add(new JLabel(""));

		// row 3
		btnGuardar = new JButton("Agregar");
		JButton btnActualiza = new JButton("Actualizar");
		JButton btnBorrar = new JButton("Borrar");

		btnGuardar.addActionListener(e -> guardarRegistro());
		btnActualiza.addActionListener(e -> actualizarRegistro());
		btnBorrar.addActionListener(e -> borrarRegistro());

		panelFormulario.add(new JLabel(""));
		panelFormulario.add(btnGuardar);
		panelFormulario.add(btnActualiza);
		panelFormulario.add(btnBorrar);
		add(panelFormulario, BorderLayout.CENTER);

		JPanel panelGrid = new JPanel();
		panelGrid.setBorder(new TitledBorder("Datos existentes"));
		String[] columnas = {
			"ID",
			"Nombre repartidor"
		};
		modeloTabla = new DefaultTableModel(columnas, 0);
		tablaRegistros = new JTable(modeloTabla);
		tablaRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaRegistros.getSelectionModel().addListSelectionListener(e -> tablaClickEvt(e));
		JScrollPane scrollPanel = new JScrollPane(tablaRegistros);
		panelGrid.add(scrollPanel, BorderLayout.CENTER);
		add(panelGrid, BorderLayout.CENTER);

		cargaDatosTabla();

	}

	/**
	 * Para limpiar campos cada vez que se completa una accion
	 */
	private void limpiarTextfields(){
		txtId.setText("");
		txtNombre.setText("");
	}

	/**
	 * Obtiene informacion desde base de datos y llena grilla de datos.
	 */
	private void cargaDatosTabla()
	{
		try {
			modeloTabla.setRowCount(0);
			for(Repartidor r:this.controlador.listarTodos()){
				modeloTabla.addRow(new Object[]{
					r.getId(), r.getNombre()
				});
			}
		} catch (SQLException e) {
			System.err.print(e);
			JOptionPane.showMessageDialog(this,"No se pudo cargar la informacion: "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	private void tablaClickEvt(ListSelectionEvent lst)
	{
		if( !lst.getValueIsAdjusting() && tablaRegistros.getSelectedRow() >= 0){
			int fila = tablaRegistros.getSelectedRow();
			txtId.setText(modeloTabla.getValueAt(fila,0).toString());
			txtNombre.setText(modeloTabla.getValueAt(fila,1).toString());
		}
	}

	private void guardarRegistro()
	{
		String nombre = txtNombre.getText().trim();
		if (nombre.isEmpty()){
			JOptionPane.showMessageDialog(this,"Por favor complete todos los campos.","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			Repartidor nuevoRepartidor = new Repartidor(0, nombre, "");
			this.controlador.guardar(nuevoRepartidor);
		} catch(SQLException e){
			System.err.print(e);
		}

		limpiarTextfields();
		cargaDatosTabla();
	}

	private void actualizarRegistro(){
		try {
			String nom = txtNombre.getText().trim();
			if( nom.isEmpty() ){
				JOptionPane.showMessageDialog(this,"Por favor complete todos los campos.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			int id = Integer.parseInt(txtId.getText());
			this.controlador.actualizar(new Repartidor( id, nom, ""));
		} catch (SQLException e) {
			System.err.print(e);
			JOptionPane.showMessageDialog(this,"No se pudo actualizar el registro: "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}

		limpiarTextfields();
		cargaDatosTabla();
	}

	private void borrarRegistro(){
		try {
			if( txtId.getText().isEmpty() ){
				//JOptionPane.showMessageDialog(this,"Debe seleccionar un registro para eliminar.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			int id = Integer.parseInt(txtId.getText());
			this.controlador.borrar(id);
		} catch (SQLException e) {
			System.err.print(e);
			JOptionPane.showMessageDialog(this,"No se pudo borrar el registro: "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}

		limpiarTextfields();
		cargaDatosTabla();
	}



}
