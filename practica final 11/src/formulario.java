import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import conn.Conexion;


public class formulario extends JFrame {

	private JPanel contentPane;
	private JTextField t_nom;
	private JTextField t_cod;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formulario frame = new formulario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public formulario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(42, 47, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo");
		lblNewLabel_1.setBounds(42, 88, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Genero");
		lblNewLabel_2.setBounds(42, 135, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		t_nom = new JTextField();
		t_nom.setEnabled(false);
		t_nom.setBounds(98, 44, 142, 20);
		contentPane.add(t_nom);
		t_nom.setColumns(10);
		
		t_cod = new JTextField();
		t_cod.setEnabled(false);
		t_cod.setBounds(98, 85, 142, 20);
		contentPane.add(t_cod);
		t_cod.setColumns(10);
		
		JComboBox combo_gen = new JComboBox();
		combo_gen.setEnabled(false);
		combo_gen.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		combo_gen.setBounds(110, 132, 94, 20);
		contentPane.add(combo_gen);
		
		JLabel lblGuardar = new JLabel("");
		lblGuardar.setBounds(42, 177, 46, 14);
		contentPane.add(lblGuardar);
		
	
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion cn = new Conexion();
				
				String sql= "INSERT INTO participante(genero,nombre,codigo) VALUES(?,?,?)";;
				String nombre = t_nom.getText().toString();
				String genero = combo_gen.getSelectedItem().toString();
				String codigo = t_cod.getText().toString();
				try {
					
					
					
					
					Connection conn = cn.obteneConeccion();
					PreparedStatement st = conn.prepareStatement(sql);
					st.setString(1, genero);
					st.setString(2, nombre);
					st.setString(3, codigo);
					
					st.executeUpdate();
					JOptionPane.showMessageDialog(null, "Datos Guardados");
				}catch(SQLException el) {
					el.printStackTrace();
					
				}
			}
		});
		btnGuardar.setBounds(198, 218, 89, 23);
		contentPane.add(btnGuardar);
		
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t_nom.setEnabled(true);
				t_cod.setEnabled(true);
				combo_gen.setEnabled(true);
				btnNuevo.setEnabled(false);
			}
		});
		btnNuevo.setBounds(80, 218, 89, 23);
		contentPane.add(btnNuevo);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(323, 218, 89, 23);
		contentPane.add(btnSalir);
	}
}
