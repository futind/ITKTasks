package ru.itk.command;

/**
 * A class describing a command to insert a string into a certain position of a {@link ru.itk.CancelableStringBuilder}
 */
public class InsertCommand implements CancelableStringBuilderCommand {

    private final StringBuilder stringBuilder;

    private final int offset;

    private final String stringToInsert;

    public InsertCommand(StringBuilder stringBuilder,
                         int offset,
                         String stringToInsert) {
        this.stringBuilder = stringBuilder;
        this.offset = offset;
        this.stringToInsert = stringToInsert;
    }

    @Override
    public void execute() {
        stringBuilder.insert(offset, stringToInsert);
    }
}
