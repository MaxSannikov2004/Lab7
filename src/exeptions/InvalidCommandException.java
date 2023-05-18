package exeptions;

public class InvalidCommandException extends Exception{
    public InvalidCommandException() {
        super("Неверная команда");
    }
}