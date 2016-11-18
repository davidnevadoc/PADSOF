/**
* @author Jorge Arellano &lt;jorge.arellano@estudiante.uam.es&gt;
* @author David Nevado &lt;david.nevadoc@estudiante.uam.es&gt;
*
* This class implements the employee account 
*/
package user;

public class Employee extends User{

    /**
     * Constructor
     * @param name 
     * @param password
     */
	public Employee(String name, String password) {
		super(name, password);
	}
	
    /**
     * Overrides equals: two employees are equal if their user names are equal
     * @param o employee I'm comparing to
     * @return true if they are equal, false if not
     */
	@Override public boolean equals(Object o){ 
		if (o==this) return true;
		if (!(o instanceof Employee)) return false;
		Employee obj = (Employee)o;		
		return (obj.name).equals(this.name);
	}
	
	/**
	 * To manage hash when seeing if two employees are equal
	 * @return the hash code of employee
     */
	@Override public int hashCode() {
		return this.name.hashCode();
	}
	
}
