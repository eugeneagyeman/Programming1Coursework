/**
 * The 'Meter' class represents an object that manages the consumption and production of a particular utility.
 */
public class Meter {

    //Declaring instance variables
    private final String utilityName;    //String that describes type of utility (e.g. "electric")
    protected double costToReturn;
    double meterReading;    //Represents balance of units that have been used since last meter reading
    private double unitCost;       //Cost of one unit of this type of utility

    //ELECTRICITY = £0.013 per unit
    //WATER = £0.002 per unit

    /**
     * Constructor which initialises the properties which stores the type of utility along with its unit cost and meter reading
     *
     * @param utilityName A String representing the name of the utility (e.g: Electricity)
     * @param unitCost    Cost per unit stored as a double
     */
    public Meter(String utilityName, double unitCost) {
        this.utilityName = utilityName;
        this.unitCost = unitCost;
        this.meterReading = 0;
    }

    /**
     * Overloaded constructor which allows the client to input their own initial meter reading
     *
     * @param utilityName  A String for the name of utility (e.g: Electricity)
     * @param unitCost     Cost per unit stored as a double
     * @param meterReading Initial meter reading stored as a double
     */
    public Meter(String utilityName, double unitCost, double meterReading) {
        /*if (utilityName.contains("Electric")) {
            this.utilityName = "Electricity";
            this.unitCost = unitCost;
            this.meterReading = meterReading;
        } else {
            this.utilityName = "Water";
            this.unitCost = unitCost;
            this.meterReading = meterReading;
        }*/
        this.utilityName = utilityName;
        this.unitCost = unitCost;
        this.meterReading = meterReading;

    }

    /**
     * @param utilityName A String representing the name of the utility
     * @deprecated Constructor for debugging purposes
     */
    public Meter(String utilityName) {
        this.utilityName = utilityName;
    }

    protected String getUtilityName() {
        return utilityName;
    }

    public double getUnitCost() {
        return unitCost;
    }

    double getMeterReading() {
        return meterReading;
    }

    void setMeterReading(double meterReading) {
        this.meterReading = meterReading;
    }

    /**
     * Method to update meterReading with number of units used since last reading
     */
    public void consumeUnits(double numOfUnits) {
        numOfUnits = Math.round(numOfUnits);
        this.meterReading += numOfUnits;
    }

    /**
     * Method to return the cost of an hourly reading
     */
    double getHourlyCost() {

        double cost = this.meterReading * unitCost;
        cost = roundCost(cost);
        return cost;
    }


    private double roundCost(double costToRound) {
        costToRound = Math.round(costToRound * 100.0) / 100.0;

        if (costToRound < 0) {
            costToRound = 0;
        }
        return costToRound;
    }

    /**
     * Method to report the amount of units the utility has used and the corresponding cost
     */
    public double report() {

        double hourlyCost = this.getHourlyCost();

        System.out.println(this.getUtilityName() + " units used");

        System.out.println("Meter Reading: " + this.getMeterReading());
        System.out.println("Hourly Cost: " + "\u00a3" + this.getHourlyCost());

        this.setMeterReading(0);
        return hourlyCost;

    }
}
