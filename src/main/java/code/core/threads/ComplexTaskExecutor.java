package code.core.threads;

import java.util.concurrent.*;

class ComplexTaskExecutor {
    private final CyclicBarrier barrier;
    private final ExecutorService executor;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.executor = Executors.newFixedThreadPool(numberOfTasks);
        this.barrier = new CyclicBarrier(numberOfTasks, ()
                -> System.out.println("All tasks are completed. Merging results..."));
    }

    public void executeTasks(int tasksCount) {
        if (tasksCount > 5) {
            throw new IllegalArgumentException("tasksCount should not exceed numberOfTasks");
        }

        for (int i = 0; i < tasksCount; i++) {
            final int taskId = i;
            executor.submit(() -> {
                ComplexTask task = new ComplexTask(taskId);
                String result = task.execute();
                System.out.println(Thread.currentThread().getName() + " completed " + result);
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdown();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}