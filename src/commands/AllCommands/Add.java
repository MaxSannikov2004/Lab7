package commands.AllCommands;

import commands.ArgsChecker;
import commands.Command;
import vehicleData.*;
import java.util.InputMismatchException;
import java.util.Scanner;




    public class Add implements Command {
        /**
         * Method, that adding new object
         */
        @Override
        public void execute() {
            ArgsChecker.ArgsChecker(0);
            Adder.vehicleAdderToDB(Adder.vehicleAdder());
        }
        /**
         * Метод, выполняющий команду add из файла
         * @see Adder#fromFileAdder(Scanner)
         * @see Adder#vehicleAdderToDB(Vehicle)
         */
        protected static void adderFromFile(Scanner scanner) {
            try {
                Adder.vehicleAdderToDB(Adder.fromFileAdder(scanner));
            } catch (InputMismatchException ignored) {}}

    @Override
    public String description() {
        return "add {element} : добавить новый элемент в коллекцию";
    }
}