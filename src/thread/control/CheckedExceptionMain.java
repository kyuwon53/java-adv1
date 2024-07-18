package thread.control;

import static util.ThreadUtils.sleep;

public class CheckedExceptionMain {
    public static void main(String[] args) throws Exception {
        throw new Exception();
    }

    static class CheckedRunnable implements Runnable {
        @Override
        public void run() /*throws Exception*/ {
            // 부모에서 던지지 않은 체크 예외를 구현체 자식에서 던질 수 없다.
            // 부모 클래스 메서드를 호출하는 경우 부모 메서드가 던지는 예외는 처리할 수 있지만, 자식에서 더 넓은 범위의 예외를 던지면 모든 예외를 처리하기 힘들어진다.
            // 예외 처리의 일관성을 해치고, 예상치 못한 런타임 오류를 초래함
            /*throw new Exception();*/  // 주석 풀면 예외 발생

            sleep(1000); // 유틸에서 예외를 잡아서 사용
        }
    }
}
