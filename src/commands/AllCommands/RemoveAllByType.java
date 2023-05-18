package commands.AllCommands;

import vehicleData.Vehicle;
import vehicleData.VehicleType;
import vehicleData.VehiclesCollection;
import commands.Command;
import commands.Invoker;
import exeptions.InvalidCommandException;

import java.util.List;

/**
 * Method, that delete oll elements with specified type
 */
public class RemoveAllByType implements Command {
    @Override
    public void execute() {
        try{
            if(Invoker.getSplit().length != 2){
                throw new InvalidCommandException();
            }
            if (VehiclesCollection.getVehicle().isEmpty()) {
                System.out.println("Коллекция пуста, нечего удалять");
            } else {
                try {
                    List<Vehicle> list = VehiclesCollection.getVehicle().stream().filter(vehicle -> vehicle.getType().equals(VehicleType.valueOf(Invoker.getSplit()[1].trim()))).toList();
                    list.forEach(vehicle -> VehiclesCollection.getVehicle().remove(vehicle));
                    System.out.println("Техника такого типа удалена из коллекции");
                } catch (IllegalArgumentException illegalArgumentException) {
                    System.out.println("Такого типа не существует");
                }
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }

    @Override
    public String description() {
        return "remove_all_by_type type : удалить из коллекции все элементы, значение поля type которого эквивалентно заданному";
    }
}
