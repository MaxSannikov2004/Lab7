package commands.AllCommands;

import vehicleData.VehiclesCollection;
import commands.Command;
import commands.Invoker;
import exeptions.InvalidCommandException;

public class Clear implements Command {
    /**
     * Method, that clean collection
     */
    @Override
    public void execute() {
        try {
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            VehiclesCollection.getVehicle().clear();
            System.out.println("Коллекция очищена");
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }
    @Override
    public String description() {
        return "clear : очистить коллекцию";
    }
}