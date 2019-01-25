/**
 * <h1>Smart Home Simulator</h1>
 * This program sets up a simulation of the energy consumption- and energy generation- of a smart house.
 * In the house are a number of appliances which consume/generate electricity or consume water when they operate,
 * meters that record the amount of water and electricity consumed/generated and a battery which stores excess electricity
 * if the house happens to generate more than it needs.
 *
 * @author Eugene Agyeman
 * @version 1.0
 * @since 03-12-2018
 *
 * The abstract class 'Appliance' defines the basic properties of an appliance, name and the associated meter and the
 * different methods all its subclasses will use.
 */
public abstract class Appliance {

    /** Meter instance that an appliance will use to record its consumption and production*/
    private Meter meter;

    /** Name of an appliance*/
    private String name;

    /** Type of appliance (e.g: RandomVaries)*/
    protected String subclass;

    /** Minimum amount of units an appliance uses if applicable (Varies) */
    protected double minUnits;

    /**Maximum amount of units an appliance uses if applicable (Varies)*/
    protected double maxUnits;

    /**Fixed amount of units an appliance uses if applicable*/
    protected double fixedUnits;

    /**Probability the appliance will run if a Random type of appliance*/
    protected int probability;

    /**The amount of time an appliance will run for if a Cyclic type of appliance*/
    protected int cycleLength;


    /** Constructor initialises appliances with a name
     *
     * @param name Name of appliance
     */
    Appliance(String name) {
        this.name = name;
    }

    /** Default Constructor */
    public Appliance() {

    }


    /** 'timePasses' is an abstract method which simulates one hour passing by in the simulation.
     *  The appliance subclasses have their own implementations which represents how they use utilities.
     */
    public abstract void timePasses();

    /** 'tellMeterToConsumeUnits()' is a method to update the meter associated with an appliance with the number of units
     * it has to consume / generate.
     * @param numOfUnits number of units the appliance has consumed or generated.
     */
    protected void tellMeterToConsumeUnits(double numOfUnits) {
        meter.consumeUnits(numOfUnits);
    }

    /** 'meterToSet' attaches a meter to an appliance to record its usage
     * @param meterToSet Meter to attach to appliance (water or electric)
     * */
    public void setMeter(Meter meterToSet) {
        this.meter = meterToSet;
    }


}
