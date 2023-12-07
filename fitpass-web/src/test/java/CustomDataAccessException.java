import org.springframework.dao.DataAccessException;

// CustomDataAccessException implementation for testing
public class CustomDataAccessException extends DataAccessException {
    public CustomDataAccessException(String msg) {
        super(msg);
    }
}