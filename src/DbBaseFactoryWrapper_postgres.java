
/**
 * The class is a factory for creating instances of the IDbConnectionWrapper
 * interface.
 */
class DbBaseFactoryWrapper_postgres extends DbBaseFactoryWrapper {
    static {
        IFactory.register("postgres", DbBaseFactoryWrapper_postgres.class);
    }

    public DbBaseFactoryWrapper_postgres() {
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            logging.log(e);
        }
    }

    @Override
    /**
     * The function builds the jdbc url for the database.
     * 
     * @return The method is returning the jdbc url for the database.
     */
    public String buildJdbcUrl() {
        return "jdbc:postgresql://" + this.getHost() + ":" + this.getPort() + "/" + this.getDatabase();
    }
}