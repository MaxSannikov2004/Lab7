package commands.AllCommands;

import vehicleData.VehiclesCollection;
import commands.Command;
import commands.Invoker;
import exeptions.InvalidCommandException;

public class Info implements Command {
    /**
     * Method, that print information about collection
     */
    @Override
    public void execute() {
        try {
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            VehiclesCollection.getInfo();
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }

    @Override
    public String description() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}