package commands.AllCommands;

import vehicleData.VehiclesCollection;
import commands.Command;
import commands.Invoker;
import exeptions.InvalidCommandException;

/**
 * Method, that delete element of collection by specified position
 */
public class RemoveAtIndex implements Command {
    @Override
    public void execute(){
        try{
            if(Invoker.getSplit().length != 2){
                throw new InvalidCommandException();
            }
            try {
                VehiclesCollection.getVehicle().removeElementAt(Integer.parseInt(Invoker.getSplit()[1]));
                System.out.println("Элемент на заданном индексе удалён");
            } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                System.out.println("Такой индекс недостижим");
            } catch (NumberFormatException numberFormatException) {
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }



    @Override
    public String description(){
        return "remove_at index : удалить элемент, находящийся в заданной позиции коллекции";
    }
}
