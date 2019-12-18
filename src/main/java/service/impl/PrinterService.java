package service.impl;

import java.util.List;
import java.util.Optional;

public class PrinterService {

    public static PrinterService printerService;

    public Boolean printOutput;

    public static PrinterService getSingletonService() {
        if (Optional.ofNullable(printerService).isPresent()) {
            return printerService;
        }
        printerService = new PrinterService();
        printerService.printOutput = false;
        return printerService;
    }

    public void setPrintOutput(Boolean printOutput) {
        this.printOutput = printOutput;
    }

    public void print(List<String> values) {
        if (printOutput) {
            values.forEach(s -> System.out.print(s + " "));
            System.out.println();
        }
    }
}
