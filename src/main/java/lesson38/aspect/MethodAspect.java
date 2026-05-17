package lesson38.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class MethodTimeLoggingAspect {

    private static final Path LOG_FILE_PATH = Path.of("method-log.txt");

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    @Around("@annotation(lesson38.annotation.LogExecutionTime)")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalDateTime callTime = LocalDateTime.now();
        long startTime = System.nanoTime();

        try {
            return joinPoint.proceed();
        } finally {
            long endTime = System.nanoTime();
            long executionTimeInMilliseconds = (endTime - startTime) / 1_000_000;

            String methodName = joinPoint.getSignature().toShortString();

            String logMessage = String.format(
                    "Метод: %s | Время вызова: %s | Время работы: %d мс%n",
                    methodName,
                    callTime.format(DATE_TIME_FORMATTER),
                    executionTimeInMilliseconds
            );
            writeLogToFile(logMessage);
        }
    }

    private void writeLogToFile(String logMessage) {
        try {
            Files.writeString(
                    LOG_FILE_PATH,
                    logMessage,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException exception) {
            System.out.println("Не удалось записать инфу в файл логов");
        }
    }
}