package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV0 {
    public static void main(String[] args) {
        log("Start");
        Thread thread1 = new Thread(new Job(), "thread-1");
        Thread thread2 = new Thread(new Job(), "thread-2");

        thread1.start();
        thread2.start();
        log(thread1.getName() + " state : " + thread1.getState());
        log(thread2.getName() + " state : " + thread2.getState());

        log("End");

        sleep(500);
        log(thread1.getName() + " state2 : " + thread1.getState());
        log(thread2.getName() + " state2 : " + thread2.getState());

        sleep(2000);
        log(thread1.getName() + " state3 : " + thread1.getState());
        log(thread2.getName() + " state3 : " + thread2.getState());
    }

    static class Job implements Runnable {
        @Override
        public void run() {
            log("작업 시작");
            sleep(2000);
            log("작업 완료");
        }
    }
}
