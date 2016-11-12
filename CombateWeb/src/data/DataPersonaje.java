package data;
import java.sql.*;
import java.util.ArrayList;

import entidades.*;
import util.ApplicationException;

public class DataPersonaje {
	public DataPersonaje(){		
	}
	
	public Personaje getPersonajeNombre(String nombreIngresado){
			
		ResultSet rs=null;
		PreparedStatement stmt=null;
		Personaje perEncontrado = null;
		
		try{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement("SELECT idPersonaje,nombrePersonaje,vida,"
					+ "energia,defensa,evasion,puntos_totales FROM personajes WHERE nombrePersonaje = ?"); //le mando la consulta a la bd
			stmt.setString(1, nombreIngresado);		// seteo el parametro del where	
			rs = stmt.executeQuery();//guardo el resultado de la consulta
			if(rs != null && rs.next()) //recorro la consulta 
			{
				//guardo al personaje encontrado
				perEncontrado = new Personaje();
				perEncontrado.setIdPersonaje(rs.getInt("idPersonaje"));
				perEncontrado.setNombrePersonaje(rs.getString("nombrePersonaje"));
				perEncontrado.setVida(rs.getInt("vida"));
				perEncontrado.setEnergia(rs.getInt("energia"));
				perEncontrado.setDefensa(rs.getInt("defensa"));
				perEncontrado.setEvasion(rs.getInt("evasion"));			
				perEncontrado.setPuntosTotales(rs.getInt("puntos_totales"));				
			}
		}catch (SQLException e) {			
			e.printStackTrace();
		} catch (ApplicationException e) {			
			e.printStackTrace();
		}finally {
			try {
				//cierro todo
				if(rs!=null) rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {				
				e.printStackTrace();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}	
		//devuelvo el personaje 
		return perEncontrado;
		
		
	}
	
	
	public void add(Personaje p){
		ResultSet rs=null;
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into personajes(nombrePersonaje,vida,energia,defensa,evasion,puntos_totales)"+
					" values(?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			// PreparedStatement.RETURN_GENERATED_KEYS to be able to retrieve id generated on the db
			// by the autoincrement column. Otherwise don't use it
						
			stmt.setString(1, p.getNombrePersonaje());
			stmt.setInt(2, p.getVida());
			stmt.setInt(3, p.getEnergia());
			stmt.setInt(4, p.getDefensa());
			stmt.setInt(5, p.getEvasion());
			stmt.setInt(6, p.getPuntosTotales());			
			
			stmt.execute();			
			//after executing the insert use the following lines to retrieve the id
			rs=stmt.getGeneratedKeys();
			if(rs!=null && rs.next()){
				p.setIdPersonaje(rs.getInt(1));		
			}			
		} catch (SQLException e) {			
			e.printStackTrace();
		} catch (ApplicationException e) {			
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {				
				e.printStackTrace();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
	}
	public void update(Personaje p){
		
		PreparedStatement stmt=null;	
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
					"update personajes set nombrePersonaje=?, vida=?, energia=?, defensa=?, evasion=?,puntos_totales=?"+
					" where idPersonaje=?");	
			
		
			stmt.setString(1, p.getNombrePersonaje());
			stmt.setInt(2, p.getVida());
			stmt.setInt(3, p.getEnergia());
			stmt.setInt(4, p.getDefensa());
			stmt.setInt(5, p.getEvasion());
			stmt.setInt(6, p.getPuntosTotales());
			stmt.setInt(7, p.getIdPersonaje());
			
			
			stmt.execute();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} catch (ApplicationException e) {			
			e.printStackTrace();
		}
		finally {
			try {
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {				
				e.printStackTrace();
			} catch (ApplicationException e) {				
				e.printStackTrace();
			}
		}
	}
	public void delete(Personaje p){
		PreparedStatement stmt=null;		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"delete from personajes where idPersonaje=?");
			stmt.setInt(1, p.getIdPersonaje());
			stmt.execute();
		} catch (SQLException e) {			
			e.printStackTrace();
		} catch (ApplicationException e) {			
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {				
				e.printStackTrace();
			} catch (ApplicationException e) {				
				e.printStackTrace();
			}
		}		
	}
	
	
}
