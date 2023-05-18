package vehicleData;

public enum VehicleType {
    PLANE("PLANE","1",1),
    HELICOPTER("HELICOPTER","2",10),
    SUBMARINE("SUBMARINE","3",100);
    /**
     * Field for vehicle type name choosing
     */
    private final String name;
    /**
     * Field for vehicle type number choosing
     */
    private final String number;
    /**
     * Field for sort by type (for method  Count_grater_than_type)
     */
    private final int power;
    VehicleType(String name, String number, int power) {
        this.name = name;
        this.number = number;
        this.power = power;
    }
    public static VehicleType getType(String string) {
        for (VehicleType vehicleType : VehicleType.values()) {
            if (vehicleType.number.equals(string)|vehicleType.name.equals(string)) return vehicleType;
        }
        return null;
    }
    public static int getPower(VehicleType vehicleType) {
        return vehicleType.power;
    }
}
