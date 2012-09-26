package com.artivisi.project;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class BelajarInternalFrame {
	public static void main(String[] xx) throws Exception {
		// membuat object fr yang bertipe JFrame
		JFrame fr = new JFrame();
		
		// Judul frame
		fr.setTitle("Belajar Internal Frame");
		
		// ukurannya
		fr.setSize(800,600);
		fr.setLocationRelativeTo(null);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane jdp = new JDesktopPane();
		
		JInternalFrame ifr = new JInternalFrame();
		ifr.setTitle("Ini internal frame");
		ifr.setSize(200,300);
		ifr.setClosable(true);
		ifr.setResizable(true);
		ifr.setIconifiable(true);
		ifr.setMaximizable(true);
		jdp.add(ifr);
		ifr.setVisible(true);
		
		fr.setContentPane(jdp);
		fr.setVisible(true);
		
	}
}