package mainPackage;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

	public static void main(String[] args) {
//		Color primaryColor = new Color(255, 250, 250);
//		Color secondaryColor = new Color(210, 105, 30);
//		Color backgroundColor = Color.cyan;
////		Color backgroundColor = new Color(25, 0, 51);
//		
//		
//
//		
//		        try {
//					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (InstantiationException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (UnsupportedLookAndFeelException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		    
//		    UIManager.put("base", primaryColor);
//			UIManager.put("nimbusBlueGrey", backgroundColor);
//			UIManager.put("control", backgroundColor);
//			
//			UIManager.put("Label.foreground", secondaryColor);
//			UIManager.put("Button.foreground", secondaryColor);
//	        UIManager.put("Label.foreground", secondaryColor);
//	        UIManager.put("RadioButton.foreground", secondaryColor);
		
		
		GraphicFrame frame = new GraphicFrame();
		frame.setVisible(true);
	}

}
