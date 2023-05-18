package commands.AllCommands;
import commands.Command;
import vehicleData.*;
import commands.Invoker;
import exeptions.InvalidCommandException;
public class Show implements Command {

    /**
     * Method, that print all elements of collection
     */
    @Override
    public void execute() {
        try {
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            if (VehiclesCollection.getVehicle().isEmpty()) {
                System.out.println("Коллекция пуста");
            } else {
                for (Vehicle vehicle : VehiclesCollection.getVehicle()) {
                    System.out.println(vehicle);
                }
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }

    @Override
    public String description() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
