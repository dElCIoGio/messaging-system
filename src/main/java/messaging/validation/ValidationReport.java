package messaging.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationReport {

    private final List<RuntimeException> errors;

    public ValidationReport() {
        errors = new ArrayList<RuntimeException>();
    }

    public void addError(RuntimeException error) {
        this.errors.add(error);
    }

    public List<RuntimeException> getErrors() {
        return errors;
    }

    public String printErrors() {

        return errors.stream()
                .reduce("",
                        (acc, error) -> acc + (acc.isBlank()? "\t - ": "\t\t - ") + error.getMessage() + "\n", String::concat);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }



}
