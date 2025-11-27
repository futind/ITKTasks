package ru.itk.command;

/**
 * A class describing a command to delete a substring from a {@link ru.itk.CancelableStringBuilder}
 */
public class DeleteCommand implements CancelableStringBuilderCommand {

    private final StringBuilder stringBuilder;

    private final int startIndex;

    private final int endIndex;

    public DeleteCommand(StringBuilder stringBuilder,
                         int startIndex,
                         int endIndex) {
        this.stringBuilder = stringBuilder;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void execute() {
        stringBuilder.delete(startIndex, endIndex);
    }
}
