package commands.AllCommands;

import commands.Command;
import commands.Invoker;
import exeptions.InvalidCommandException;

public class Exit implements Command {
    /**
     * Method, that completes program without data saving
     * */

    @Override
    public void execute() {
        try {
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            Invoker.setProgramRunning(false);
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }

    @Override
    public String description() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}