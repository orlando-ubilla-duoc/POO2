package com.duoc.bibliotecapp.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.duoc.bibliotecapp.controller.ControladorCategoria;
import com.duoc.bibliotecapp.controller.ControladorEstudiante;
import com.duoc.bibliotecapp.controller.ControladorLibro;
import com.duoc.bibliotecapp.controller.ControladorPrestamo;
import com.duoc.bibliotecapp.dao.CategoriaDAO;
import com.duoc.bibliotecapp.dao.EstudianteDAO;
import com.duoc.bibliotecapp.dao.LibroDAO;
import com.duoc.bibliotecapp.dao.PrestamoDAO;
import com.duoc.bibliotecapp.dao.impl.CategoriaDAOImpl;
import com.duoc.bibliotecapp.dao.impl.EstudianteDAOImpl;
import com.duoc.bibliotecapp.dao.impl.LibroDAOImpl;
import com.duoc.bibliotecapp.dao.impl.PrestamoDAOImpl;
import com.duoc.bibliotecapp.model.Rol;
import com.duoc.bibliotecapp.model.Usuario;

public class VentanaPrincipal {

	JFrame ventana;
	private Usuario usuario;

	public VentanaPrincipal(Usuario usuarioActual){
		this.usuario = usuarioActual;
        this.ventana = new JFrame("Bibliotecas");
		this.ventana.setSize(800,480);
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // cerrar app
		this.ventana.setLocationRelativeTo(null); // centrar
		this.ventana.setLayout(new GridLayout( 1, 2, 0, 0));
		ConfigurarComponentes("");
		ventana.setVisible(true);
    }

    private void ConfigurarComponentes(String tituloPadre)
	{

		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new GridLayout( 5, 1, 10, 10));
		panelMenu.setBorder(BorderFactory.createEmptyBorder(15, 50, 50, 50));

		JLabel lblDescripcion = new JLabel("Bienvenido al sistema de gestion.");
		lblDescripcion.setFont(new Font("Arial", Font.BOLD, 16));

		// crear botones para el menu
		JButton boton1 = new JButton("Gestion de Libros");
		boton1.setFont(new Font("Arial", Font.PLAIN, 20));
		JButton boton2 = new JButton("Gestion de Estudiantes");
		boton2.setFont(new Font("Arial", Font.PLAIN, 20));
		JButton boton3 = new JButton("Prestamos y Devoluciones");
		boton3.setFont(new Font("Arial", Font.PLAIN, 20));
		JButton boton4 = new JButton("Reportes");
		boton4.setFont(new Font("Arial", Font.PLAIN, 20));

		boton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				// Oculta ventana principal
				ventana.setVisible(false);
				// Gestion de Libros
				LibroDAO libroDAO         = new LibroDAOImpl();
				CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
				ControladorLibro controladorLibro         = new  ControladorLibro(libroDAO);
				ControladorCategoria controladorCategoria = new ControladorCategoria(categoriaDAO);
				GestionLibros viewLibros = new GestionLibros(ventana, controladorLibro, controladorCategoria);
			}
		});

		boton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				// Oculta ventana principal
				ventana.setVisible(false);
				// Gestion de Estudiantes
				EstudianteDAO estudianteDAO = new EstudianteDAOImpl();
				ControladorEstudiante controladorEstudiante = new ControladorEstudiante(estudianteDAO);
				GestionEstudiantes viewEstudiantes = new GestionEstudiantes(ventana, controladorEstudiante);
			}
		});

		boton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				// Oculta ventana principal
				ventana.setVisible(false);
				// Prestamos y Devoluciones
				PrestamoDAO prestamoDAO     = new PrestamoDAOImpl();
				EstudianteDAO estudianteDAO = new EstudianteDAOImpl();
				LibroDAO libroDAO           = new LibroDAOImpl();
				ControladorPrestamo controladorPrestamo     = new ControladorPrestamo(prestamoDAO);
				ControladorEstudiante controladorEstudiante = new ControladorEstudiante(estudianteDAO);
				ControladorLibro controladorLibro           = new ControladorLibro(libroDAO);
				RegistroPrestamosDevoluciones viewPrestamos = new RegistroPrestamosDevoluciones( ventana, controladorPrestamo, controladorEstudiante,	controladorLibro);
			}
		});

		boton4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				// Oculta ventana principal
				ventana.setVisible(false);
				PrestamoDAO prestamoDAO     = new PrestamoDAOImpl();
				EstudianteDAO estudianteDAO = new EstudianteDAOImpl();
				ControladorPrestamo controladorPrestamo     = new ControladorPrestamo(prestamoDAO);
				ControladorEstudiante controladorEstudiante = new ControladorEstudiante(estudianteDAO);
				VistaReportes viewReportes = new VistaReportes( ventana, controladorPrestamo, controladorEstudiante);
			}
		});

		// Agrega elementos al menu
		panelMenu.add(lblDescripcion);
		if( usuario.getRol().equals( Rol.BIBLIOTECARIO)){
			panelMenu.add(boton1);
			panelMenu.add(boton2);
		}
		panelMenu.add(boton3);
		panelMenu.add(boton4);

		ventana.add(panelMenu);
	}

}
