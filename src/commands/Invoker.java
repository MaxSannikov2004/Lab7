package commands;

import vehicleData.VehiclesCollection;
import commands.AllCommands.*;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;

public class Invoker {
    /**
     * Field, that responsible for program continuation
     */
    private static boolean programRunning = true;
    /**
     * Field, that contain command and arguments from user (from console)
     */
    private static String[] split;
    /**
     * Field, that contain path to file, that used by program
     */
    private static String file;
    /**
     * Collection through which commands are executed
     */
    private static final HashMap<String, Command> commandHashMap = new HashMap<>();
    static {
        commandHashMap.put("help", new Help());
        commandHashMap.put("info", new Info());
        commandHashMap.put("show", new Show());
        commandHashMap.put("add", new Add());
        commandHashMap.put("update", new Update());
        commandHashMap.put("remove_by_id", new RemoveById());
        commandHashMap.put("clear", new Clear());
        commandHashMap.put("save", new Save());
        commandHashMap.put("execute_script", new ExecuteScript());
        commandHashMap.put("exit", new Exit());
        commandHashMap.put("count_greater_than_type", new CountGreaterThanType());
        commandHashMap.put("min_by_capacity", new MinByCapacity());
        commandHashMap.put("remove_last", new RemoveLast());
        commandHashMap.put("remove_all_by_type", new RemoveAllByType());
        commandHashMap.put("insert_at", new InsertAtIndex());
        commandHashMap.put("remove_at", new RemoveAtIndex());
    }
    public static String[] getSplit() {
        return split;
    }
    public static void setSplit(String[] split) {
        Invoker.split = split;
    }
    public static String getFile() {
        return file;
    }
    public static void setProgramRunning(boolean programRunning) {
        Invoker.programRunning = programRunning;
    }
    public static HashMap<String, Command> getCommandHashMap() {
        return commandHashMap;
    }

    /**
     * Method that implements work with the console
     */
    public static void startConsoleProgram() {
        System.out.println("Введите команду (help : вывести справку по доступным командам)");
        Scanner scanner = new Scanner(System.in);
        while (programRunning) {
            try {
                try {
                    split = scanner.nextLine().trim().split(" ");
                } catch (NoSuchElementException noSuchElementException) {
                    System.out.println("Неверный ввод, перезапустите программу");
                    programRunning = false;
                }
                commandHashMap.get(split[0]).execute();
            } catch (NullPointerException nullPointerException) {
                if (programRunning) { System.out.println("Неверная команда"); }
            }
        }
        scanner.close();
    }
    /**
     * Method that checks the given file and starts working with the collection
     * @see Invoker#startConsoleProgram()
     * @see VehiclesCollection#putVehicleFromFile()
     */
    public static void invoker(String[] args) throws FileNotFoundException {
        if (args.length == 1) {
            file = args[0];
            if (new File(file).canRead() & new File(file).exists() & new File(file).canWrite()) {
                if (VehiclesCollection.putVehicleFromFile()) startConsoleProgram();
            } else {
                System.out.println("Нет доступа к файлу");
            }
        } else {
            System.out.println("Неверно указано имя файла");
        }
    }
}