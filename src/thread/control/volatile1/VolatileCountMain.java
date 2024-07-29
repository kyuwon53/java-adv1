package thread.control.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileCountMain {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(1000);
        task.runFlag = false;
        log("flag = " + task.runFlag + ", count = " + task.count + " in main");
    }

    static class MyTask implements Runnable {
//        boolean runFlag = true; // 캐시 메모리에서 읽어옴
//        long count;
        volatile boolean runFlag = true; // 캐시 메모리에서 읽어옴
        volatile long count;


        @Override
        public void run() {
            log("Task 시작");
            while (runFlag) {
                count++;
                // 1억번에 한번씩 출력
                if (count % 100_000_000 == 0) {
                    log("flag = " + runFlag + ", count = " + count + " in while()");
                }
            }
            log("flag = " + runFlag + ", count = " + count + " 종료");
        }
    }
}
