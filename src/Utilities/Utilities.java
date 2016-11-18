package Utilities;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * This class contains some useful methods
 */
public abstract class Utilities{
    
    /**
     * This method parses a String to a Date
     * @param fecha the date to conert
     * @return fecha parsed to Date
     * @throws ParseException 
     */
    public static Date stringToDate(String fecha) throws ParseException{
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String strFecha = fecha;
        Date fechaDate = null;
        fechaDate = formato.parse(strFecha);
        return fechaDate;
    }
    
    /**
     * This method parses a Y/N form to true/false
     * @param b the String (must be Y/N) to parse 
     * @return the boolean result
     * @throws ParseException 
     */
    public static boolean YNToBoolean(String b) throws ParseException{
        if (b.equals("Y")) return true;
        else if (b.equals("N")) return false;
        else throw new ParseException("Error parsing bool", 0);
    }
    
    /**
     * This method parses a true/false form to Y/N
     * @param b the boolean
     * @return the string result
     */
    public static String BooleanToYN(Boolean b){
        if(b==true) return "Y";
        return "N";
    }
}
