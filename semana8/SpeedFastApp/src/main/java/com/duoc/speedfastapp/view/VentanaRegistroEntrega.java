package com.duoc.speedfastapp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import com.duoc.speedfastapp.controller.ControladorEntrega;
import com.duoc.speedfastapp.controller.ControladorPedido;
import com.duoc.speedfastapp.controller.ControladorRepartidor;
import com.duoc.speedfastapp.model.Entrega;
import com.duoc.speedfastapp.model.Pedido;
import com.duoc.speedfastapp.model.Repartidor;

public class VentanaRegistroEntrega extends JFrame {

	private JTextField txtId;
	private JComboBox<Pedido> cboPedido;
	private JComboBox<Repartidor> cboRepartidor;
	private JSpinner spnFecha;
	private JSpinner spnHora;
	private JTable tablaRegistros;
	private DefaultTableModel modeloTabla;
	private ControladorEntrega controladorEntrega;
	private ControladorPedido controladorPedido;
	private ControladorRepartidor controladorRepartidor;
	private List<Entrega> listaEntregas;

	public VentanaRegistroEntrega(String titulo, JFrame ventanaPadre, ControladorEntrega ctr1, ControladorPedido ctr2, ControladorRepartidor ctr3)
	{
		super(titulo);
		this.listaEntregas = new ArrayList<>();
		this.controladorEntrega = ctr1;
		this.controladorPedido = ctr2;
		this.controladorRepartidor = ctr3;
		setSize(800,600);
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
		spnFecha.requestFocus();
	}

	private void ConfigurarComponentes(){
		// Panel para campos de texto
		JPanel panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridLayout( 5, 4, 10, 10));
		//panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelFormulario.setBorder(new TitledBorder("Registro"));

		// row 1
		txtId = new JTextField();
		txtId.setEditable(false);
		panelFormulario.add(new JLabel("Id Entrega:"));
		panelFormulario.add(txtId);
		panelFormulario.add(new JLabel(""));
		panelFormulario.add(new JLabel(""));

