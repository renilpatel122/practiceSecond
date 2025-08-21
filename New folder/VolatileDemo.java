class VolatileDemo {
    private volatile boolean running = true; // Shared variable

    public void startTask() {
        new Thread(() -> {
            while (running) { // This loop will keep running
                System.out.println("Task is running...");
                try {
                    Thread.sleep(1000);
                    System.out.println("11 flag : "+running);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Task stopped.");
        }).start();
    }

    public void stopTask() {
        System.out.println("21 flag : "+running);
        System.out.println("Stopping task...");
        running = false; // Change will be immediately visible to the thread
        System.out.println("22 flag : "+running);
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileDemo demo = new VolatileDemo();
        demo.startTask(); // Start the task

        Thread.sleep(5000); // Let it run for 5 seconds
        demo.stopTask(); // Stop the task
    }
}
