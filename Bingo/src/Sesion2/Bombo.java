
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
		for(int i = 0; i < bolas; i++) {
			this.bolasUtilizadas[i] = true;
		}
	}

	/*
	 * FOLLOW ME ON INSTAGRAM @gmarsi 
	*/
	
	public int extraerBola() {
		// Genera un numero aleatorio que sera la bola extraida
		// Valida que la bola no haya sido extraida
		boolean bolaValida = false;
		int number = 0;
		Random rdm = new Random();
		
		do {
			number = rdm.nextInt(this.bolasUtilizadas.length);
//			this.mostrarBolasUsadas();
			if(this.bolasUtilizadas[number]) {
				System.out.println("\n-----------");
				System.out.println("Extraemos la bola " + number + " del bombo");
				System.out.println("------------\n");
				bolaValida = true;
				this.bolasUtilizadas[number] = false;
				this.bombo--;
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
		int numeroBolasUsadas = 0;
		System.out.println("\n--------------");
		System.out.println("Bolas usadas: ");
		for(int i = 0; i < this.bolasUtilizadas.length; i++) {
			if(!this.bolasUtilizadas[i]){
				numeroBolasUsadas++;
				System.out.print(i + ", ");
			}
		}
		System.out.println("Se han usado un total de " + numeroBolasUsadas + " bolas");
		System.out.println("--------------\n");
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

