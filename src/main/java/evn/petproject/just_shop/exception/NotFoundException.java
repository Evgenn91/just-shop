package evn.petproject.just_shop.exception;

public class NotFoundException extends RuntimeException  {
    public NotFoundException(Long id) {
        super("Not found with id: " + id);
    }
}
