package ru.itk.command;

/**
 * A class describing a command to append a string in the end of a {@link ru.itk.CancelableStringBuilder}
 */
public class AppendCommand implements CancelableStringBuilderCommand {

    private final StringBuilder stringBuilder;

    private final String stringToAppend;

    public AppendCommand(StringBuilder stringBuilder,
                         String stringToAppend) {
        this.stringBuilder = stringBuilder;
        this.stringToAppend = stringToAppend;
    }

    @Override
    public void execute() {
        stringBuilder.append(stringToAppend);
    }

}
