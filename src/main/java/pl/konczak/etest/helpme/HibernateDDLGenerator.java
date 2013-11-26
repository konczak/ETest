package pl.konczak.etest.helpme;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import pl.konczak.etest.entity.CategoryOfQuestionEntity;
import pl.konczak.etest.entity.ClosedAnswerEntity;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.ImageEntity;
import pl.konczak.etest.entity.RoleEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserGroupEntity;
import pl.konczak.etest.entity.UserPersonalDataEntity;

public class HibernateDDLGenerator {

    public static void main(String[] args) {
        new HibernateDDLGenerator().execute(Dialect.POSTGRESQL,
                ImageEntity.class,
                CategoryOfQuestionEntity.class,
                ClosedAnswerEntity.class,
                ClosedQuestionEntity.class,
                UserEntity.class,
                UserPersonalDataEntity.class,
                RoleEntity.class,
                UserGroupEntity.class);
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
