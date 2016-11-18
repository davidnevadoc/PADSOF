package graphics;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

import Utilities.Configuration;
import antiquary.Antiquary;
import exceptions.NotFoundException;
import graphics.controllers.EmployeesController;

public class ShitTest {

	public static void main(String[] args) {
		

		Antiquary antiquary = null;
		Configuration.loadMacros();
		try {
			antiquary = Antiquary.loadAntiquary();
		} catch (IOException | NotFoundException e1) {
			System.out.println("Load error");
			System.exit(1);
		}
		
		JFrame window = new JFrame();
		new EmployeesTab(window, new EmployeesController(antiquary));
		window.setVisible(true);
		window.setMinimumSize(new Dimension(1100,700));

		window.setSize(1100, 800);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
