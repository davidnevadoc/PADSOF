package report;

import java.util.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Sales Report
 * 
 * @author David Nevado Catalan
 * @author Jorge Arellano Subias
 *
 */

public class Report implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2323754994176113986L;
	/**
	 * List of the sales
	 */
	protected ArrayList<Record> records;

	
	/**
	 * Report constructor
	 */
	public Report(){
		this.records = new ArrayList<Record>();
	}
	/**
	 * Adds a new sale to the report
	 * @param newRecord Sale added to the record
	 */
	public void addRecord(Record newRecord){
		
		this.records.add(newRecord);
	}
	

	
	/**
	 * Prints a report of all the sales and auctions  made 
	 * in the antiquary ordered by date
	 * @return String with the sales report
	 */
	public String printReport(){
		String report = "Report:\n";
		for ( Record record : this.records){
			report += record.toString();
			report += "\n";
		}
		
		return report;
	}
	/**
	 * Prints a report of all the sales made between
	 * the dates given.
	 * @param from Beginning date
	 * @param to End date
	 * @return String with the sales report
	 */
	public String printReport(LocalDate from, LocalDate to){
		String report = "Report:\n";
		for ( Record record : this.records){
			if(record.getDate().isAfter(from) && record.getDate().isBefore(to)){
				report += record.toString();
			}
			report += "\n";
		}
		
		
		return report;
	}
	/**
	 * Records getter
	 * @return An array of all the records
	 */
	public ArrayList<Record> getRecords(){
		return this.records;
	}
	/**
	 * Compares two reports
	 * @param record the record we want to compare
	 * @return true if both reports are equal, false otherwise
	 */
	@Override public boolean equals(Object report){
		Report report2 = (Report )report;
		return (this.records.equals(report2.getRecords()));
	}
}
