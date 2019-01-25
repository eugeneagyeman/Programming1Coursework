import java.util.Random;

/**
 * RandomFixed is a type of an appliance. This appliance is "Switched on" randomly during the day and
 * uses a fixed amount of units each hour.
 */
public class RandomFixed extends ApplianceBuilder {

    private final double hourlyConsumption;
    private final int probability;

    /**
     * Constructor which initialises the properties which store its units and the probability it will be switched on
     */
    public RandomFixed(String name, double hourlyConsumption, int probability) {
        super(name);
        this.hourlyConsumption = hourlyConsumption;
        this.probability = probability;

    }

    /**
     * Overridden timePasses method that calculates how many units the appliance consumes if turned on.
     */
    public void timePasses() {
        Random rand = new Random();
        boolean chanceTime = rand.nextInt(probability) == 0;

        if (chanceTime) {
            tellMeterToConsumeUnits(this.hourlyConsumption);
        }
    }
}
