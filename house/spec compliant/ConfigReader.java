

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ConfigReader is a class used for the parsing of the configuration file. The configuration file will list a set of appliances
 * which are in a house
 */
public class ConfigReader {

    /**Array of the barebone appliances*/
    private final ArrayList<ApplianceBuilder> extractedAppliances = new ArrayList<>();

    /**File reader*/
    private BufferedReader reader;

    /**ArrayList of the strings in the configFile*/
    private ArrayList<String> applianceRead = new ArrayList<>();

    /** Name of an appliance*/
    private String name;

    /** Type of appliance (e.g: RandomVaries)*/
    private String subclass;

    /** Meter instance that an appliance will use to record its consumption and production*/
    private String meter;

    /** Minimum amount of units an appliance uses if applicable (Varies) */
    private double minUnits;

    /**Maximum amount of units an appliance uses if applicable (Varies)*/
    private double maxUnits;

    /**Fixed amount of units an appliance uses if applicable*/
    private double fixedUnits;

    /**Probability the appliance will run if a Random type of appliance*/
    private int probability;

    /**The amount of time an appliance will run for if a Cyclic type of appliance*/
    private int cycleLength;

    /**
     * Constructor to initialise the reader of the given file name and parse the file creating new default appliances with no subtype
     *
     * @param filename String of the file name
     */
    public ConfigReader(String filename) throws IOException, AssertionError {
        try {
            reader = new BufferedReader(new FileReader(filename));
            parse();
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        } finally {
            System.out.println("File read successfully.");
            if (reader == null) throw new AssertionError();
            reader.close();
        }
    }

    /**
     * 'getLines' is a method used to read the file one line at a time, extract the string and store that in an array
     *
     * @return returns the arrayList of the strings in the file to be parsed
     * @throws IOException throws IO Exception if it cannot read the file
     */
    private ArrayList<String> getLines() throws IOException {
        String line;

        while ((line = reader.readLine()) != null) {
            String[] splitLine = line.split("\\n");
            applianceRead.add(splitLine[0]);
        }
        return applianceRead;
    }

    /**
     * 'parse()' is the method to parse the file. This reads each string stored in an array and builds an appliance from it.
     * This is then stored in a temporary array in which the HouseBuilder class will construct the different types of appliances.
     *
     * @see HouseBuilder
     */
    private void parse() {

        try {
            applianceRead = this.getLines();
            for (String line : applianceRead) {

                if (line.equals("")) {
                    extractedAppliances.add(new ApplianceBuilder(name, subclass, meter, minUnits, maxUnits, fixedUnits, probability, cycleLength));

                } else {
                    extractApplianceDetails(line);
                    //System.out.println(line);
                }

            }
        } catch (IOException e) {
            System.out.println("Failed to extract appliances");
        } finally {
            System.out.println("\nAppliances Extracted:\n");

            for (ApplianceBuilder x : extractedAppliances) {
                System.out.println(x.getName());
            }
        }
    }


    /**
     * 'extractApplianceDetails' is a method to set the properties of an appliance in the config file.
     *
     * @param line String with the property of an appliance along with its variable (e.g: Name: Fridge)
     */
    private void extractApplianceDetails(String line) {
        String[] splitLine;
        String variable;
        try {
            splitLine = line.split(": ");
            variable = splitLine[1];

            switch (splitLine[0]) {
                case "name":
                    setName(variable);
                    break;

                case "subclass":
                    setSubclass(variable);
                    break;

                case "meter":
                    setMeter(variable);
                    break;

                case "Min units consumed":
                    setMinUnits(variable);
                    break;

                case "Max units consumed":
                    setMaxUnits(variable);
                    break;

                case "Fixed units consumed":
                    setFixedUnits(variable);
                    break;

                case "Probability switched on":
                    setProbability(variable);
                    break;

                case "Cycle length":
                    setCycleLength(variable);
                    break;

                default:
                    System.out.println("Couldn't parse line");
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            variable = "";
        }
    }

    /**
     * Returns the array of appliances extracted from the file before correctly assigning their type
     *
     * @return returns appliances extracted from the file.
     * @see HouseBuilder
     */
    public ArrayList<ApplianceBuilder> getExtractedAppliances() {
        return extractedAppliances;
    }


    /* Setter Methods */

    private void setName(String line) {
        this.name = line;
    }

    private void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    private void setMeter(String meter) {
        this.meter = meter;
    }

    private void setMinUnits(String minUnits) {

        this.minUnits = Double.parseDouble(minUnits);
    }

    private void setMaxUnits(String maxUnits) {

        this.maxUnits = Double.parseDouble(maxUnits);
    }

    private void setFixedUnits(String fixedUnits) {

        this.fixedUnits = Double.parseDouble(fixedUnits);
    }

    private void setProbability(String probability) {
        String[] splitProbability = probability.split(" in ");

        this.probability = Integer.parseInt(splitProbability[1]);
    }

    private void setCycleLength(String cycleLength) {
        String[] splitCycleLength = cycleLength.split("/");
        this.cycleLength = Integer.parseInt(splitCycleLength[0]);
    }

}
