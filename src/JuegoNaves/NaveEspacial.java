package JuegoNaves;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class NaveEspacial extends javax.swing.JFrame {    

	// Constructor
	public NaveEspacial() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(new java.awt.Color(255, 255, 255));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 400, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 300, Short.MAX_VALUE));
		pack();
	}

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch (Exception ex) {
			java.util.logging.Logger.getLogger(NaveEspacial.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}       
		NaveEspacial f = new NaveEspacial();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Actividad 6 - Naves Espaciales");
		f.setContentPane(new PanelNave());
		f.setSize(500, 600);
		f.setVisible(true);
	}
}