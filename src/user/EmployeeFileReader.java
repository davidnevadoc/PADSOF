
package user;
import java.util.*;
import java.io.*;
import java.text.*;
import exceptions.*;


/**
 * This class has the methods needed to read and load a file of employees into the app
 * @author David Nevado Catalan
 * @author Jorge Arellano Subias
 */
public abstract class EmployeeFileReader {

    
    /**
     * Static method to read a file of users, cosidering the first a manager
     * @param fichero the name, with the path of the file to read.
     * @return list of items read
     * @throws FileNotFoundException if the file is not found
     * @throws IOException failure in I/O
     * @throws NotFoundException if the manager is not found
     */
    public static ArrayList<User> readEmployees (String fichero) throws FileNotFoundException, IOException, NotFoundException, IndexOutOfBoundsException {
                  ArrayList<User> users=new ArrayList<>();
                  String cadena;
                  FileReader f = new FileReader(fichero);
                  BufferedReader b = new BufferedReader(f);
                  if((cadena = b.readLine())==null) throw new NotFoundException("Manager not found in the file");
                  String[] fields =cadena.split(";");/*split the line in fields*/
                  Manager manager = new Manager(fields[0],fields[1]);
                  users.add(manager);
                  for(int i=0;(cadena = b.readLine())!=null;i++) {/*read line by line*/
                        fields =cadena.split(";");/*split the line in fields*/
                        try{
                            addUser(users,fields);
                        }
                        catch(Exception e){
                            /*ignore line*/
                        }
                  }
                  b.close();
                  return users;
    }
    
        /**
     * Static method to read a file of users without considering the first a manager
     * @param fichero the name, with the path of the file to read.
     * @return list of items read
     * @throws FileNotFoundException if the file is not found
     * @throws IOException failure in I/O
     * @throws NotFoundException if the manager is not found
     */
    public static ArrayList<Employee> readEmployeesWithoutManager (String fichero) throws FileNotFoundException, IOException, NotFoundException, IndexOutOfBoundsException {
            ArrayList <User> users = EmployeeFileReader.readEmployees(fichero); 
            User user=users.remove(0); /*I know this one is manager*/
            Employee emp=new Employee(user.getName(),user.getPassword());
            users.add(0, emp);
            return (ArrayList<Employee>)(ArrayList<?>)users;
    }


    /**
     * Static method to add the item to the list
     * @param items the list of items where we are adding
     * @param fields the fields to consider
     * @throws Exception if an error ocurred
     * @return list of items tirh a new item added
     */

    private static void addUser(ArrayList<User> users, String[] fields) throws ParseException{

        User user;
        user=new Employee(fields[0],fields[1]);

        if(!users.contains(user)) users.add(user); /*If repeated, the user is not added*/
    }    
    
    /**
     * Saves a list of users in a file
     * @param file the file to save
     * @param users the list of users
     * @throws IOException 
     */
     public static void saveEmployees(String file, ArrayList<User> users) throws IOException {   
           FileOutputStream stream = new FileOutputStream(file);
           PrintWriter salida = new PrintWriter(stream);
           for(User it:users) salida.print(it.printUser());   
           salida.flush();
           stream.close();  
    }
}