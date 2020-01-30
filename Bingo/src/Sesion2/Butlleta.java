
package Sesion2;
import java.util.Random;

public class Butlleta {
	private int[][] carton;
	private int[] contadorAciertosLinea;
	private int aciertos;
	private int idJugador;
	
	// Otros atributos
	private boolean linea = false; // este carton tiene linea
	@SuppressWarnings("unused")
	private boolean bingo = false;
	
	public Butlleta(int filas, int columnas, int bolas, int id) {
		this.carton = new int[filas][columnas];
		this.rellenarCarton(bolas);
		this.contadorAciertosLinea = new int[columnas];
		this.idJugador = id;
		
	}
	
	private void rellenarCarton(int bolas){
		// Rellena un carton con numeros aleatorios no repetidos

		int[] numerosGenerados = new int[bolas];
		int numeroAleatorio;
		int contadorGenerados = 0;
		// Establecemos los valores a -1 para poder introducir la bola con numero 0
		for(int i = 0; i< bolas; i++) {
			numerosGenerados[i] = -1;
		}
		
		Random rdm = new Random();
		boolean add = true;

		while(contadorGenerados < bolas) {
			numeroAleatorio = rdm.nextInt(bolas);

			
			for(int i = 0; i<bolas; i++) {
				if(numeroAleatorio == numerosGenerados[i]) {
					add = false;
					break;
				}else {
					add = true;
					
				}
			}
			
			if(add) {
				for(int i = 0; i<bolas; i++) {
					if(numerosGenerados[i] == -1) {
						numerosGenerados[i] = numeroAleatorio;
						contadorGenerados++;
						break;
					}
				}
					
			}
	

		}
				
		// Rellenamos el carton
		int contador = 0;
		for(int i = 0; i < this.carton[0].length; i++) {
			for(int j = 0; j < this.carton.length; j++) {
				this.carton[j][i] = numerosGenerados[contador];
				contador++;
			}
		}
		
//		PARA COMPROBAR QUE EL CARTON SE RELLENA CORRECTAMENTE

		
		
//		PARA COMPROBAR QUE NO SE REPITEN AL GENERARLOS
//		for(int i = 0; i<bolas; i++) {
//			int ii = numerosGenerados[i];
//			for(int j = 0; j<bolas; j++) {
//				if(i != j) {
//					if(ii == numerosGenerados[j]) {
//						System.out.println("Duplicado");
//						System.out.println(i + " - " + j);
//					}else {
//						System.out.println("No duplicado");
//					}
//				}
//			}
//		}
		
		

	}

	public void mostrarCarton() {
		System.out.println("Carton del jugador " + this.idJugador);
		for(int i = 0; i < this.carton[0].length; i++) {
			for(int j = 0; j < this.carton.length; j++) {
				if(this.carton[j][i] < 10) {
					System.out.print("| 0" + this.carton[j][i] + " | ");
				}else {
					System.out.print("| " + this.carton[j][i] + " | ");
				}
				
			}
			System.out.println("");
		}
	}
	
	public void resetCarton() {
		this.aciertos = 0;
		for(int i = 0; i < this.carton[0].length; i++) {
			for(int j = 0; j < this.carton.length; j++) {
				this.carton[j][i] = Math.abs(this.carton[j][i]);
			}
			contadorAciertosLinea[i] = 0;
		}
		
	}
	
	public boolean marcarCarton(int numeroExtraido) {
		
		for(int i = 0; i < this.carton[0].length; i++) {
			for(int j = 0; j < this.carton.length; j++) {
				if(this.carton[j][i] == numeroExtraido) {
					// MARCAR CON -1 LOS ACIERTOS
					this.carton[j][i] = this.carton[j][i] * -1;
					/* 
					 Y TAMBIEN CONTAR LOS ACIERTOS POR FILA??
					 YO QUITARIA ESTE ULTIMO ATRIBUTO Y MIRARIA SI UNA FILA TIENE TODOS LOS VALORES EN NEGATIVO 
					*/
					contadorAciertosLinea[i]++;
					aciertos++;
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean mostrarAciertosFilas() {
		boolean linea = false;
		for(int i = 0; i < this.carton[0].length; i++) {
			// El numero de aciertos coincide con el numero de columnas
			if(!this.linea) {
				if(contadorAciertosLinea[i] == this.carton.length) {
					linea = true;
					this.linea = true;
				}
			}
		}
		return linea;
	}
	
	public boolean comprobarBingo() {
		if(this.aciertos == (this.carton.length * this.carton[0].length)) {
			this.bingo = true;
			return true;
		}
		return false;
	}
	
	public int getAciertos() {
		return this.aciertos;
	}
	
	public int getIdJugador() {
		return this.idJugador;
	}
}

