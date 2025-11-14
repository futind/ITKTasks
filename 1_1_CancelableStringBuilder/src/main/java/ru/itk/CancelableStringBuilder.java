package ru.itk;

import ru.itk.command.AppendCommand;
import ru.itk.command.CancelableStringBuilderCommand;
import ru.itk.command.DeleteCommand;
import ru.itk.command.InsertCommand;

import java.util.ArrayDeque;

/**
 * A custom StringBuilder wrapper with the ability to undo last operation.
 * Every operation here is lazy and is not going to be applied until {@link CancelableStringBuilder#toString()} is called.
 */
public class CancelableStringBuilder {

    private final StringBuilder stringBuilder;

    // I don't think that a proper implementation of Memento pattern is warranted here, a deque is enough to act like it.
    /**
     * A deque which acts like a memento-holder.
     */
    private final ArrayDeque<CancelableStringBuilderCommand> mementoDeque = new ArrayDeque<>();;

    public CancelableStringBuilder() {
        stringBuilder = new StringBuilder();
    }

    public CancelableStringBuilder(String string) {
        stringBuilder = new StringBuilder(string);
    }

    public CancelableStringBuilder(int capacity) {
        stringBuilder = new StringBuilder(capacity);
    }

    /**
     * A method which appends the stringToAppend at the end.
     * @param stringToAppend - a string to append at the end.
     * @return {@link CancelableStringBuilder} to chain operations further.
     */
    public CancelableStringBuilder append(String stringToAppend) {
        mementoDeque.push(new AppendCommand(stringBuilder, stringToAppend));
        return this;
    }

    /**
     * A method which inserts the stringToInsert at a given position.
     * @param offset - a position to insert a string into.
     * @param stringToInsert - a string to insert.
     * @return {@link CancelableStringBuilder} to chain operations further.
     */
    public CancelableStringBuilder insert(int offset, String stringToInsert) {
        mementoDeque.push(new InsertCommand(stringBuilder, offset, stringToInsert));
        return this;
    }

    /**
     * A method which deletes a substring from a string.
     * @param start - a start index (inclusive).
     * @param end - an end index (exclusive).
     * @return {@link CancelableStringBuilder} to chain operations further.
     */
    public CancelableStringBuilder delete(int start, int end) {
        mementoDeque.push(new DeleteCommand(stringBuilder, start, end));
        return this;
    }

    /**
     * A method which reverts the result of previous operation.
     * It does not "revert" per se, it actually never allows previous operation to be executed.
     * @return
     */
    public CancelableStringBuilder undo() {
        if (!mementoDeque.isEmpty()) {
            mementoDeque.pop();
        }

        return this;
    }

    @Override
    public String toString() {
        mementoDeque.descendingIterator().forEachRemaining(CancelableStringBuilderCommand::execute);
        return stringBuilder.toString();
    }
}
