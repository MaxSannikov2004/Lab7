package vehicleData;

import commands.Invoker;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VehiclesCollection {
    /**
     * Static collection that all commands in the program work with
     */
    private static final Stack<Vehicle> vehicles = new Stack<>();
    /**
     * The field containing the collection initialization date
     */
    private static final Date dateOfInitialization = new Date();
    public static Stack<Vehicle> getVehicle() {
        return vehicles;
    }
    /**
     * Method that print information about the collection
     */
    public static void getInfo() {
        System.out.println("Тип коллекции: " + vehicles.getClass().getTypeName().split(".util.")[1] + "\n" +
                "Дата инициализации: " + dateOfInitialization + "\n" +
                "Количество элементов: " + vehicles.size() + "\n");
    }
    /**
     * method for adding vehicle from a file to a collection
     * @return возвращает boolean в зависимости от того, существует файл или нет
     * */
    public static boolean putVehicleFromFile() {
        try {
            Scanner scanner = new Scanner(new File(Invoker.getFile()));
            String name; //vehicle[0]
            double x; //vehicle[1]
            long y; //vehicle[2]
            Double enginePower; //vehicle[3]
            long capacity; //vehicle[4]
            long fuelConsumption; //vehicle[5]
            VehicleType type; //vehicle[6]
            while (scanner.hasNext()) {
                try {
                    String[] vehicle = scanner.nextLine().split(", ");
                    if (vehicle.length == 7) {
                        try {
                            if (!vehicle[0].trim().isEmpty()) {
                                name = vehicle[0];
                            } else {
                                throw new InputMismatchException();
                            }
                            x = Double.parseDouble(vehicle[1]);
                            y = Long.parseLong(vehicle[2]);
                            enginePower = Double.parseDouble(vehicle[3]);
                            capacity = Long.parseLong(vehicle[4]);
                            fuelConsumption = Long.parseLong(vehicle[5]);
                                if (vehicle[6].equals("PLANE") || vehicle[6].equals("HELICOPTER") || vehicle[6].equals("SUBMARINE"))
                                    type = VehicleType.valueOf(vehicle[6]);
                                else {
                                throw new InputMismatchException();
                            }
                            vehicles.add(new Vehicle(name, new Coordinates(x, y), enginePower, capacity, fuelConsumption, type));
                        } catch (Exception ignored) {}
                    }
                } catch (Exception ignored) {}
            }
            scanner.close();
            System.out.println("Из файла добавлено объектов в коллекцию: " + vehicles.size());
            return true;
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Файл не найден");
            return false;
        }
    }
}
