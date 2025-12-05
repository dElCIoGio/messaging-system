package main.java.messaging.validation;

public interface IValidator<T> {

    boolean validate(T contactDetails);

}
