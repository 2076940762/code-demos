package JdbcUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JdbcInfo {

    String driverClass() default "com.mysql.jdbc.Driver";

    String url();

    String username() default "root";

    String password() default "root";
}
