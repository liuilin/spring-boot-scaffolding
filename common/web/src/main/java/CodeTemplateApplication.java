import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.liumulin.common.exceptions")
public class CodeTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeTemplateApplication.class, args);
    }

}
