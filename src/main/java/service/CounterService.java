package service;

import java.util.Optional;

public class CounterService {

    public static CounterService counter;

    public Integer count = 0;

    public static CounterService getCounter() {
        if (Optional.ofNullable(counter).isPresent()) {
            return counter;
        }
        counter = new CounterService();
        return counter;
    }
}
