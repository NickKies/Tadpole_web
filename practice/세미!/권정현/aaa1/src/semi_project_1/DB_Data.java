package semi_project_1;

public final class DB_Data {
	private static final String url = "jdbc:mariadb://localhost:3307/melon";
	private static final String driver = "org.mariadb.jdbc.Driver";
	private static final String id = "root";
	private static final String pass = "1234";
	public static String getUrl() {return url;}
	public static String getDriver() {return driver;}
	public static String getId() {return id;}
	public static String getPass() {return pass;}
}
