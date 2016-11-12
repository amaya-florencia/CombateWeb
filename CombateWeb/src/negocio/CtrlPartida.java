package negocio;



import java.util.Random;
import entidades.*;
import util.ApplicationException;

public class CtrlPartida {	
	
	private Personaje personaje1,personaje2;	
	private int vidaOriginal1;
	private int vidaOriginal2;
	private int energiaOriginal1;
	private int energiaOriginal2;
	private int puntosGanador = 10;

	public int getVidaOriginal1() {
		return vidaOriginal1;
	}
	public void setVidaOriginal1(int vidaOriginal1) {
		this.vidaOriginal1 = vidaOriginal1;
	}
	public int getVidaOriginal2() {
		return vidaOriginal2;
	}
	public void setVidaOriginal2(int vidaOriginal2) {
		this.vidaOriginal2 = vidaOriginal2;
	}
	public int getEnergiaOriginal1() {
		return energiaOriginal1;
	}
	public void setEnergiaOriginal1(int energiaOriginal1) {
		this.energiaOriginal1 = energiaOriginal1;
	}
	public int getEnergiaOriginal2() {
		return energiaOriginal2;
	}
	public void setEnergiaOriginal2(int energiaOriginal2) {
		this.energiaOriginal2 = energiaOriginal2;
	}
	public Personaje getPersonaje1() {
		return personaje1;
	}
	public void setPersonaje1(Personaje personaje1) {
		this.personaje1 = personaje1;
	}
	public Personaje getPersonaje2() {
		return personaje2;
	}
	public void setPersonaje2(Personaje personaje2) {
		this.personaje2 = personaje2;
	}
	public CtrlPartida(){
		personaje1 = null;
		personaje2 = null;	
	}
	public CtrlPartida(Personaje per1,Personaje per2){
		personaje1 = per1;
		personaje2=per2;
		//guardo los valores originales para el update 
		this.setVidaOriginal1(personaje1.getVida());
		this.setVidaOriginal2(personaje2.getVida());
		this.setEnergiaOriginal1(personaje1.getVida());
		this.setEnergiaOriginal2(personaje2.getVida());	
		
	}
	public void defensa(Personaje personajeActual) throws ApplicationException{
		
			if(personajeActual.getIdPersonaje()==personaje1.getIdPersonaje()){ //valido que personaje esta atacando
				
				int energiaRecuperada = this.getEnergiaOriginal1()*personajeActual.getDefensa()/100; 
				int vidaRecuperada = this.getVidaOriginal1()*personajeActual.getDefensa()/250;
				
				if(energiaRecuperada + personajeActual.getEnergia() > this.getEnergiaOriginal1()){ // si la energia recuperada supera la original
					personajeActual.setEnergia(this.getEnergiaOriginal1());//seteo el valor maximo que puede recuperar que es el original
				}else{
					personajeActual.setEnergia(energiaRecuperada + personajeActual.getEnergia());//si no seteo lo calculado mas lo que tenia de energia el personaje en el momento
				}
				//lo mismo para la vida
				if(vidaRecuperada +  personajeActual.getVida() > this.getVidaOriginal1()){
					personajeActual.setVida(this.getVidaOriginal1());
				}else{
					personajeActual.setVida(vidaRecuperada + personajeActual.getVida());
				}			
				this.setPersonaje1(personajeActual); //guardo valores en el ctrl
				
			}else{ //si el atacante es el otro personaje
				int energiaRecuperada = this.getEnergiaOriginal2()*personajeActual.getDefensa()/100;
				int vidaRecuperada = this.getVidaOriginal2()*personajeActual.getDefensa()/250;
				
				if(energiaRecuperada +  personajeActual.getEnergia() > this.getEnergiaOriginal2()){
					personajeActual.setEnergia(this.getEnergiaOriginal2());
				}else{
					personajeActual.setEnergia(energiaRecuperada + personajeActual.getEnergia());
				}
				
				if(vidaRecuperada +  personajeActual.getVida() > this.getVidaOriginal2()){
					personajeActual.setVida(this.getVidaOriginal2());
				}else{
					personajeActual.setVida(vidaRecuperada + personajeActual.getVida());
				}
				this.setPersonaje2(personajeActual); //guardo valores en el ctrl
			}
				
			throw new ApplicationException(personajeActual.getNombrePersonaje()+" recupera " + personajeActual.getEnergia() + " de energia y " 
							+ personajeActual.getVida() + " de vida.");
	
		
	}
	
	public void ataque(int energiaAtaque,Personaje atacante, Personaje defensor) throws ApplicationException{
		
			atacante.setEnergia(atacante.getEnergia() - energiaAtaque);	
			boolean evade =this.defensorEvade(energiaAtaque,defensor);
			if(!evade){ //si el defensor no evade
				//le resto a la vida la energia de ataque
				int vidaRestante = defensor.getVida() - energiaAtaque;
				if (vidaRestante > 0){		//si no perdio toda la vida			
					defensor.setVida(vidaRestante);		//seteo nuevo valor de vida				
					throw new ApplicationException("El ataque es efectivo!!  "+ 
								defensor.getNombrePersonaje()+"pierde "+energiaAtaque + "  puntos de vida");
	
				}else{			
						defensor.setVida(0);//si perdio seteo la vida en cero
						throw new ApplicationException("Felicitaciones! Has derrotado a "+defensor.getNombrePersonaje());						
				}
				}else{//cuando evade
				throw new ApplicationException("Tu ataque ha sido evadido!  "+ defensor.getNombrePersonaje()+" conserva sus puntos de vida ");
			}			
		}	
	
	public void premio(Personaje ganador) throws ApplicationException{
		if (ganador == this.getPersonaje1()){//me fijo que personaje gano
			ganador.setEnergia(this.getEnergiaOriginal1()); //le seteo su energia y vida original
			ganador.setVida(this.getVidaOriginal1());
			ganador.setPuntosTotales(this.getPersonaje1().getPuntosTotales() + puntosGanador ); // sumo los puntos ganados
		}else{ //lo mismo para el otro caso
			ganador.setEnergia(this.getEnergiaOriginal2());
			ganador.setVida(this.getVidaOriginal2());
			ganador.setPuntosTotales(this.getPersonaje2().getPuntosTotales() + puntosGanador );
		}
		CtrlABMCPersonaje ctrlPersonaje = new CtrlABMCPersonaje();
		ctrlPersonaje.update(ganador); // llamo al ctrl de personaje para que haga el update
		throw new ApplicationException("Felicitaciones! "+ganador.getNombrePersonaje()+" has ganado 10 puntos");	
	}
	
	public boolean defensorEvade(int energiaAtaque,Personaje defensor){
		boolean evade;
		Random rnd = new Random(); //genero un random 
		Double i = rnd.nextDouble();
		if (i*100 > defensor.getEvasion()){//no evade ataque			
			evade=false;
		}else{
			evade = true; //evade el ataque
		}
		return evade;
	}
}
