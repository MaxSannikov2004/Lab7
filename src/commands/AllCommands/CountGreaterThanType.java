package commands.AllCommands;

import vehicleData.VehicleType;
import vehicleData.VehiclesCollection;
import commands.Command;
import commands.Invoker;
import exeptions.InvalidCommandException;

/**
 * Method that count amount of elements, with higher value of type power
 */
public class CountGreaterThanType implements Command {
    @Override
    public void execute() {
        try {
            if(Invoker.getSplit().length != 2){
                throw new InvalidCommandException();
            }
            if (VehiclesCollection.getVehicle().isEmpty()) {
                System.out.println("Коллекция пуста");
            } else {
                try {
                    VehicleType vehicleType = VehicleType.valueOf(Invoker.getSplit()[1]);
                    System.out.println("Количество техники типа больше чем заданный: " + VehiclesCollection.getVehicle().stream().filter(vehicle -> VehicleType.getPower(vehicle.getType()) > VehicleType.getPower(vehicleType)).count());
                } catch (IllegalArgumentException illegalArgumentException) {
                    throw new InvalidCommandException();
                }
            }
        } catch (InvalidCommandException e) {System.out.println(e.getMessage());}
    }

    @Override
    public String description() {
        return "count_greater_than_type type : вывести количество элементов, значение поля type которых больше заданного";
    }
}
