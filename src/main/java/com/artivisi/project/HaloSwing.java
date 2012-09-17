package com.artivisi.project;
/* Container : 
		untuk import Frame Pada Java : "import javax.swing.JFrame;"
		untuk import Label Pada Java : "import javax.swing.JLabel;"
*/
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class HaloSwing {
	public static void main(String[] xx){
		JFrame fr = new JFrame();  // untuk membuat frame
		fr.setTitle("Halo Swing"); // untuk memberi judul pada frame
		
		JLabel lbl = new JLabel("Halo Swing");
		fr.getContentPane().add(lbl);
		
		JOptionPane.showMessageDialog(null, "Halo Dialog");
		
		fr.setSize(200,200);
		fr.setVisible(true);
	}
}
