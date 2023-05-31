package commands;

import commands.concreteCommand.*;
import java.util.HashMap;
import java.util.Scanner;

public class Invoker {
    /** Поле, отвечающее за продолжение работы программы */
    private static boolean programRunning = true;
    /** Поле, содержащее в себе введенные пользователем команду и её аргументы */
    private static String[] split;
    /** Коллекция, через которую осуществляется выполнение команд */
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
    public static void setProgramRunning(boolean programRunning) {
        Invoker.programRunning = programRunning;
    }
    public static HashMap<String, Command> getCommandHashMap() {
        return commandHashMap;
    }
    /** Метод, реализующий работу с консолью
     * @see Command#execute() */
    public static void invoker() {
        System.out.println("Введите команду (help : вывести справку по доступным командам)");
        Scanner scanner = new Scanner(System.in);
        while (programRunning) {
            try {
                split = scanner.nextLine().trim().split(" ");
                commandHashMap.get(split[0]).execute();
            } catch (NullPointerException nullPointerException) {
                if (programRunning) { System.out.println("Неверная команда"); }
            }
        }
        scanner.close();
    }
}
