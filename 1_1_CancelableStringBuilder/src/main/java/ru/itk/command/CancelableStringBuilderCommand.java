package ru.itk.command;

/**
 * A simple interface for the Command pattern (GoF)
 */
public interface CancelableStringBuilderCommand {

    /**
     * A method which executes the command
     */
    void execute();
}
