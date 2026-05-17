package lesson38;

import lesson38.config.AppConfig;
import lesson38.service.ReportService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        ReportService reportService = context.getBean(ReportService.class);
        reportService.generateReport();

        int productsCount = reportService.countProducts();
        System.out.println("Количество товаров: " + productsCount);

        reportService.methodWithoutLogging();

        context.close();
    }
}