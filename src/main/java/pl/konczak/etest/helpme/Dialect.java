package pl.konczak.etest.helpme;

public enum Dialect {

    POSTGRESQL("org.hibernate.dialect.PostgreSQLDialect"),
    MYSQL("org.hibernate.dialect.MySQLInnoDBDialect"),
    ORACLE("org.unhcr.omss.db.oracle.OracleDialectDeferredFK"),
    SYBASE("org.hibernate.dialect.SybaseAnywhereDialect");
    private String className;

    private Dialect(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
