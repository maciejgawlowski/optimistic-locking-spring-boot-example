package optimistic.config;

import optimistic.exception.*;
import org.hibernate.*;
import org.hibernate.dialect.lock.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityVersionException.class)
    public void handle(EntityVersionException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.PRECONDITION_FAILED.value(), ex.getMessage());
    }

    @ExceptionHandler({StaleObjectStateException.class, OptimisticEntityLockException.class})
    public void handle(StaleObjectStateException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public void handle(NoSuchElementException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

}
