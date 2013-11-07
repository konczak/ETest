package com.cybercom.telep.etest.helpme;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.cybercom.telep.etest.entity.Role;
import com.cybercom.telep.etest.entity.User;

public class HibernateDDLGenerator {

    public static void main(String[] args) {
        new HibernateDDLGenerator().execute(Dialect.POSTGRESQL, User.class, Role.class);
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
