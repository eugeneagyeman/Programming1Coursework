

/**
 * The class 'BatteryMeter' is a variant of a 'Meter'. It has the ability to store a utility as it is connected to a
 * 'Battery'. If the utility consumed by the house over a time increment is negative the excess units gets stored in the
 * battery.
 */
public class BatteryMeter extends Meter {

    /**Instance of the battery*/
    private final Battery houseBattery;


    /**
     * Constructor which initialises the battery and the capacity.
     */
    public BatteryMeter(String utilityName, double unitCost, double batteryCapacity) {
        super(utilityName, unitCost);
        houseBattery = new Battery(0, 0);
        houseBattery.batteryCapacity = batteryCapacity;
        costToReturn = 0;
    }


    /**
     * Overridden report method to report the amount of utility drawn from the battery and the battery level.
     */
    @Override
    public double report() {
        if (meterReading < 0) {
            houseBattery.chargeBattery(-meterReading);
            setMeterReading(0);
        } else {
            if (houseBattery.getBatteryCharge() > 0) {
                meterReading -= houseBattery.dischargeBattery(meterReading);
            }
        }
        System.out.println();
        System.out.println("--Report--");
        System.out.println("Meter Reading For House After Hour: " + this.getMeterReading());
        System.out.println("Battery Level After Hour: " + houseBattery.getBatteryCharge());
        System.out.println("Charge Drawn: " + houseBattery.getChargeDrawn());
        System.out.println("Total Cost Over Hour: " + this.getHourlyCost());
        System.out.println();

        this.costToReturn = this.getHourlyCost();
        setMeterReading(0);
        return costToReturn;
    }


}
