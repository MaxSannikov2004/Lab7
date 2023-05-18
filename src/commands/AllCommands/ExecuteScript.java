package commands.AllCommands;

import commands.Command;
import commands.Invoker;
import exeptions.InvalidCommandException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecuteScript implements Command {

    /**
     * Field, that count amount of execute_script repeats
     * */
    private static int recursionChecker = 0;
    /**
     * Field, that stop program if amount of repeats will be more, than 10
     * */
    private boolean recursion = false;

    /**
     * Method, that read commands from file
     * @see ExecuteScript#invokerFromFile(Scanner)
     * @throws FileNotFoundException
     */
    private void executorFromFile(String file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(file));
        while (scanner.hasNext() & !recursion) {
            Invoker.setSplit(scanner.nextLine().split(" "));
            try {
                if (recursionChecker < 10) {
                    invokerFromFile(scanner);
                } else {
                    recursionChecker = 0;
                    recursion = true;
                    System.out.println("Рекурсия!");
                }
            } catch (NullPointerException ignored) {}
        }
        scanner.close();
    }
    /**
     * Method, that do commands from file
     * @see Add#adderFromFile(Scanner)
     * @see Update#updaterFromFile(Scanner)
     * @see Command#execute()
     * */
    private void invokerFromFile(Scanner scanner) {
        switch (Invoker.getSplit()[0]) {
            case "add" -> Add.adderFromFile(scanner);
            case "update" -> Update.updaterFromFile(scanner);
            default -> Invoker.getCommandHashMap().get(Invoker.getSplit()[0]).execute();
        }
    }
    /**
     * Метод, проверяющий файл и исполняющий скрипт из файла с помощью executorFromFile
     * @see ExecuteScript#executorFromFile(String)
     */
    @Override
    public void execute() {
        try {
            if (Invoker.getSplit().length == 2) {
                String file = Invoker.getSplit()[1];
                try {
                    if (new File(file).exists() & new File(file).canRead()) {
                        recursion = false;
                        ++recursionChecker;
                        executorFromFile(file);
                    } else {
                        System.out.println("Нет доступа к файлу");
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    System.out.println("Файл не найден");
                }
            } else {
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e){System.out.println(e.getMessage());}
    }
    @Override
    public String description() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит";
    }
}