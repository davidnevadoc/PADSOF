/**
* @author Jorge Arellano &lt;jorge.arellano@estudiante.uam.es&gt;
* @author David Nevado &lt;david.nevadoc@estudiante.uam.es&gt;
*
* This class implements a user abstractly
*/

package user;

public abstract class User {
	
    /**
    * The user name
    */
    protected String name;
    /**
    * The login password
    */
    protected String password;

    /**
     * Constructor
     * @param name name
     * @param password password
     */
    protected User(String name, String password) {
            super();
            this.name = name;
            this.password = password;
    }
    /**
     * Getter
     * @return the user name
     */
    public String getName() {
            return name;
    }
    /**
     * Setter
     * @param name new user name
     */
    public void setName(String name) {
            this.name = name;
    }
    /**
     * Getter
     * @return the login password
     */
    public String getPassword() {
            return password;
    }
    /**
     * Setter
     * @param password new login password
     */
    public void setPassword(String password) {
            this.password = password;
    }
        
    public String printUser(){
        return (name + ";" + password + "\n");
    }

}
