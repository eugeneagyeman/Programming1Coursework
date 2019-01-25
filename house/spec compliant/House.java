import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class represents a house in which the appliances will operate and have its usage recorded by the meters for
 * electricity and water.
 */
public class House {

    /**
     * Meter for water appliances
     */
    private final Meter waterMeter;

    /**
     * Meter for water appliances
     */
    private final Meter electricMeter;

    /**
     * Total cost reported by both meters
     */
    private double totalCost;

    /**
     * ArrayList of all appliances currently registered in the house
     */
    private ArrayList<Appliance> applianceArrayList;

    int dayCount = 0;
    int hourCount = 0;
    int simulationCount =  1;

    /**
     * Constructor creates and initialises two types of meters, one for electric and another for water.
     */
    public House() {
        this.applianceArrayList = new ArrayList<>();
        this.electricMeter = new BatteryMeter("Electricity", 0.013, 30);
        this.waterMeter = new Meter("Water", 0.002);
    }

    /**
     * Overloaded constructor with two parameters for two meters should the client want to use their own meters
     */
    public House(Meter electricMeter, Meter waterMeter) {
        this.electricMeter = electricMeter;
        this.waterMeter = waterMeter;
    }

    /**
     * Main method
     */
    public static void main(String[] args) throws Exception {


        House builtHouse = new HouseBuilder(args[0]);
        int MAXIMUM_TIME = Integer.parseInt(args[1]);
        boolean toRun = true;

        while(toRun) {
            System.out.println("How Long do you want to run the simulation?:");
            Scanner userInput = new Scanner(System.in);
            int hours = 0;
            try {
                hours = userInput.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("This is not an integer.");
            }
            if (hours < 168) {
                hours = 168;
                System.out.println("Simulation cannot run for less than seven days. Has defaulted to a 7 day simulation.");

            } else if (hours > MAXIMUM_TIME) {
                hours = MAXIMUM_TIME;
                System.out.println("You cannot run the simulation for more than the the maximum time inputted (1 month) ");

            }
            builtHouse.activate(hours);
            userInput.reset();


            System.out.println("Would you like to save the state of your simulation");

            try {
                String saveDecision = userInput.next();

                if(saveDecision.contains("y")) {
                    builtHouse.save();
                } else {
                    System.out.println("Simulation not saved");
                }
            } catch (Exception e) {
                throw new Exception ("Incorrect Input");
            }

            System.out.println("Okay would you like to continue?");

            try {
                String continuationDecision = userInput.next();
                if(continuationDecision.contains("n")) {
                    System.out.println("Okay goodBye!");
                    toRun = false;
                }
            } catch (Exception e) {
                throw new Exception("Incorrect Input");
            }

        }
    }

    /**
     * addElectricAppliance adds a specific appliance to the house and sets the meter for the appliance to the electric
     * meter
     *
     * @param electricAppliance Appliance to add to the house
     */
    protected void addElectricAppliance(Appliance electricAppliance) {
        electricAppliance.setMeter(electricMeter);
        this.applianceArrayList.add(electricAppliance);
    }

    /**
     * addWaterAppliance adds a specific appliance to the house and sets the meter for the appliance to the water
     * meter
     *
     * @param waterAppliance Appliance to add to the house
     */
    public void addWaterAppliance(Appliance waterAppliance) {
        waterAppliance.setMeter(waterMeter);
        this.applianceArrayList.add(waterAppliance);
    }

    /**
     * removeAppliance removes a specified appliance from the house
     *
     * @param applianceToDelete Appliance to remove from the house
     */
    public void removeAppliance(Appliance applianceToDelete) {
        this.applianceArrayList.remove(applianceToDelete);
    }

    /**
     * numAppliances returns the number of Appliances currently registered with the house
     */
    public void numAppliances() {
        System.out.println(this.applianceArrayList.size());
    }

    private double roundCost(double costToRound) {
        costToRound = Math.round(costToRound * 100.0) / 100.0;

        if (costToRound < 0) {
            costToRound = 0;
        }
        return costToRound;
    }

    private double getTotalCost() {
        return roundCost(totalCost);
    }


    /**
     * Overloaded method in which the client can specify how long the simulation should run for.
     *
     * @param numberOfHours Number of hours to be simulated
     * @throws Exception Exception thrown if the simulation is interrupted.
     */
    public void activate(int numberOfHours) {

        for (int currentHours = 0; currentHours < numberOfHours; currentHours++) {


            try {
                Thread.sleep(0);
                System.out.println();
                System.out.println("Day: " + dayCount);
                System.out.println("Hour: " + hourCount);

                this.totalCost += activate();    //Summation of hourly cost from both meters
                hourCount++;


            } catch (InterruptedException e) {
                System.out.println("Simulation interrupted");
            }

            if ((currentHours + 1) % 24 == 0) {
                dayCount++;
                hourCount = 1;
            }
        }
        System.out.println();
        System.out.println("Summary cost: " + "\u00a3" + this.getTotalCost());    //Unicode for British Pound
    }

    /**
     * The 'activate' method simulates one hour passing in the house by calling timePasses for each appliance currently
     * registered in the house. It then reports the usage from both meters along with the cost from both meters for that
     * hour.
     */
    private double activate() {
        try {
            for (Appliance anAppliance : this.applianceArrayList) {
                anAppliance.timePasses();
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("No meter has been set");
        }
        return electricMeter.report() + waterMeter.report();
    }
}
