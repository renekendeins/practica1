
package Sesion2;
import java.util.Random;

public class Bombo {
	
	// Representa el bombo de bolas
	private boolean[] bolasUtilizadas;
	// Numero de bolas que tiene el bombo
	private int bombo;
	
	public Bombo(int bolas) {
		// Numero de boles del bombo
		this.bombo = bolas;
		
		// Inicialitzem el bombo de boles amb totes les boles a TRUE
		this.bolasUtilizadas = new boolean[bolas];
		for(int i = 0; i < bolas; i++) {
			this.bolasUtilizadas[i] = true;
		}
	}
	
	public int extraerBola() {
		/*
		 * Genera un número aleatorio que será la bola extraida.
		 * Valida que la bola no haya sido extraida con anterioridad
		 */
		boolean bolaValida = false;
		int number = 0;
		Random rdm = new Random();
		
		do {
			number = rdm.nextInt(this.bolasUtilizadas.length);
			if(this.bolasUtilizadas[number]) {
				bolaValida = true;
				this.bolasUtilizadas[number] = false;
				// Decrementamos el numero de bolas del bombo
				this.bombo--;
			}
		}while(!bolaValida);
		return number;
		
	}
	
	public boolean comprobarBomboVacio() {
		// Comprobamos si quedan bolas en el bombo 
		 
		if(this.bombo <= 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void mostrarBolasUsadas() {
		// Muestra las bolas que ha usado el bombo y cuantas
		int numeroBolasUsadas = 0;
		System.out.println("\n--------------");
		System.out.println("Bolas usadas: ");
		for(int i = 0; i < this.bolasUtilizadas.length; i++) {
			if(!this.bolasUtilizadas[i]){
				numeroBolasUsadas++;
				if(i >= 0 && i < 10) {
					System.out.print("| 0" + i + " | ");
				}else {
					System.out.print("| " + i + " | ");
				}
				if(numeroBolasUsadas%7 == 0) {
					System.out.println();
				}
			}

		}
		System.out.println("\nSe han usado un total de " + numeroBolasUsadas + " bolas");
		System.out.println("\n--------------");
	}
	
	public void resetearBombo() {
		// Establece todas las bolas como no extraidas
		for(int i = 0; i < this.bolasUtilizadas.length; i++) {
			this.bolasUtilizadas[i] = true;
		}
	}
	
	public int getBolasBombo() {
		 //devuelve el numero de bolas del bombo
		 
		return bombo;
	}
	
	public int getCapacidadBombo() {
		// Devuelve la capacidad del bombo
		return this.bolasUtilizadas.length;
	}
	
}

