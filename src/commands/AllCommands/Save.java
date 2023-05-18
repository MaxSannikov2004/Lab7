package commands.AllCommands;

import vehicleData.Vehicle;
import vehicleData.VehiclesCollection;
import commands.Command;
import commands.Invoker;
import exeptions.InvalidCommandException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Save implements Command {

    /**
     * Method, that save collection into the file
     */
    @Override
    public void execute() {
        try {
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            try (FileOutputStream outputStream = new FileOutputStream(Invoker.getFile())) {
                for (Vehicle vehicle : VehiclesCollection.getVehicle()) {
                    outputStream.write((vehicle.getName() + ", " + vehicle.getCoordinates().getX() + ", " + vehicle.getCoordinates().getY() + ", " + vehicle.getEnginePower() + ", " + vehicle.getCapacity() + ", " + vehicle.getFuelConsumption() + ", " + vehicle.getType() + "\n").getBytes());
                }
                outputStream.flush();
                outputStream.close();
                System.out.println("Коллекция успешно сохранена в файл");

            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("Файл не найден");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }

    @Override
    public String description() {
        return "save : сохранить коллекцию в файл";
    }
}