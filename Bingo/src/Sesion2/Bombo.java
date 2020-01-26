
package Sesion2;
import java.util.Random;

public class Bombo {
	
	// Representa el bombo de bolas
	private boolean[] bolasUtilizadas;
	// Numero de bolas que tiene el bombo
	private int bombo;
	
	public Bombo(int bolas) {
		// Indiquem el numero de boles del bombo
		this.bombo = bolas;
		
		// Inicialitzem el bombo de boles amb totes les boles a TRUE
		this.bolasUtilizadas = new boolean[bolas];
		for(int i = 0; i < this.bolasUtilizadas.length; i++) {
			this.bolasUtilizadas[i] = true;
		}
	}

	public boolean[] getBolasUtilizadas() {
		// devuelve el numero de bolas usadas
		return bolasUtilizadas;
	}

	public void setBolasUtilizadas(boolean[] bolasUtilizadas) {
		this.bolasUtilizadas = bolasUtilizadas;
	}


	public void setBombo(int bombo) {
		// establece el numero de bolas del bombo
		// ?? no se usa ya el constructor para establecer el numero de bolas del bombo??
		this.bombo = bombo;
	}	
	
	public int extraerBola() {
		// Genera un numero aleatorio que sera la bola extraida
		// Valida que la bola no haya sido extraida
		boolean bolaValida = false;
		int number = 0;
		
		do {
			Random rdm = new Random();
			number = rdm.nextInt(this.bombo);
			System.out.println("Extraemos la bola " + number + " del bombo");
			if(this.bolasUtilizadas[number]) {
				bolaValida = true;
				this.bolasUtilizadas[number] = false;
			}
		}while(!bolaValida);
			
		return number;
		
	}
	
	public boolean comprobarBomboVacio() {
		
		boolean vacio = true;
		for(int i = 0; i < this.bolasUtilizadas.length; i++) {
			if(this.bolasUtilizadas[i] == true) {
				System.out.println("Bombo NO vacio");
				return false;
			}
		}	
		System.out.println("Bombo vacio");
		return true;
	}
	
	
	public void mostrarBolasUsadas() {
		System.out.println("Bolas usadas: ");
		for(int i = 0; i < this.bolasUtilizadas.length; i++) {
			if(!this.bolasUtilizadas[i]){
				System.out.println(i + ", ");
			}
		}
	}
	
	public void resetearBombo() {
		for(int i = 0; i < this.bolasUtilizadas.length; i++) {
			this.bolasUtilizadas[i] = true;
		}
	}
	
	public int getBolasBombo() {
		// devuelve el numero de bolas del bombo
		return bombo;
	}
	
	
	
	
	
	
}

