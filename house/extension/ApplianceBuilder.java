/** ApplianceBuilder is a class which extends the abstract appliance class. This is a template for an appliance read from the config file. */
public class ApplianceBuilder extends Appliance {


    /** Name of an appliance */
    private String name;

    /**String containing the meter that should be applied to the appliance*/
    private String meter;

    /**Constructor initialises the properties of an appliance stored as a string at first before being built in the housebuilder class
     * @see HouseBuilder
     *
     * @param name Name of the appliance (e.g: Cooker)
     * @param subclass Type of appliance (e.g: RandomVaries)
     * @param meter Meter which records its consumption and production (e.g: Electric)
     * @param minUnits Minimum amount of units an appliance uses if applicable (Varies)
     * @param maxUnits Maximum amount of units an appliance uses if applicable (Varies)
     * @param fixedUnits Fixed amount of units an appliance uses if applicable
     * @param probability Probability the appliance will run if a Random type of appliance
     * @param cycleLength The amount of time an appliance will run for if a Cyclic type of appliance
     */
    public ApplianceBuilder(String name, String subclass, String meter, double minUnits, double maxUnits, double fixedUnits, int probability, int cycleLength) {
        super();
        this.name = name;
        this.subclass = subclass;
        this.meter = meter;
        this.minUnits = minUnits;
        this.maxUnits = maxUnits;
        this.fixedUnits = fixedUnits;
        this.probability = probability;
        this.cycleLength = cycleLength;
    }

    /**Constructor for subclasses*/
    protected ApplianceBuilder(String name) {
        super(name);
    }

    /* Getter Methods */

    public String getName() {
        return name;
    }

    public String getSubclass() {
        return subclass;
    }

    public String getMeter() {
        return meter;
    }

    public double getMinUnits() {
        return minUnits;
    }

    public double getMaxUnits() {
        return maxUnits;
    }

    public double getFixedUnits() {
        return fixedUnits;
    }

    public int getProbability() {
        return probability;
    }

    public int getCycleLength() {
        return cycleLength;
    }


    @Override
    public void timePasses() {

    }
}
