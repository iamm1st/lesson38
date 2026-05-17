package lesson38.service;

import lesson38.annotation.LogExecutionTime;
import org.springframework.stereotype.Component;

@Component
public class ReportService {

    @LogExecutionTime
    public void generateReport() {
        System.out.println("Формирование отчета...");
        pause(1200);
        System.out.println("Отчет сформирован");
    }

    @LogExecutionTime
    public int countProducts() {
        System.out.println("Подсчет товаров...");
        pause(700);
        return 150;
    }

    public void methodWithoutLogging() {
        System.out.println("Этот метод не будет записан в файл");
    }

    private void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.out.println("Выполнение метода было прервано");
        }
    }
}