public class Notifier {

    public static final Object notifier = new Object();
    public Notifier() {
        synchronized (notifier) {
            notifier.notify();
        }
    }

}
