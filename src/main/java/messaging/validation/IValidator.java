package messaging.validation;

public interface IValidator<T> {

    boolean validate(T contactDetails);

}
