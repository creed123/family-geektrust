package executor;

import model.Gender;
import model.Person;
import service.CounterService;
import service.PersonRegistryService;
import service.impl.OperationExecutorService;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Family {
    public static void main(String[] args) throws Exception {
        try {
            initialiseFamilyTree();
            PersonRegistryService.getPersonAccessor().print();
            File file = new File(args[0]);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String lineContents = fileScanner.nextLine();
                List<String> spaceSeparatedValues = Arrays.asList(lineContents.split(" "));
                try {
                    OperationExecutorService.getSingletonService().resolveAndExecute(spaceSeparatedValues);
                } catch (Exception exception) {
                }
            }
        } catch (Exception exception) {
            System.out.println("ERROR while processing file. Please check file at the path sepcified.");
        }
    }

    private static void initialiseFamilyTree() throws Exception {
        Person kingShan = new Person("Shan", Gender.MALE, new ArrayList<>(), "Anga", CounterService.incrementAndGet(), null);
        Person queenAnga = new Person("Anga", Gender.FEMALE, new ArrayList<>(), "Shan", CounterService.incrementAndGet(), null);
        PersonRegistryService.getPersonAccessor().registerPerson("Shan", kingShan);
        PersonRegistryService.getPersonAccessor().registerPerson("Anga", queenAnga);

        File file = new File("/Users/harshilnori/Documents/geektrust/src/main/resources/setup/InitialiseTree.csv");
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String lineContents = fileScanner.nextLine();
            List<String> spaceSeparatedValues = Arrays.asList(lineContents.split(" "));
            System.out.println(spaceSeparatedValues.toString());
            try {
                OperationExecutorService.getSingletonService().resolveAndExecute(spaceSeparatedValues);
            } catch (Exception exception) {
                System.out.println(Arrays.stream(exception.getStackTrace())
                        .map(StackTraceElement::toString)
                        .collect(Collectors.joining()));
                return;
            }
        }
    }
}
