package code.core.stream;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {
    private final int number;

    public FactorialTask(int number) {
        this.number = number;
    }

    @Override
    protected Long compute() {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be greater than zero");
        }
        if (number == 0 || number == 1) {
            return 1L;
        }
        FactorialTask task = new FactorialTask(number - 1);
        task.fork();
        return number * task.join();
    }
}