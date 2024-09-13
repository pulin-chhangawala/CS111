import java.util.ArrayList;

/**
 * The StopAndFrisk class represents stop-and-frisk data, provided by
 * the New York Police Department (NYPD), that is used to compare
 * during when the policy was put in place and after the policy ended.
 * 
 * @author Tanvi Yamarthy
 * @author Vidushi Jindal
 */
public class StopAndFrisk {

    /*
     * The ArrayList keeps track of years that are loaded from CSV data file.
     * Each SFYear corresponds to 1 year of SFRecords. 
     * Each SFRecord corresponds to one stop and frisk occurrence.
     */ 
    private ArrayList<SFYear> database; 

    /*
     * Constructor creates and initializes the @database array
     * 
     * DO NOT update nor remove this constructor
     */
    public StopAndFrisk () {
        database = new ArrayList<>();
    }

    /*
     * Getter method for the database.
     * *** DO NOT REMOVE nor update this method ****
     */
    public ArrayList<SFYear> getDatabase() {
        return database;
    }

    /**
     * This method reads the records information from an input csv file and populates 
     * the database.
     * 
     * Each stop and frisk record is a line in the input csv file.
     * 
     * 1. Open file utilizing StdIn.setFile(csvFile)
     * 2. While the input still contains lines:
     *    - Read a record line (see assignment description on how to do this)
     *    - Create an object of type SFRecord containing the record information
     *    - If the record's year has already is present in the database:
     *        - Add the SFRecord to the year's records
     *    - If the record's year is not present in the database:
     *        - Create a new SFYear 
     *        - Add the SFRecord to the new SFYear
     *        - Add the new SFYear to the database ArrayList
     * 
     * @param csvFile
     */
    public void readFile ( String csvFile ) {

        // DO NOT remove these two lines
        StdIn.setFile(csvFile); // Opens the file
        StdIn.readLine();       // Reads and discards the header line

        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            String[] recEntries = line.split(",");
    
            int year = Integer.parseInt(recEntries[0]);
            String description = recEntries[2];
            String gender = recEntries[52];
            String race = recEntries[66];
            String location = recEntries[71];
            boolean arrested = recEntries[13].equals("Y");
            boolean frisked = recEntries[16].equals("Y");
    
            SFRecord record = new SFRecord(description, arrested, frisked, gender, race, location);
    
            boolean yearFound = false;
            for (SFYear sfYear : database) {
                if (sfYear.getcurrentYear() == year) {
                    sfYear.addRecord(record);
                    yearFound = true;
                    break;
                }
            }
    
            if (!yearFound) {
                SFYear newYear = new SFYear(year);
                newYear.addRecord(record);
                database.add(newYear);
            }
        }
    }

    /**
     * This method returns the stop and frisk records of a given year where 
     * the people that was stopped was of the specified race.
     * 
     * @param year we are only interested in the records of year.
     * @param race we are only interested in the records of stops of people of race. 
     * @return an ArrayList containing all stop and frisk records for people of the 
     * parameters race and year.
     */

    public ArrayList<SFRecord> populationStopped ( int year, String race ) {
        ArrayList<SFRecord> recordsOfSpecificRace = new ArrayList<>();
        for (SFYear sfYear : database) {
            if (sfYear.getcurrentYear() == year) {
                for (SFRecord record : sfYear.getRecordsForYear()) {
                    if (record.getRace().equalsIgnoreCase(race)) {
                        recordsOfSpecificRace.add(record);
                    }
                }
                break;
            }
        }
        return recordsOfSpecificRace;    }

    /**
     * This method computes the percentage of records where the person was frisked and the
     * percentage of records where the person was arrested.
     * 
     * @param year we are only interested in the records of year.
     * @return the percent of the population that were frisked and the percent that
     *         were arrested.
     */
    public double[] friskedVSArrested ( int year ) {
        
        int friskedCount = 0;
        int arrestedCount = 0;
        int totalRecords = 0;
    
        for (SFYear sfYear : database) {
            if (sfYear.getcurrentYear() == year) {
                ArrayList<SFRecord> recordsForYear = sfYear.getRecordsForYear();
                totalRecords = recordsForYear.size();
    
                for (SFRecord record : recordsForYear) {
                    if (record.getFrisked()) {
                        friskedCount++;
                    }
                    if (record.getArrested()) {
                        arrestedCount++;
                    }
                }
                break;
            }
        }
    
        double[] output = new double[2];
        if (totalRecords > 0) {
            output[0] = (double) friskedCount / totalRecords * 100;
            output[1] = (double) arrestedCount / totalRecords * 100;
        }
        return output;
    }

    /**
     * This method keeps track of the fraction of Black females, Black males,
     * White females and White males that were stopped for any reason.
     * Drawing out the exact table helps visualize the gender bias.
     * 
     * @param year we are only interested in the records of year.
     * @return a 2D array of percent of number of White and Black females
     *         versus the number of White and Black males.
     */
    public double[][] genderBias ( int year ) {
        int blackMaleCount = 0;
        int whiteMaleCount = 0;
        int blackFemaleCount = 0;
        int whiteFemaleCount = 0;
        int blackCount = 0;
        int whiteCount = 0;
    
        for (SFYear sfYear : database) {
            if (sfYear.getcurrentYear() == year) {
                for (SFRecord record : sfYear.getRecordsForYear()) {
                    String race = record.getRace();
                    String gender = record.getGender();
    
                    if (race.equals("B")) {
                        blackCount++;
                        if (gender.equals("M")) {
                            blackMaleCount++;
                        } else if (gender.equals("F")) {
                            blackFemaleCount++;
                        }
                    } else if (race.equals("W")) {
                        whiteCount++;
                        if (gender.equals("M")) {
                            whiteMaleCount++;
                        } else if (gender.equals("F")) {
                            whiteFemaleCount++;
                        }
                    }
                }
                break;
            }
        }
    
        double[][] percentages = new double[2][3];
        if (blackCount > 0) {
            percentages[0][0] = (double) blackFemaleCount / blackCount * 0.5 * 100;
            percentages[1][0] = (double) blackMaleCount / blackCount * 0.5 * 100;
        }
        if (whiteCount > 0) {
            percentages[0][1] = (double) whiteFemaleCount / whiteCount * 0.5 * 100;
            percentages[1][1] = (double) whiteMaleCount / whiteCount * 0.5 * 100;
        }
        percentages[0][2] = percentages[0][0] + percentages[0][1];
        percentages[1][2] = percentages[1][0] + percentages[1][1];
    
        return percentages;
    }

    /**
     * This method checks to see if there has been increase or decrease 
     * in a certain crime from year 1 to year 2.
     * 
     * Expect year1 to preceed year2 or be equal.
     * 
     * @param crimeDescription
     * @param year1 first year to compare.
     * @param year2 second year to compare.
     * @return 
     */

    public double crimeIncrease ( String crimeDescription, int year1, int year2 ) {
        
        if (year1 >= year2) {
            throw new IllegalArgumentException("year1 must be less than year2");
        }

        int countYear1 = 0;
        int countYear2 = 0;
        int totalRecordsYear1 = 0;
        int totalRecordsYear2 = 0;
        for (SFYear sfYear : database) {
            if (sfYear.getcurrentYear() == year1) {
                totalRecordsYear1 = sfYear.getRecordsForYear().size();
                for (SFRecord record : sfYear.getRecordsForYear()) {
                    if (record.getDescription().indexOf(crimeDescription) != -1)
                        {countYear1++;}
                }
            } else if (sfYear.getcurrentYear() == year2) {
                totalRecordsYear2 = sfYear.getRecordsForYear().size();
                for (SFRecord record : sfYear.getRecordsForYear()) {
                    if (record.getDescription().indexOf(crimeDescription) != -1) 
                    {countYear2++;}
                }
            }
        }
        double percentageYear1 = (double) countYear1 / totalRecordsYear1 * 100;
        double percentageYear2 = (double) countYear2 / totalRecordsYear2 * 100;
        return percentageYear2 - percentageYear1;    }

    /**
     * This method outputs the NYC borough where the most amount of stops 
     * occurred in a given year. This method will mainly analyze the five 
     * following boroughs in New York City: Brooklyn, Manhattan, Bronx, 
     * Queens, and Staten Island.
     * 
     * @param year we are only interested in the records of year.
     * @return the borough with the greatest number of stops
     */
    public String mostCommonBorough ( int year ) {
        int[] counts = new int[5];
        String[] boroughs = {"BROOKLYN", "MANHATTAN", "BRONX", "QUEENS", "STATEN ISLAND"};
    
        for (SFYear sfYear : database) {
            if (sfYear.getcurrentYear() == year) {
                for (SFRecord record : sfYear.getRecordsForYear()) {
                    String location = record.getLocation();
                    for (int i = 0; i < boroughs.length; i++) {
                        if (location.equalsIgnoreCase(boroughs[i])) {
                            counts[i]++;
                            break;
                        }
                    }
                }
                break;
            }
        }
        int maxIndex = 0;
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] > counts[maxIndex]) {
                maxIndex = i;
            }
        }
        return boroughs[maxIndex];    }

}