package com.duoc.speedfastapp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.BoxLayout;
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

import com.duoc.speedfastapp.controller.ControladorPedido;
import com.duoc.speedfastapp.model.Pedido;
import com.duoc.speedfastapp.model.PedidoComida;
import com.duoc.speedfastapp.model.PedidoEncomienda;
import com.duoc.speedfastapp.model.PedidoExpress;
import com.duoc.speedfastapp.model.TipoPedido;

public class VentanaRegistroPedido extends JFrame {

	private JTextField txtId;
	private JTextField txtDireccion;
	private JComboBox<TipoPedido> cboTipo;
	private JTable tablaRegistros;
	private DefaultTableModel modeloTabla;
	private ControladorPedido controlador;

	public VentanaRegistroPedido(String titulo, JFrame ventanaPadre, ControladorPedido ctr)
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
		panelFormulario.setLayout(new GridLayout( 4, 4, 10, 10));
		//panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelFormulario.setBorder(new TitledBorder("Registro"));

		// row 1
		txtId = new JTextField();
		txtId.setEditable(false);
		panelFormulario.add(new JLabel("Id pedido:"));
		panelFormulario.add(txtId);
		panelFormulario.add(new JLabel(""));
		panelFormulario.add(new JLabel(""));

		// row 2
		txtDireccion = new JTextField();
		panelFormulario.add(new JLabel("Dirección:"));
		panelFormulario.add(txtDireccion);
		panelFormulario.add(new JLabel(""));
		panelFormulario.add(new JLabel(""));

		// row 3
		cboTipo = new JComboBox<>(TipoPedido.values());
		panelFormulario.add(new JLabel("Tipo de pedido:"));
		panelFormulario.add(cboTipo);
		panelFormulario.add(new JLabel(""));
		panelFormulario.add(new JLabel(""));

		// row 4
		JButton btnGuardar = new JButton("Guardar");
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
		panelGrid.setLayout(new BorderLayout());
		String[] columnas = {
			"ID",
			"Direccion",
			"Tipo pedido"
		};
		modeloTabla = new DefaultTableModel(columnas, 0);
		tablaRegistros = new JTable(modeloTabla);
		tablaRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaRegistros.getSelectionModel().addListSelectionListener(e -> tablaClickEvt(e));
		JScrollPane scrollPanel = new JScrollPane(tablaRegistros);
		scrollPanel.setBackground(Color.WHITE);
		panelGrid.add(scrollPanel, BorderLayout.CENTER);
		add(panelGrid, BorderLayout.CENTER);

		cargaDatosTabla();
		txtDireccion.requestFocus();
	}

	/**
	 * Para limpiar campos cada vez que se completa una accion
	 */
	private void limpiarTextfields(){
		txtId.setText("");
		txtDireccion.setText("");
		cboTipo.setSelectedIndex(0);
		txtDireccion.requestFocus();
	}

	/**
	 * Obtiene informacion desde base de datos y llena grilla de datos.
	 */
	private void cargaDatosTabla()
	{
		try {
			modeloTabla.setRowCount(0);
			for(Pedido p:this.controlador.listarPedidos()){
				modeloTabla.addRow(new Object[]{
					p.getNroPedido(), p.getDireccionEntrega(),p.getTipo()
					//pedido.getEstado()
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
			txtDireccion.setText(modeloTabla.getValueAt(fila,1).toString());
			if( modeloTabla.getValueAt(fila,2).toString().equals( TipoPedido.COMIDA.name() ) ){
				cboTipo.setSelectedItem(TipoPedido.COMIDA);
			}
			if( modeloTabla.getValueAt(fila,2).toString().equals(TipoPedido.ENCOMIENDA.name()) ){
				cboTipo.setSelectedItem(TipoPedido.ENCOMIENDA);
			}
			if( modeloTabla.getValueAt(fila,2).toString().equals(TipoPedido.EXPRESS.name()) ){
				cboTipo.setSelectedItem(TipoPedido.EXPRESS);
			}
		}
	}

	private void guardarRegistro()
	{
		String direccion = txtDireccion.getText().trim();
		String tipo = cboTipo.getSelectedItem().toString();

		if (direccion.isEmpty()){
			JOptionPane.showMessageDialog(this,"Por favor complete todos los campos.","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}

		Pedido pedido;

		switch (tipo) {
			case "Comida":
				pedido = new PedidoComida( 0, direccion, TipoPedido.COMIDA.name(), "PENDIENTE");
				break;
			case "Encomienda":
				pedido = new PedidoEncomienda(0, direccion, TipoPedido.ENCOMIENDA.name(), "PENDIENTE");
				break;
			case "Express":
				pedido = new PedidoExpress(0, direccion, TipoPedido.EXPRESS.name(), "PENDIENTE");
				break;
			default:
				throw new IllegalArgumentException("Tipo de pedido no válido");
		}

		try {
			controlador.guardar(pedido);
		} catch (SQLException e) {
			System.err.print(e);
		}

		limpiarTextfields();
		cargaDatosTabla();
	}

	private void actualizarRegistro(){
		try {
			String direccion = txtDireccion.getText().trim();
			if( direccion.isEmpty() ){
				JOptionPane.showMessageDialog(this,"Por favor complete todos los campos.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			int id = Integer.parseInt(txtId.getText());
			String tipo = cboTipo.getSelectedItem().toString();
			Pedido pedido;
			switch (tipo) {
				case "Comida":
					pedido = new PedidoComida( id, direccion, TipoPedido.COMIDA.name(), "PENDIENTE");
					break;
				case "Encomienda":
					pedido = new PedidoEncomienda( id, direccion, TipoPedido.ENCOMIENDA.name(), "PENDIENTE");
					break;
				case "Express":
					pedido = new PedidoExpress( id, direccion, TipoPedido.EXPRESS.name(), "PENDIENTE");
					break;
				default:
					throw new IllegalArgumentException("Tipo de pedido no válido");
			}
			this.controlador.actualizar(pedido);
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
