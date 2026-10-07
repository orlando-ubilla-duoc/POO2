package com.duoc.speedfastapp.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.duoc.speedfastapp.controller.ControladorPedido;
import com.duoc.speedfastapp.model.Pedido;
import com.duoc.speedfastapp.model.PedidoComida;
import com.duoc.speedfastapp.model.PedidoEncomienda;
import com.duoc.speedfastapp.model.PedidoExpress;
import com.duoc.speedfastapp.model.TipoPedido;

public class VentanaRegistroPedido extends JFrame {

	private JTextField txtDireccion;
	private JComboBox<TipoPedido> cboTipo;
	private JButton btnGuardar;
	private JTable tablaRegistros;
	private DefaultTableModel modeloTabla;
	private ControladorPedido controlador;

	public VentanaRegistroPedido(String titulo, JFrame ventanaPadre, ControladorPedido ctr)
	{
		super(titulo);
		this.controlador = ctr;
		setSize(400,320);
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
				/*if( getVentanaPadre()!=null ){
					getVentanaPadre().setVisible(true); // vuelve a mostrar ventana padre
				}*/
			}

			@Override
			public void windowClosed(WindowEvent e){
				ventanaPadre.setVisible(true);
				/*if( getVentanaPadre()!=null ){
					getVentanaPadre().setVisible(true); // vuelve a mostrar ventana padre
				}*/
			}
		});

		setVisible(true);

	}

	private void ConfigurarComponentes(){
		// Panel para campos de texto
		JPanel panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridLayout( 5, 2, 10, 10));
		panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		panelFormulario.add(new JLabel("Agregar nuevo Pedido"));
		panelFormulario.add(new JLabel(""));

		panelFormulario.add(new JLabel("Dirección:"));
		txtDireccion = new JTextField();
		panelFormulario.add(txtDireccion);

		panelFormulario.add(new JLabel("Tipo de pedido:"));
		cboTipo = new JComboBox<>(TipoPedido.values());
		panelFormulario.add(cboTipo);

		panelFormulario.add(new JLabel());
		btnGuardar = new JButton("Guardar");
		panelFormulario.add(btnGuardar);

		panelFormulario.add(new JLabel());

		add(panelFormulario, BorderLayout.CENTER);
		btnGuardar.addActionListener(e -> guardarPedido());

		JPanel panel2 = new JPanel();
		add(panel2, BorderLayout.CENTER);

	}

	private void guardarPedido()
	{
		String direccion = txtDireccion.getText().trim();
		String tipo = cboTipo.getSelectedItem().toString();

		if (direccion.isEmpty()){
			JOptionPane.showMessageDialog(
				this,
				"Por favor complete todos los campos.",
				"Error",
				JOptionPane.ERROR_MESSAGE
			);
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
		} catch (Exception e) {
			System.err.print(e);
		}

	}
	
}
