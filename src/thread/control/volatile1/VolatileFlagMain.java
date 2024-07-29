package thread.control.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        log("runFlag = " + task.runFlag);
        thread.start();

        sleep(1000);
        log("runFlag를 false로 변경 시도");
        task.runFlag = false;
        log("runFlag = " + task.runFlag);
        log("main 종료");
    }

    static class MyTask implements Runnable {
        //        boolean runFlag = true; // 캐시 메모리에서 읽어옴
        volatile boolean runFlag = true; // 메인 메모리에서 읽어옴 , 캐시 메모리 사용시보다 성능이 느려지는 단점이 있음. 꼭 필요한 곳에만 사용


        @Override
        public void run() {
            log("Task 시작");
            while (runFlag) {
                // runFlag가 false로 변하면 탈출
            }
            log("Task 종료");
        }
    }
}
