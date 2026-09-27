package com.duoc.speedfastapp.view;

import com.duoc.speedfastapp.controller.ControladorPedido;
import com.duoc.speedfastapp.model.Pedido;
import com.duoc.speedfastapp.model.PedidoComida;
import com.duoc.speedfastapp.model.PedidoEncomienda;
import com.duoc.speedfastapp.model.PedidoExpress;
import com.duoc.speedfastapp.model.TipoPedido;
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
import javax.swing.JTextField;

public class VentanaRegistroPedido extends JFrame {

	private JTextField txtId;
	private JTextField txtDireccion;
	private JComboBox<TipoPedido> cboTipo;
	private JButton btnGuardar;
	private ControladorPedido controlador;

	public VentanaRegistroPedido(String titulo, JFrame ventanaPadre, ControladorPedido c){
		super(titulo);
		this.controlador = c;
		setSize(800,512);
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
		panelFormulario.setLayout(new GridLayout( 4, 4, 10, 10));
		panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		panelFormulario.add(new JLabel("ID:"));
		txtId = new JTextField();
		panelFormulario.add(txtId);

		panelFormulario.add(new JLabel("Dirección:"));
		txtDireccion = new JTextField();
		panelFormulario.add(txtDireccion);

		panelFormulario.add(new JLabel("Tipo de pedido:"));
		cboTipo = new JComboBox<>(TipoPedido.values());
		panelFormulario.add(cboTipo);

		panelFormulario.add(new JLabel());
		btnGuardar = new JButton("Guardar");
		panelFormulario.add(btnGuardar);

		add(panelFormulario, BorderLayout.CENTER);
		btnGuardar.addActionListener(e -> guardarPedido());

		JPanel panel2 = new JPanel();
		add(panel2, BorderLayout.CENTER);

	}

	private void guardarPedido(){
		String id = txtId.getText().trim();
		String direccion = txtDireccion.getText().trim();
		String tipo = cboTipo.getSelectedItem().toString();

		if (id.isEmpty() || direccion.isEmpty()){
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
				pedido = new PedidoComida( Integer.parseInt(id), direccion, 0.0);
				break;
			case "Encomienda":
				pedido = new PedidoEncomienda(Integer.parseInt(id), direccion, 0);
				break;
			case "Express":
				pedido = new PedidoExpress(Integer.parseInt(id), direccion, 0);
				break;
			default:
				throw new IllegalArgumentException("Tipo de pedido no válido");
		}
		controlador.agregarPedido(pedido);
	}
	
}
