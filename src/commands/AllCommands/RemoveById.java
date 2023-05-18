package commands.AllCommands;

import vehicleData.Vehicle;
import vehicleData.VehiclesCollection;
import commands.Command;
import commands.Invoker;
import exeptions.InvalidCommandException;
import java.util.List;

public class RemoveById implements Command {

    /**
     * Method, that delete element of collection By id
     * @param id - id of element^ tat will be deleted
     */
    private void removerById(long id) {
        List<Vehicle> list = VehiclesCollection.getVehicle().stream().filter(vehicle -> vehicle.getId() == id).toList();
        if (list.isEmpty()) {
            System.out.println("Такого элемента не существует");
        } else {
            VehiclesCollection.getVehicle().remove(list.get(0));
            System.out.println("Элемент успешно удалён");
        }

    }
    /**
     * Method, that do command by removerById
     * @see RemoveById#removerById(long)
     */
    @Override
    public void execute() {
        try {
            if (Invoker.getSplit().length != 2) {
                throw new InvalidCommandException();
            }
            try {
                Long.parseLong(Invoker.getSplit()[1]);
            } catch (NumberFormatException ex) {
                throw new InvalidCommandException();
            }
            long id = Long.parseLong(Invoker.getSplit()[1]);
            if (!VehiclesCollection.getVehicle().isEmpty()) {
                removerById(id);
            } else {
                System.out.println("Коллекция пуста, такого элемента не существует");
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }
    @Override
    public String description() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
