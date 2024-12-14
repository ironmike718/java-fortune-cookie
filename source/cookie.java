/*	Author: Michael Melendez
	Date: 12.03.10
	Descrip: Fortune Cookie Application
*/


import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;
import java.util.*;


public class cookie implements ActionListener {
	
	URL cookiePic;
	URL gongSound;
	URL crunchSound;
	JLabel label1;
	JPanel panel1;
	JPanel panel2;
	JButton button1;
	JButton button2;
	JFrame f;
	ImageIcon icon;
	String aboutMsg;
	
	public cookie () throws Exception {
	
		// construct gui components
		
		panel1 = new JPanel();
		panel1.setBackground(Color.RED.darker());
		
		panel2 = new JPanel();
		panel2.setBackground(Color.RED.darker());

		button1 = new JButton("Eat Me");
		button1.setPreferredSize(new Dimension(80, 30));      
		button1.setFocusPainted(false);
		
		button2 = new JButton("About");
		button2.setPreferredSize(new Dimension(80, 30));
		button2.setFocusPainted(false);
		
		cookiePic = getClass().getClassLoader().getResource("media/cookie.jpg");
		icon = new ImageIcon(cookiePic);
		label1 = new JLabel(icon);

		f = new JFrame();
		f.setLayout(new BorderLayout(0, 0));
		
		aboutMsg = "\nAuthor:  Michael Melendez\nNortheastern University\nITC 2305 Advanced Java Fall 2010\n\n";
		
		// add gui components to the frame and show window
		
		panel1.add(label1);

		panel2.add(button1); 
		panel2.add(button2); 

		f.add(panel1, BorderLayout.NORTH);
		f.add(panel2, BorderLayout.SOUTH);
		
		button1.addActionListener(this);
		button2.addActionListener(this);

		f.getContentPane();
		f.pack();
		f.setLocationRelativeTo(null); 
		f.setTitle("Fortune Cookie");
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		f.setVisible(true);
		
		// play gong sound
		
		gongSound = getClass().getClassLoader().getResource("media/gong.wav");
		AudioInputStream sound = AudioSystem.getAudioInputStream(gongSound);
		Clip clip = AudioSystem.getClip();
		clip.open(sound);
		clip.start();
	
	} // end cookie constructor
	
	public cookie (int i) throws Exception {
	
		// play crunch sound
		
		crunchSound = getClass().getClassLoader().getResource("media/crunch.wav");
		AudioInputStream sound = AudioSystem.getAudioInputStream(crunchSound);
		Clip clip = AudioSystem.getClip();
		clip.open(sound);
		clip.start();
	
	}
	
	public void actionPerformed(ActionEvent e) {
      
		Toolkit.getDefaultToolkit().beep();
		
		if ( e.getSource() == button1 ) {
		
			goToWork();
		
		}
		
		if ( e.getSource() == button2 ) {
		
			JOptionPane.showMessageDialog(null, aboutMsg,"About Fortune Cookie", JOptionPane.PLAIN_MESSAGE);
		
		}
		 
	} // end actionPerformed
	
	public void goToWork() {
	
		try {
	
		// play crunch sound
		
		new cookie(1);
		
		// create fortune object and get the fortune
		
		fortuneCookie yummy = new fortuneCookie();
		
		int myFortuneIndex = yummy.getMyFortuneIndex();
		String myFortuneInfo = yummy.getMyFortune();
		String myFortuneNums = yummy.getMyNumbers();
		
		String myFortuneMsg = "Your Fortune Is:\n\n" + myFortuneInfo + "\n\nYour Lucky Numbers are:\n\n" + myFortuneNums;
		
		JOptionPane.showMessageDialog(null, myFortuneMsg,"Fortune #" + myFortuneIndex, JOptionPane.PLAIN_MESSAGE);
		
		} catch  ( Exception e ) {
			
			System.exit(1);
		
		}
	
	}// end goGetFortune function
	
	public static void main(String args[]) throws Exception {
		
		new cookie();
		

    }// end main function
	
} // end class cookie
