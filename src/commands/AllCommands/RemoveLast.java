package commands.AllCommands;

import vehicleData.VehiclesCollection;
import commands.Command;
import commands.Invoker;
import exeptions.InvalidCommandException;

/**
 * Method, that delete last element of collection
 */
public class RemoveLast implements Command {
    @Override
    public void execute() {
        try{
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            if (VehiclesCollection.getVehicle().isEmpty()) {
                System.out.println("Коллекция пуста, нечего удалять");
            } else {
                VehiclesCollection.getVehicle().remove(VehiclesCollection.getVehicle().lastElement());
                System.out.println("Последний элемент успешно удалён");
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }
    @Override
    public String description() {
        return "remove_last : удалить последний элемент из коллекции";
    }
}
