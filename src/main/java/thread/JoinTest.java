package thread;

/**
 * Title: JoinTest
 * Description:
 * author: liujie
 * date: 2018-02-27 下午4:54
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        B b = new B(a);
        a.start();
        Thread.sleep(1000L);
        b.start();
//        b.join();
        System.out.println("main end");
    }

    static class A extends Thread{
        @Override
        public void run() {
            System.out.println("A start");
            for(int i = 0 ; i< 5 ; i++){
                System.out.println("A : " + i);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("A end");
        }
    }

    static class B extends Thread{
        private A a;
        B(A a){
            this.a = a;
        }

        @Override
        public void run() {
            System.out.println("B start");

            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("B end");
        }
    }
}
