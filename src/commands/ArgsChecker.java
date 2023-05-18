package commands;

public class ArgsChecker {
    /**
     * Метод для проверки количества аргументов команды
     * @param amountOfArgs количество аргументов команды
     */
    public static void ArgsChecker(int amountOfArgs) {
        if (Invoker.getSplit().length - 1 != amountOfArgs) {
            throw new NullPointerException();
        }
    }
}
