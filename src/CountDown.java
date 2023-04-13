
public class CountDown implements Runnable {

    public CountDown() {
    }

    @Override
    public void run() {
        for (int i = 5; i >= 0; i--) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(i + " ");
        }
        System.out.println("Start!");
    }
}
