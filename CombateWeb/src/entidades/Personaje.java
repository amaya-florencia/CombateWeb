package entidades;

public class Personaje {
	
	/*Los personajes tienen las siguientes características:

		Un código que asigna el sistema.
		Un nombre que no debe repetirse.
		Atributos:*/
		private String nombrePersonaje;
		private int idPersonaje,vida, energia, defensa, evasion, puntosTotales, puntosRestantes;
		public String getNombrePersonaje() {
			return nombrePersonaje;
		}

		public void setNombrePersonaje(String nombrePersonaje) {
			this.nombrePersonaje = nombrePersonaje;
		}

		public int getIdPersonaje() {
			return idPersonaje;
		}

		public void setIdPersonaje(int idPersonaje) {
			this.idPersonaje = idPersonaje;
		}

		public int getPuntosRestantes() {
			return puntosRestantes;
		}

		public void setPuntosRestantes(int puntosRestantes) {
			this.puntosRestantes = puntosRestantes;
		}

		public int getVida() {
			return vida;
		}
		
		public void setVida(int vida) {
			this.vida = vida;
		}

		public int getEnergia() {
			return energia;
		}

		public void setEnergia(int energia) {
			this.energia = energia;
		}

		public int getDefensa() {
			return defensa;
		}

		public void setDefensa(int defensa) {
			this.defensa = defensa;
		}

		public int getEvasion() {
			return evasion;
		}

		public void setEvasion(int evasion) {
			this.evasion = evasion;
		}

		public int getPuntosTotales() {
			return puntosTotales;
		}

		public void setPuntosTotales(int puntosTotales) {
			this.puntosTotales = puntosTotales;
		}
		
		
}
