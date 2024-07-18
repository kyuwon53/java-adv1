package thread.control.test;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class CurrentThreadTest1Main {

    public static void main(String[] args) {
        Queue queue = new ConcurrentLinkedQueue();

        Thread thread1 = new Thread(new RandomNumGenerator(queue), "generator-1");
        Thread thread2 = new Thread(new RandomNumGenerator(queue), "generator-2");
        Thread thread3 = new Thread(new RandomNumGenerator(queue), "generator-3");

        Thread printer1 = new Thread(new NumberPrinter(queue), "printer-1");
        Thread printer2 = new Thread(new NumberPrinter(queue), "printer-2");
        Thread printer3 = new Thread(new NumberPrinter(queue), "printer-3");

        thread1.start();
        thread2.start();
        thread3.start();

        printer1.start();
        printer2.start();
        printer3.start();
    }

    static class RandomNumGenerator implements Runnable {
        private Queue queue;

        public RandomNumGenerator(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                Random random = new Random();
                int randomNumber = random.nextInt(1000);
                queue.add(randomNumber);
                sleep(1);
            }
        }
    }

    static class NumberPrinter implements Runnable {
        private Queue queue;

        public NumberPrinter(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                Integer randomNumber = (Integer) queue.poll();
                if (randomNumber != null) {
                    log(randomNumber);
                    sleep(1);
                }
            }
        }
    }
}
