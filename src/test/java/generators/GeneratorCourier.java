package generators;

import models.Courier;

import java.util.Random;

public class GeneratorCourier {
    private static final Random random = new Random();

    public static Courier createValidCourier() {
        String testLogin = "qa-test-" + random.nextInt(9999);
        String password = "qapassword" + random.nextInt(999);
        String firstName = "Random kek";
        return new Courier(testLogin, password, firstName);
    }

    public static Courier createUserLoginOnly() {
        String testLogin = "qa-test-" + random.nextInt(9999);
        return new Courier(testLogin, null, null);
    }

    public static Courier createUserPasswordOnly() {
        String password = "qapassword" + random.nextInt(999);
        return new Courier(null, password, null);
    }

    public static Courier createUserFirstNameOnly() {
        String firstName = "name surname" + random.nextInt(999);
        return new Courier(null, null, firstName);
    }
}
