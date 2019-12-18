package executor;

import model.Gender;
import model.Person;
import service.CounterService;
import service.PersonRegistryService;
import service.impl.OperationExecutorService;
import service.impl.PrinterService;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Family {
    public static void main(String[] args) throws Exception {
        initializeFamilyTree();
        PrinterService.getSingletonService().setPrintOutput(true);
        readAndExecuteInput(new File(args[0]));
    }

    private static void initializeFamilyTree() throws Exception {
        Person kingShan = new Person("Shan", Gender.MALE, new ArrayList<>(), "Anga", CounterService.incrementAndGet(), null);
        Person queenAnga = new Person("Anga", Gender.FEMALE, new ArrayList<>(), "Shan", CounterService.incrementAndGet(), null);
        PersonRegistryService.getPersonAccessor().registerPerson("Shan", kingShan);
        PersonRegistryService.getPersonAccessor().registerPerson("Anga", queenAnga);
        readAndExecuteInput(new File("src/main/resources/setup/InitialiseTree.csv"));
    }

    private static void readAndExecuteInput(File file) {
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String lineContents = fileScanner.nextLine();
                OperationExecutorService
                        .getSingletonService()
                        .resolveAndExecute(Arrays.asList(lineContents.split(" ")));
            }
        } catch (Exception exception) {
            System.out.println("ERROR while processing file. Please check file at the path sepcified." +
                    " ------" + Arrays.stream(exception.getStackTrace())
                    .map(StackTraceElement::toString)
                    .collect(Collectors.joining()));
            }
        }
}

