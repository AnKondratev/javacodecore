package code.core;

import java.util.Stack;

public class MyStringBuilder {
    private StringBuilder stringBuilder;
    private final Stack<String> history;

    public MyStringBuilder() {
        this.stringBuilder = new StringBuilder();
        this.history = new Stack<>();
    }

    private void saveState() {
        history.push(stringBuilder.toString());
    }

    public MyStringBuilder append(String str) {
        saveState();
        stringBuilder.append(str);
        return this;
    }

    public void delete(int index, int length) {
        saveState();
        if (stringBuilder.length() < length) {
            throw new StringIndexOutOfBoundsException();
        }
        stringBuilder.delete(index, index + length);
    }

    public void undo() {
        if (!history.isEmpty()) {
            stringBuilder = new StringBuilder(history.pop());
        }
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}