		// row 2
		cboPedido = new JComboBox<>();
		try {
			for(Pedido p:controladorPedido.listarPedidos()){
				cboPedido.addItem(p);
			}
		} catch (SQLException e) {
			System.err.print(e);
			JOptionPane.showMessageDialog(this,"No se pudo cargar el listado de Pedidos: "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		panelFormulario.add(new JLabel("Pedido:"));
		panelFormulario.add(cboPedido);
		panelFormulario.add(new JLabel(""));
		panelFormulario.add(new JLabel(""));

		// row 3
		cboRepartidor = new JComboBox<>();
		try {
			for(Repartidor r:controladorRepartidor.listarTodos()){
				cboRepartidor.addItem(r);
			}
		} catch (SQLException e) {
			System.err.print(e);
			JOptionPane.showMessageDialog(this,"No se pudo cargar el listado de Repartidores: "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		panelFormulario.add(new JLabel("Repartidor:"));
		panelFormulario.add(cboRepartidor);
		panelFormulario.add(new JLabel(""));
		panelFormulario.add(new JLabel(""));

		// row 4
		SpinnerDateModel model = new SpinnerDateModel();
		spnFecha = new JSpinner(model);
		JSpinner.DateEditor editorFecha = new JSpinner.DateEditor(spnFecha, "dd/MM/yyyy");
		spnFecha.setEditor(editorFecha);

		SpinnerDateModel model2 = new SpinnerDateModel();
		spnHora = new JSpinner(model2);
		JSpinner.DateEditor editorHora = new JSpinner.DateEditor(spnHora, "HH:mm");
		spnHora.setEditor(editorHora);

		panelFormulario.add(new JLabel("Fecha:"));
		panelFormulario.add(spnFecha);
		panelFormulario.add(new JLabel("Hora:"));
		panelFormulario.add(spnHora);

		// row 5
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
			"Pedido",
			"Repartidor",
			"Fecha",
			"Hora"
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
	}

	/**
	 * Para limpiar campos cada vez que se completa una accion
	 */
	private void limpiarTextfields(){
		txtId.setText("");
		spnFecha.setValue(new Date());
		spnHora.setValue(new Date());
		cboPedido.setSelectedIndex(0);
		cboRepartidor.setSelectedIndex(0);
		spnFecha.requestFocus();
	}

	/**
	 * Obtiene informacion desde base de datos y llena grilla de datos.
	 */
	private void cargaDatosTabla()
	{
		this.listaEntregas = new ArrayList<>();
		try {
			this.listaEntregas = this.controladorEntrega.listarEntregas();
			modeloTabla.setRowCount(0);
			for(Entrega e:this.listaEntregas){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String fechaTexto = sdf.format(e.getFecha());
				modeloTabla.addRow(new Object[]{
					e.getId(),
					e.getIdPedido()+" - "+e.getDirPedido(),
					e.getIdRepartidor()+" - "+e.getNomRepartidor(),
					fechaTexto,
					e.getHora()
				});
			}
		} catch (SQLException e) {
			System.err.print(e);
			JOptionPane.showMessageDialog(this,"No se pudo cargar la grilla de datos: "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	private void tablaClickEvt(ListSelectionEvent lst)
	{
		if( !lst.getValueIsAdjusting() && tablaRegistros.getSelectedRow() >= 0 ){
			int fila = tablaRegistros.getSelectedRow();
			int entregaId = Integer.parseInt(modeloTabla.getValueAt(fila,0).toString().trim());
			int pedidoId = 0;
			int repartidorId = 0;

			txtId.setText(modeloTabla.getValueAt(fila,0).toString());

			for(Entrega e:this.listaEntregas){
				if(e.getId()==entregaId){
					pedidoId = e.getIdPedido();
					repartidorId = e.getIdRepartidor();
				}
			}

			for( int i = 0; i < cboPedido.getItemCount(); i++ ){
				Pedido p = cboPedido.getItemAt(i);
				if( p.getNroPedido() == pedidoId ){
					cboPedido.setSelectedIndex(i);
					break;
				}
			}

			for( int i = 0; i < cboRepartidor.getItemCount(); i++ ){
				Repartidor p = cboRepartidor.getItemAt(i);
				if( p.getId() == repartidorId ){
					cboRepartidor.setSelectedIndex(i);
					break;
				}
			}

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
			Date fecha = new Date();
			Date hora = new Date();
			try {
				fecha = sdf1.parse(modeloTabla.getValueAt(fila,3).toString());
				hora  = sdf2.parse(modeloTabla.getValueAt(fila,4).toString());
			} catch (Exception e) {
				System.err.print(e);
			}

			spnFecha.setValue(fecha);
			spnHora.setValue(hora);
		}
	}


	private void guardarRegistro()
	{
		Pedido pedidoObj = (Pedido) cboPedido.getSelectedItem();
		Repartidor repartidorObj = (Repartidor) cboRepartidor.getSelectedItem();

		// Formatos de fecha y hora
		Date fecha = (Date) spnFecha.getValue();
		Date hora = (Date) spnHora.getValue();

		LocalTime horaLT = hora.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalTime()
        .withSecond(0)
        .withNano(0);

		try {
			Entrega nuevaEntrega = new Entrega(0, pedidoObj.getNroPedido(), repartidorObj.getId(), fecha, horaLT, pedidoObj.getDireccionEntrega(), repartidorObj.getNombre() );
			this.controladorEntrega.guardar(nuevaEntrega);
		} catch(SQLException e){
			System.err.print(e);
		}

		limpiarTextfields();
		cargaDatosTabla();
	}

	private void actualizarRegistro()
	{
		int id = Integer.parseInt(txtId.getText());
		Pedido pedidoObj = (Pedido) cboPedido.getSelectedItem();
		Repartidor repartidorObj = (Repartidor) cboRepartidor.getSelectedItem();

		// Formatos de fecha y hora
		Date fecha = (Date) spnFecha.getValue();
		Date hora = (Date) spnHora.getValue();

		LocalTime horaLT = hora.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalTime()
        .withSecond(0)
        .withNano(0);

		try {
			Entrega nuevaEntrega = new Entrega( id, pedidoObj.getNroPedido(), repartidorObj.getId(), fecha, horaLT, pedidoObj.getDireccionEntrega(), repartidorObj.getNombre() );
			this.controladorEntrega.actualizar(nuevaEntrega);
		} catch(SQLException e){
			System.err.print(e);
		}

		limpiarTextfields();
		cargaDatosTabla();
	}

	private void borrarRegistro()
	{
		try {
			if( txtId.getText().isEmpty() ){
				//JOptionPane.showMessageDialog(this,"Debe seleccionar un registro para eliminar.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			int id = Integer.parseInt(txtId.getText());
			this.controladorEntrega.borrar(id);
		} catch (SQLException e) {
			System.err.print(e);
			JOptionPane.showMessageDialog(this,"No se pudo borrar el registro: "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}

		limpiarTextfields();
		cargaDatosTabla();
	}

}
