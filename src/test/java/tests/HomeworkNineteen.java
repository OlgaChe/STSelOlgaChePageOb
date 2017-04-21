package tests;

import app.Application;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Olga Cherniak on 4/21/2017.
 */
public class HomeworkNineteen {

    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public Application app;

    @Before
    public void start() {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }

        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { app.quit(); app = null; }));
    }

    @Test
    public void addRemoveFromCart() throws InterruptedException {
        app.addToCart();
    }
}
