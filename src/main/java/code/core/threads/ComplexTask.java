package code.core.threads;

class ComplexTask {
    private final int taskId;

    public ComplexTask(int taskId) {
        this.taskId = taskId;
    }

    public String execute() {
        try {
            Thread.sleep((int) (Math.random() * 5));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Result from task " + taskId;
    }
}