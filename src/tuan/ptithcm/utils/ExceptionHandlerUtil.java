package ptithcm.utils;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.springframework.ui.ModelMap;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.Set;

public class ExceptionHandlerUtil {

    public static void handleException(Transaction transaction, Exception e, ModelMap model) {
        if (transaction != null) {
            transaction.rollback();
        }

        String errorMessage = "Lỗi: " + e.getMessage();
        String exceptionClassName = e.getClass().getSimpleName();
        System.out.print(exceptionClassName);
        if (e instanceof StaleStateException) {
            errorMessage = "Xóa + cập nhật thất bại: " + e.getMessage();
        } else if (e instanceof org.hibernate.exception.ConstraintViolationException) {
            errorMessage = handleHibernateConstraintViolationException((org.hibernate.exception.ConstraintViolationException) e);
        } else if (e instanceof ConstraintViolationException) {
            errorMessage = handleJavaConstraintViolationException((ConstraintViolationException) e);
        }

        model.addAttribute("errorMessage", errorMessage);
    }

    private static String handleHibernateConstraintViolationException(org.hibernate.exception.ConstraintViolationException e) {
        SQLException sqlException = e.getSQLException();
        if (sqlException != null) {
            return sqlException.getMessage();
        } else {
            return "Lỗi xung đột dữ liệu.";
        }
    }

    private static String handleJavaConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        if (!violations.isEmpty()) {
            ConstraintViolation<?> violation = violations.iterator().next();
            return violation.getMessage();
        } else {
            return "Lỗi xung đột dữ liệu.";
        }
    }
}
