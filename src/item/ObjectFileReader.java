
package item;
import java.util.*;
import java.io.*;
import java.text.*;


/**
 * This class has the methods needed to read and load a file of itms into the app
 */
public abstract class ObjectFileReader {

    
    /**
     * Static method to read a file
     * @param fichero the name, with the path of the file to read.
     * @return list of items read
     * @throws FileNotFoundException if the file is not found
     * @throws IOException failure in I/O
     */
    public static ArrayList<Item> readItems (String fichero) throws FileNotFoundException, IOException {
                  ArrayList<Item> items=new ArrayList<>();
                  String cadena;
                  FileReader f = new FileReader(fichero);
                  BufferedReader b = new BufferedReader(f);
                  for(@SuppressWarnings("unused")
				int i=0;(cadena = b.readLine())!=null;i++) {/*read line by line*/
                        String[] fields =cadena.split(";");/*split the line in fields*/
                        try{
                            addItem(items,fields);
                        }
                        catch(Exception e){
                            /*ignore line*/
                        }
                  }
                  b.close();
                  return items;
    }


    /**
     * Static method to add the item to the list
     * @param items the list of items where we are adding
     * @param fields the fields to consider
     * @throws Exception if an error ocurred
     * @return list of items tirh a new item added
     */

    private static void addItem(ArrayList<Item> items, String[] fields) throws ParseException{

        Item item;
        String type = fields[0];
            if(type.compareTo("S")==0){/*Small Item*/
                item = new SmallItem(fields);        	
            }
            else if(type.compareTo("B")==0){/*Bulky object*/
                item = new BigItem(fields);
            }
            else{
                item = new WorkOfArt(fields);
            }

            items.add(item);
    }    
    
    /**
     * Saves a list of items in a file
     * @param file the file where I save
     * @param items the items to save
     * @throws IOException 
     */
     public static void saveItems(String file, ArrayList<Item> items) throws IOException {   
           FileOutputStream stream = new FileOutputStream(file);
           PrintWriter salida = new PrintWriter(stream);
           for(Item it:items) salida.print(it.printItem());   
           salida.flush();
           stream.close();  
    }
}