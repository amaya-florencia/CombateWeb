package negocio;


import entidades.*;
import util.ApplicationException;
import data.DataPersonaje;

public class CtrlABMCPersonaje {

	
	private DataPersonaje dataPer;


	public CtrlABMCPersonaje(){
	
		dataPer=new DataPersonaje(); //instancio la capa de datos
	}

	
	
	public void agregar(Personaje p) throws ApplicationException {	
		if(!validarNombre(p)){ //valido que no haya un personaje con el mismo nombre
			
			int puntosAsignados = p.getDefensa()+p.getEnergia()+p.getEvasion()+p.getVida();
			
			if(p.getPuntosTotales()>=puntosAsignados){//valido que los puntos asignados no superen a los puntos totales del personaje
				if(p.getDefensa()>20){ //valido que no ingrese una defensa mayor a los 20 p
					throw new ApplicationException("Los puntos de defensa deben ser menor o igual a 20");
				}else if(p.getEvasion()>80){//valido que no ingrese una evasion mayor a los 80 p
					throw new ApplicationException("Los puntos de evasion deben ser menor o igual a 80");
				}
				
			}else {
				throw new ApplicationException("La suma de los puntos asignados no debe ser mayor a "+ p.getPuntosTotales());
			}
		} else {
			throw new ApplicationException("El personaje ya existe");
		}
		dataPer.add(p); // llamo al insert de la capa de datos
	}
	
	private boolean validarNombre(Personaje p) { //valida existencia
		boolean valida = true;
		Personaje encontrado = dataPer.getPersonajeNombre(p.getNombrePersonaje());	
		if (encontrado==null){
			valida=false;
		}
		return valida;
	}
	
	private boolean validarNombre(String nombre) { //valida existencia al inicio del juego
		boolean valida = true;
		Personaje encontrado = dataPer.getPersonajeNombre(nombre);	
		if (!(encontrado==null)){
			valida=true;
		}else{
			valida=false;
		}
		return valida;
	}

	public void update(Personaje p) throws ApplicationException{
		if(this.validarNombre(p.getNombrePersonaje())){ //valida que exista 
			int puntosAsignados = p.getDefensa()+p.getEnergia()+p.getEvasion()+p.getVida();
			if(p.getPuntosTotales()>=puntosAsignados){
				if(p.getDefensa()>20){
					throw new ApplicationException("Los puntos de defensa deben ser menor o igual a 20"); //valida valores
				}else if(p.getEvasion()>80){
					throw new ApplicationException("Los puntos de evasion deben ser menor o igual a 80");
				}else{
					dataPer.update(p); //llama al update de la capa de datos
				}
			}else{
				throw new ApplicationException("La suma de los puntos asignados no debe ser mayor a "+ p.getPuntosTotales());
			}
		}else{
			throw new ApplicationException("El personaje no existe");
		}
		
	}	
	
	public void eliminar(Personaje p)throws ApplicationException{
		dataPer.delete(p);		//llama al delete de la capa de datos
	}
	
	public Personaje buscarPersonajePorNombre(String nombreIngresado)throws ApplicationException{
		
			Personaje per = dataPer.getPersonajeNombre(nombreIngresado); 
			if(per == null){
				throw new ApplicationException("El personaje " + nombreIngresado + " no existe");
			}
	
		return per;
	}

	
	
}
