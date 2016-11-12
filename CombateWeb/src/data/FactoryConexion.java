package data;

import java.sql.*;
import util.*;

public class FactoryConexion {
	//setea las variables del string de conexion
	private String dbDriver="com.mysql.jdbc.Driver"; 
	private String host="localhost";
	private String port="3306";
	private String user="Florencia";
	private String pass="karaoke";
	private String db="tp_java";
	private String dbType="mysql";
	
	private Connection conn;
	private int cantConn=0;
	
	private FactoryConexion() throws ApplicationException{
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			throw new ApplicationException("Error del Driver JDBC",e);
		}
	}
	
	private static FactoryConexion instancia;
	
	public static FactoryConexion getInstancia() throws ApplicationException{ //obtiene instancia del factory conexion
		if (instancia==null){
			instancia = new FactoryConexion();
		}
		return instancia;
	}
	
	public Connection getConn(){
		try {
			if(conn==null || conn.isClosed()){ //si la conexion es null o esta cerrada
				conn = DriverManager.getConnection("jdbc:"+dbType+"://"+host+":"+port+"/"+ //me conecto a la bd
						db+"?user="+user+"&password="+pass);
				cantConn++;
			}
		} catch (SQLException e) {
			new ApplicationException("Error al conectar a la DB",e);

		}
		return conn;
	}
	
	public void releaseConn() throws ApplicationException{ //cierra la conexion a la bd
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error al cerrar conexión",e);
		}
		
	}
}