
package Utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

/**
 * This class loads the macros of the app
 * @author David Nevado Catalan
 * @author Jorge Arellano Subias
 */
public abstract class Configuration {
    /**
     * maximum lenght before applying extra delivery cost (cm)
     */
    private static  double maxlength =-1;
    /**
     * delivery cost if maxlenght reached
     */
    private static  double priceIfLong =-1;
    /**
     * maximum weight before applying extra delivery cost (kg)
     */
    private static  double maxweight  =-1;
    /**
     * base price if maxweight reached
     */
    private static  double basePriceIfWeight =-1;
    /**
     * Increment added to the basePrice for every incrementWeight kilos
     */
    private static  double incrementPrice =-1;
    /**
     * Every incrementWeight passing the maxweight we must pay incrementPrice more
     */
    private static  double incrementWeight=-1;
    /**
     * The base price of the deliveing
     */
    private static double basePrice=-1 ;
    /**
     * Delivery discount for VipContract
     */
    private static double deliveryDiscount = 0;
    /**
     * Number of starting free auctions in a standard contract
     */
    private static int freeAuctions = 5;
    /**
     * Minimum amount spent necessary for a standard
     *  contract to get discounts
     */
	private static double minAmount =200;
    /**
     * The file where I read/save users
     */
    private static String usersFile=null;
    /**
     * The file where I read/save items
     */
    private static String itemsFile=null;
    /**
     * File where the auctions will be stored and loaded
     */
    private static String auctionsFile="auctions.ser";
    /**
     * File where the customers will be stored and loaded
     */
    private static String customersFile="customers.ser";
    /**
     * File where the report will be stored and loaded
     */
    private static String reportFile="report.ser";
    /**
     * Where I load the macros from
     */
    protected static String fileMacros="macros.txt";
    /**
     * Marks if the macros have already been loaded
     */
    private static boolean loaded = false;
    /**
     * Loads the macros from fileMacros
     */
    
    @SuppressWarnings("unused")
	public static void loadMacros(){
        String cadena;
        try{
            FileReader f = new FileReader(fileMacros);
            BufferedReader b = new BufferedReader(f);
            for(int i=0;(cadena = b.readLine())!=null;i++) {/*read line by line*/
                  String[] fields =cadena.split(";");/*split the line in fields*/
                  try{
                    if (fields[0].equals("maxlength"))
                        Configuration.maxlength=Double.parseDouble(fields[1]);
                    else if (fields[0].equals("priceIfLong"))
                        Configuration.priceIfLong=Double.parseDouble(fields[1]);
                    else if (fields[0].equals("maxweight"))
                        Configuration.maxweight=Double.parseDouble(fields[1]);
                    else if (fields[0].equals("basePriceIfWeight"))
                        Configuration.basePriceIfWeight=Double.parseDouble(fields[1]);
                    else if (fields[0].equals("incrementPrice"))
                        Configuration.incrementPrice=Double.parseDouble(fields[1]);
                    else if (fields[0].equals("incrementWeight"))
                        Configuration.incrementWeight=Double.parseDouble(fields[1]);
                    else if (fields[0].equals("basePrice"))
                        Configuration.basePrice=Double.parseDouble(fields[1]);
                    else if (fields[0].equals("deliveryDiscount"))
                    	Configuration.deliveryDiscount= Double.parseDouble(fields[1]);
                    else if (fields[0].equals("minAmount"))
                    	Configuration.minAmount= Double.parseDouble(fields[1]);
                    else if (fields[0].equals("freeAuctions"))
                    	Configuration.freeAuctions= Integer.parseInt(fields[1]);
                    else if (fields[0].equals("usersFile"))
                        Configuration.usersFile=fields[1];
                    else if (fields[0].equals("itemsFile"))
                        Configuration.itemsFile=fields[1];
                    
                  }
                  catch(Exception e){
                      /*Ignore line*/
                  }
                }/*for loop*/
                    b.close();
            }/*try*/
            catch(IOException e){
                    /*Ignore all*/
            }
            finally{
                loaded=true;
            }
    }

    public static double getMaxlength() {
        if(!loaded) loadMacros(); 
        if(maxlength<0) maxlength=200;
        return maxlength;
    }

    public static double getPriceIfLong(){
        if(!loaded) loadMacros(); 
        if(priceIfLong<0) priceIfLong=50;
        return priceIfLong;
    }

    public static double getMaxweight(){
        if(!loaded) loadMacros();
        if(maxweight<0) maxweight = 15;
        return maxweight;
    }

    public static double getBasePriceIfWeight(){
        if(!loaded) loadMacros();
        if(basePriceIfWeight<0) basePriceIfWeight=50;
        return basePriceIfWeight;
    }

    public static double getIncrementPrice(){
        if(!loaded) loadMacros();
        if(incrementPrice<0) incrementPrice=2;
        return incrementPrice;
    }

    public static double getIncrementWeight(){
        if(!loaded) loadMacros();
        if(incrementWeight<0)incrementWeight=5;
        return incrementWeight;
    }

    public static double getBasePrice(){
        if(!loaded) loadMacros();
        if(basePrice<0)basePrice=0;
        return basePrice;
    }
    public static double getMinAmount(){
    	if(!loaded) loadMacros();
    	if(minAmount<0) minAmount =0;
    	return minAmount;
    }
    public static int getFreeAuctions(){
    	if(!loaded) loadMacros();
    	if(freeAuctions <0) freeAuctions =0;
    	return freeAuctions;
    }
    public static String getUsersFile(){
        if(!loaded) loadMacros();
        if(usersFile==null)usersFile="users.txt";
        return usersFile;
    }

    public static String getItemsFile(){
        if(!loaded) loadMacros();
        if(itemsFile==null)itemsFile="items.txt";        
        return itemsFile;
    }
    public static double getDeliveryDiscount(){
    	return Configuration.deliveryDiscount;
    }
    public static String getAuctionsFile(){
    	return Configuration.auctionsFile;
    }
    public static String getCustomersFile(){
    	return Configuration.customersFile;
    }
    public static String getReportFile(){
    	return Configuration.reportFile;
    }
    
    
    
    
    
    
    public static void setMaxlength(double maxlength) {
        Configuration.maxlength = maxlength;
    }

    public static void setPriceIfLong(double priceIfLong) {
        Configuration.priceIfLong = priceIfLong;
    }

    public static void setMaxweight(double maxweight) {
        Configuration.maxweight = maxweight;
    }

    public static void setBasePriceIfWeight(double basePriceIfWeight) {
        Configuration.basePriceIfWeight = basePriceIfWeight;
    }

    public static void setIncrementPrice(double incrementPrice) {
        Configuration.incrementPrice = incrementPrice;
    }

    public static void setIncrementWeight(double incrementWeight) {
        Configuration.incrementWeight = incrementWeight;
    }

    public static void setBasePrice(double basePrice) {
        Configuration.basePrice = basePrice;
    }
    public static void setMinAmount(double minAmount) {
        Configuration.minAmount = minAmount;
    }
    public static void setFreeAuctions(int freeAuctions) {
        Configuration.freeAuctions = freeAuctions;
    }

    public static void setUsersFile(String usersFile) {
        Configuration.usersFile = usersFile;
    }

    public static void setItemsFile(String itemsFile) {
        Configuration.itemsFile = itemsFile;
    }
    public static void setDeliveryDiscount(double deliveryDiscount){
    	if(deliveryDiscount>=0 && deliveryDiscount <=1){
    		Configuration.deliveryDiscount=deliveryDiscount;
    	}
    }
    
    
}
