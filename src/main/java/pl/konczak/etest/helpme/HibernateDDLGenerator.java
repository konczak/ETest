package pl.konczak.etest.helpme;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import pl.konczak.etest.entity.CategoryOfQuestion;
import pl.konczak.etest.entity.ClosedAnswer;
import pl.konczak.etest.entity.ClosedQuestion;
import pl.konczak.etest.entity.ClosedQuestionClosedAnswer;
import pl.konczak.etest.entity.Role;
import pl.konczak.etest.entity.User;
import pl.konczak.etest.entity.id.ClosedQuestionClosedAnswerId;

public class HibernateDDLGenerator {

    public static void main(String[] args) {
        new HibernateDDLGenerator().execute(Dialect.POSTGRESQL,
                ClosedQuestionClosedAnswerId.class,
                CategoryOfQuestion.class,
                ClosedAnswer.class,
                ClosedQuestion.class,
                ClosedQuestionClosedAnswer.class,
                User.class,
                Role.class);
    }

    private void execute(Dialect dialect, Class<?>... classes) {
        AnnotationConfiguration configuration = new AnnotationConfiguration();
        configuration.setProperty(Environment.DIALECT, dialect.getClassName());
        for (Class<?> entityClass : classes) {
            configuration.addAnnotatedClass(entityClass);
        }
        SchemaExport schemaExport = new SchemaExport(configuration);
        schemaExport.setDelimiter(";");
        schemaExport.setOutputFile(String.format("%s_%s.%s ",
                new Object[]{"ddl", dialect.name().toLowerCase(), "sql"}));
        boolean consolePrint = true;
        boolean exportInDatabase = false;
        schemaExport.create(consolePrint, exportInDatabase);
    }
}
