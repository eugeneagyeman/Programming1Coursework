

/**
 * HouseBuilder is the builder class for house. The Appliances are set to their types of appliances and added to the house.
 */
public class HouseBuilder extends House {

    /**Configuration file reader*/
    private ConfigReader configFile;

    /**
     * Constructor to build the house based on the appliances given in the configuration file
     *
     * @param filename String of the file name
     * @throws Exception Exception thrown if the file does not exist or is unreadable
     */
    public HouseBuilder(String filename) throws Exception {
        configFile = new ConfigReader(filename);
        constructAppliances();
        System.out.println();
    }

    /**
     * constructAppliances method assigns the correct type of the appliance and their meter before adding it to the house
     *
     * @throws Exception Exception thrown if the file cannot find the correct type of appliances
     */
    private void constructAppliances() throws Exception {
        for (ApplianceBuilder x : configFile.getExtractedAppliances()) {
            if (x.getMeter().equalsIgnoreCase("electric")) {
                findCorrespondingElectricAppliance(x);
            } else if (x.getMeter().equalsIgnoreCase("water")) {
                findCorrespondingWaterAppliance(x);
            }
        }
    }

    /**
     * findCorrespondingElectricAppliance creates an electric appliance with their respective type and adds it to the house
     *
     * @param appliance Bare-bones appliance to be assigned to their type and added to the house
     * @throws Exception Exception thrown if the appliance cannot be added
     */
    private void findCorrespondingElectricAppliance(ApplianceBuilder appliance) throws Exception {
        try {
            switch (appliance.getSubclass()) {
                case "CyclicFixed":
                    this.addElectricAppliance(new CyclicFixed(appliance.getName(), appliance.getFixedUnits(), appliance.getCycleLength()));
                    break;

                case "CyclicVaries":
                    this.addElectricAppliance(new CyclicVaries(appliance.getName(), appliance.getMinUnits(), appliance.getMaxUnits(), appliance.getCycleLength()));
                    break;

                case "RandomFixed":
                    this.addElectricAppliance(new RandomFixed(appliance.getName(), appliance.getFixedUnits(), appliance.getProbability()));
                    break;

                case "RandomVaries":
                    this.addElectricAppliance(new RandomVaries(appliance.getName(), appliance.getMinUnits(), appliance.getMaxUnits(), appliance.getProbability()));
                    break;
            }
        } catch (Exception e) {
            throw new Exception("Appliance could not be added");
        }

    }

    /**
     * findCorrespondingElectricAppliance creates a water metered appliance with their respective type and adds it to the house
     *
     * @param appliance Bare-bones appliance to be assigned to their type and added to the house
     * @throws Exception Exception thrown if the appliance cannot be added
     */
    private void findCorrespondingWaterAppliance(ApplianceBuilder appliance) throws Exception {
        try {
            switch (appliance.getSubclass()) {
                case "CyclicFixed":
                    this.addWaterAppliance(new CyclicFixed(appliance.getName(), appliance.getFixedUnits(), appliance.getCycleLength()));
                    break;

                case "CyclicVaries":
                    this.addWaterAppliance(new CyclicVaries(appliance.getName(), appliance.getMinUnits(), appliance.getMaxUnits(), appliance.getCycleLength()));
                    break;

                case "RandomFixed":
                    this.addWaterAppliance(new RandomFixed(appliance.getName(), appliance.getFixedUnits(), appliance.getProbability()));
                    break;

                case "RandomVaries":
                    this.addWaterAppliance(new RandomVaries(appliance.getName(), appliance.getMinUnits(), appliance.getMaxUnits(), appliance.getProbability()));
                    break;
            }
        } catch (Exception e) {
            throw new Exception("Appliance could not be added");
        }
    }

}
