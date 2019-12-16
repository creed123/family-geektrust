package executor;

import model.Gender;
import model.Person;
import service.PersonRegistryService;
import service.impl.OperationExecutorService;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Family {
    public static void main(String[] args) throws Exception {
        try {
            initialiseFamilyTree();
            File file = new File(args[0]);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String lineContents = fileScanner.nextLine();
                List<String> spaceSeparatedValues = Arrays.asList(lineContents.split(" "));
                try {
                    OperationExecutorService.getSingletonService().resolveOperationAndExecute(spaceSeparatedValues);
                } catch (Exception exception) {
                }
            }
        } catch (Exception exception) {
            System.out.println("ERROR while processing file. Please check file at the path sepcified.");
        }
    }

    private static void initialiseFamilyTree() throws Exception {
        Person kingShan = new Person("Shan", Gender.MALE, Collections.EMPTY_LIST, "Anga", 1, null);
        Person queenAnga = new Person("Anga", Gender.FEMALE, Collections.EMPTY_LIST, "Shan", 2, null);
        PersonRegistryService.getPersonAccessor().registerPerson("Shan", kingShan);
        PersonRegistryService.getPersonAccessor().registerPerson("Anga", queenAnga);
    }
}
