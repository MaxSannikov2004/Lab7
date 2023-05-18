package commands.AllCommands;

import vehicleData.Vehicle;
import vehicleData.VehiclesCollection;
import commands.Command;
import commands.Invoker;
import exeptions.InvalidCommandException;

import java.util.Comparator;

/**
 * Method, that print any element with minimal value of capacity
 */
public class MinByCapacity implements Command {
    @Override
    public void execute(){
        try {
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            if (VehiclesCollection.getVehicle().isEmpty()) {
                System.out.println("Коллекция пуста");
            } else {
                try {
                    System.out.println(VehiclesCollection.getVehicle().stream().min(Comparator.comparingLong(Vehicle::getCapacity)).get());
                } catch (NumberFormatException numberFormatException) {
                    throw new InvalidCommandException();
                }
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }



    @Override
    public String description(){
        return "min_by_capacity: вывести любой объект из коллекции, значение поля capacity которого является минимальным";
    }
}
