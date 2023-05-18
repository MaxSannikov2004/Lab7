package commands.AllCommands;

import vehicleData.Adder;
import vehicleData.VehiclesCollection;
import commands.Command;
import commands.Invoker;
import exeptions.InvalidCommandException;


/**
 * Method, that add new element of collection to specified position
 */
public class InsertAtIndex implements Command {
    @Override
    public void execute(){
        try{
            if(Invoker.getSplit().length != 2){
                throw new InvalidCommandException();
            }
            try {
                VehiclesCollection.getVehicle().insertElementAt(null, Integer.parseInt(Invoker.getSplit()[1]));
                VehiclesCollection.getVehicle().remove(null);
                VehiclesCollection.getVehicle().insertElementAt(Adder.vehicleAdder(), Integer.parseInt(Invoker.getSplit()[1]));
            } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                System.out.println("Такой индекс недопустим");
            } catch (NumberFormatException numberFormatException) {
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }


    @Override
    public String description(){
        return "insert_at index {element} : добавить новый элемент в заданную позицию";
    }
}
