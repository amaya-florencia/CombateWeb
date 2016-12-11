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
	private CtrlABMCPersonaje ctrl;
	private String mensajes = "";

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
		this.setPersonaje1(per1);
		this.setPersonaje2(per2);
		//guardo los valores originales para el update 
		this.setVidaOriginal1(this.getPersonaje1().getVida());
		this.setVidaOriginal2(this.getPersonaje2().getVida());
		this.setEnergiaOriginal1(this.getPersonaje1().getEnergia());
		this.setEnergiaOriginal2(this.getPersonaje2().getEnergia());
		
	}
	public String defensaEnergia(Personaje defensor) throws ApplicationException{
		String rta = "";
		int energia;
		//valido que personaje esta defendiendo	
			if(defensor.getIdPersonaje()==personaje1.getIdPersonaje()){ 			
				int energiaRecuperada = this.getEnergiaOriginal1()*defensor.getDefensa()/100; 	
				// si la energia recuperada supera la original,
					if(energiaRecuperada + defensor.getEnergia() > this.getEnergiaOriginal1()){ 
					// seteo el valor maximo que puede recuperar que es el original
						energia = this.getEnergiaOriginal1();
						rta = "Haz recuperado tu energia original!";
						this.agregarMensaje(rta);
					}else{
					//si no seteo lo calculado mas lo que tenia de energia el personaje en el momento
						energia = energiaRecuperada + defensor.getEnergia(); 																				
						rta = "Haz recuperado" + energiaRecuperada + "de energia";
						this.agregarMensaje(rta);
				}
				defensor.setEnergia(energia);
			}else{ //si el defensor es el personaje 2
				int energiaRecuperada = this.getEnergiaOriginal2()*defensor.getDefensa()/100;				
				
					if(energiaRecuperada +  defensor.getEnergia() > this.getEnergiaOriginal2()){
						energia = this.getEnergiaOriginal2();
						rta = "Haz recuperado tu energia original!";
						this.agregarMensaje(rta);
					}else{
						energia = energiaRecuperada + defensor.getEnergia();
						rta = "Haz recuperado tu energia original!";
						this.agregarMensaje(rta);
				}		
					defensor.setEnergia(energia);//guardo valores en el ctrl
			}			
			
			return rta;	
		
	}
	public String defensaVida(Personaje defensor){
		String rta = "";	
		int vida;
		if(defensor.getIdPersonaje()==personaje1.getIdPersonaje()){ //valido que personaje esta atacando
			
			int vidaRecuperada = this.getVidaOriginal1()*defensor.getDefensa()/250;					
			
			if(vidaRecuperada +  defensor.getVida() > this.getVidaOriginal1()){
				vida = this.getVidaOriginal1();
				rta = "Haz recuperado tu vida original!";
				this.agregarMensaje(rta);
			}else{
				vida = vidaRecuperada + defensor.getVida();
				rta = "Haz recuperado" + vida + "vida!";
				this.agregarMensaje(rta);
			}			
			defensor.setVida(vida); //guardo valores en el ctrl
			
		}else{ //si el atacante es el otro personaje
			
			int vidaRecuperada = this.getVidaOriginal2()*defensor.getDefensa()/250;			
			
			if(vidaRecuperada +  defensor.getVida() > this.getVidaOriginal2()){
				vida = this.getVidaOriginal2();
				rta = "Haz recuperado tu vida original!";
				this.agregarMensaje(rta);
			}else{
				vida = vidaRecuperada + defensor.getVida();
				rta = "Haz recuperado" + vida + "vida!";
				this.agregarMensaje(rta);
			}
			defensor.setVida(vida); //guardo valores en el ctrl
		}	
		return rta;

}
	
	public String ataque(int energiaAtaque,Personaje atacante, Personaje defensor) throws ApplicationException{
		String rta = "";
		atacante.setEnergia(atacante.getEnergia() - energiaAtaque);	
		boolean evade =this.defensorEvade(energiaAtaque,defensor);
			if(!evade){ //si el defensor no evade
				//le resto a la vida la energia de ataque
				int vidaRestante = defensor.getVida() - energiaAtaque;
				//si no perdio toda la vida	
					if (vidaRestante > 0){				
						defensor.setVida(vidaRestante);		//seteo nuevo valor de vida				
						rta = "El ataque es efectivo!!  "+ defensor.getNombrePersonaje()+" pierde "+energiaAtaque + "  puntos de vida";	
						this.agregarMensaje(rta);
					}else{									
						rta = "Felicitaciones! Has derrotado a "+defensor.getNombrePersonaje()+ "\n" + 
									atacante.getNombrePersonaje()+ "gana la partida y se le otorgan" + puntosGanador + " puntos";	
						this.agregarMensaje(rta);
						this.premio(atacante);				
						
				}
			}else{//cuando evade
			rta = "Tu ataque ha sido evadido!  "+ defensor.getNombrePersonaje()+" conserva sus puntos de vida ";
			this.agregarMensaje(rta);
			}	
			return rta;
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

	public void agregarMensaje(String rta) {
		this.mensajes += (rta + "<br/>");
	}
	
	public String getMensajes() {
		return mensajes;
	}
	
}
