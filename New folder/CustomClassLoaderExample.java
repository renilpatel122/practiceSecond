public class CustomClassLoaderExample {
//    CustomClassLoaderExample() {
//        System.out.println("123");
//    }
    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
//        MyClassLoader loader = new MyClassLoader();
//        loader.loadClass("java.lang.String"); // Already loaded by Bootstrap Loader
//        loader.loadClass("CustomClassLoaderExample"); // Custom loading


//        for (int i = 0; i < 10000; i++) {
//            new CustomClassLoaderExample(); // Creating objects for GC
//        }
//        System.gc();
//        Runtime runtime = Runtime.getRuntime();
//        long heapSize = runtime.totalMemory();  // Total heap memory allocated
//        long heapFree = runtime.freeMemory();   // Free heap memory available
//        long heapUsed = heapSize - heapFree;    // Used memory
//
//        System.out.println(1.0/0.0);

//        String s1 = "Java";
//        String s2 = "Java";
//        System.out.println(s1 == s2); // true (same reference)
//
//        String s3 = new String("Java");
//        String s4 = new String("Java");
//        System.out.println(s3 == s4); // false (different objects in Heap)
//
//        String s5 = new String("Java");
//        String s6 = "Java";
//        System.out.println(s5 == s6); // true (interned, now points to SCP)



//        StringBuilder sb = new StringBuilder("Count: ");
//        StringBuffer sb = new StringBuffer("Count: ");

//        Runnable task = () -> {
//            synchronized(sb)  {
//                for (int i = 0; i < 5; i++) {
//                    int oldLength = sb.length();
//                    sb.append(i).append(" ");
//                    int newLength = sb.length();
//
//                    if (newLength != oldLength + 2) { // Expected increase is 2 (digit + space)
//                        System.out.println("Race condition detected! Length mismatch.");
//                    }
//
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };



//        Thread t1 = new Thread(task);
//        Thread t2 = new Thread(task);
//
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();
//
//        System.out.println("Final StringBuilder Output: " + sb);


//        StringBuilder sb = new StringBuilder("Count: ");
        StringBuffer sb = new StringBuffer("Count: ");

        Thread thread1 = new Thread(() -> {
            synchronized (sb) {
                for (int i = 0; i < 1000; i++) {
                    sb.append(i + " ");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (sb) {
                for (int i = 0; i < 1000; i++) {
                    sb.append(i + " ");
                }
            }
        });

        thread1.start();
        thread2.start();

        // Wait for the threads to finish
        thread1.join();
        thread2.join();

        // Print the final count
        System.out.println("Final count: " + sb);

    }
}
