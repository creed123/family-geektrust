package service;

import java.util.Optional;

public class CounterService {

    private static CounterService counter;

    private static Integer count = 0;

    public static CounterService getCounter() {
        if (Optional.ofNullable(counter).isPresent()) {
            return counter;
        }
        counter = new CounterService();
        return counter;
    }

    public static Integer incrementAndGet() {
        count++;
        return count;
    }
}
