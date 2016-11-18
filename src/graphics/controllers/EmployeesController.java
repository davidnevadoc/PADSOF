package graphics.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import antiquary.Antiquary;
import user.Employee;
/**
 * GUI controller for the employees tab
 * @author David Nevado Catalan
 * @author Jorge Arellano Subias
 */
public class EmployeesController implements ActionListener{
	/**
	 * Antiquary controlled by the GUI
	 */
	Antiquary antiquary;
	/**
	 * EmployeeController constructor
	 * @param antiquary Antiquary to be controlled
	 * by the GUI
	 */
	public EmployeesController(Antiquary antiquary){
		this.antiquary=antiquary;
	}
	/**
	 * Gets the list of employees of the antiquary
	 * @return Employees of the antiquary as an ArrayList
	 */
	public ArrayList<Employee> getEmployeeList(){
		return this.antiquary.getEmployees();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showConfirmDialog(null, "Delete user ?","Delete confirmation" ,JOptionPane.YES_NO_OPTION);
		
	}
}
