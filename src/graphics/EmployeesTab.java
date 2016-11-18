package graphics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import graphics.controllers.EmployeesController;
import user.Employee;


public class EmployeesTab extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3855989019102879059L;

	public EmployeesTab(JFrame window, EmployeesController controller){
		
		Container container = window.getContentPane();
		container.setLayout(new BorderLayout());
		
		setCentralPanel(container, controller);
		
		
		setUpperPanel(container,controller);
		
		
		setBottomPanel(container, controller);
		
		
		
	}
	

	private void setCentralPanel(Container container, EmployeesController controller){
		JPanel centralPanel = new JPanel();
		centralPanel.setLayout(new GridLayout(3, 1));
		/*Adding employees panels to the central panel*/
		for(Employee emp : controller.getEmployeeList()){
			addEmployee( centralPanel, emp,  controller);
			
		}
		container.add(centralPanel,BorderLayout.CENTER);
		
	}
	
	private void setUpperPanel(Container container, EmployeesController controller){
		/*Upper panel*/
		JPanel uPanel = new JPanel(new FlowLayout());
		uPanel.add(new JLabel("Username: Mr.Manager"));
		JButton cPass =  new JButton("Change Password");
		cPass.setPreferredSize(new Dimension(150,50));
		uPanel.add(cPass);
		container.add(uPanel,BorderLayout.PAGE_START);
	}
	
	private void setBottomPanel(Container container, EmployeesController controller){
		/*Botton panel*/
		JPanel bPanel = new JPanel(new FlowLayout());
		JButton addE = new JButton("Add Employee");
		addE.setPreferredSize(new Dimension(150,60));
		bPanel.add(addE);
		
		container.add(bPanel, BorderLayout.PAGE_END);
	}
	
	private void addEmployee(JPanel panel, Employee emp, EmployeesController controller){
		JPanel newEmployee = new JPanel();
		newEmployee.setLayout(new FlowLayout());
		/*Image*/
		JPanel picPanel = createImagePanel("EmployeePic1.jpeg",8,8);
		newEmployee.add(picPanel);
		
		JLabel uLabel = new JLabel("Username:" + emp.getName());
		uLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		newEmployee.add(uLabel);
		/*Change Password button*/
		JButton cPass =  new JButton("Change Password");
		cPass.setPreferredSize(new Dimension(150,50));
		cPass.addActionListener(controller);
		newEmployee.add(cPass);
		/*Delete button*/
		JButton delete = new JButton("Delete");
		delete.setPreferredSize(new Dimension(90, 50));
		delete.addActionListener(controller);
		newEmployee.add(delete);
		
		/*Add employee to the panel*/
		panel.add(newEmployee);
	}
	
	private JPanel createImagePanel(String imagePath, int  width, int length){
		JPanel picPanel = new JPanel(new BorderLayout());
		BufferedImage myPicture =null;
		try {
			myPicture = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picPanel.add(picLabel);
		picPanel.setSize(width,length);
		return picPanel;
	}
	
}

