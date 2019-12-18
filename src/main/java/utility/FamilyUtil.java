package utility;

import model.Person;
import service.impl.PrinterService;

import java.util.List;
import java.util.stream.Collectors;

public class FamilyUtil {

    public static List<String> orderByPriority(List<Person> names) {
        return names.stream()
                .sorted((o1, o2) -> o1.getPriority() > o2.getPriority() ? 1 : 0)
                .map(Person::getName)
                .collect(Collectors.toList());
    }

    public static void formatAndPrint(List<String> relations) {
        PrinterService.getSingletonService().print(relations);
    }
}