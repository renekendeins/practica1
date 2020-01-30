
package Sesion2;
import java.util.Random;

public class Butlleta {
	// Inicializamos los atributos de la clase
	private int[][] carton;
	private int[] contadorAciertosLinea;
	private int aciertos;

	
	// Otros atributos
	private int idJugador; // Establecemos un id del jugador para identificarlo en caso que algun jugador marche a media partida
	private boolean linea = false; // El carton tiene linea
	@SuppressWarnings("unused")
	private boolean bingo = false; // El carton tiene bingo
	
	public Butlleta(int filas, int columnas, int bolas, int id) {
		// Creamos el objeto Butlleta con los parametros del constructor
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
			// Generamos el numero
			numeroAleatorio = rdm.nextInt(bolas);
			
			// Comprobamos si el numero ya lo hemos generado
			for(int i = 0; i<bolas; i++) {
				if(numeroAleatorio == numerosGenerados[i]) {
					add = false;
					break;
				}else { add = true; }
			}
			
			// Buscamos la siguiente posicion sin rellenar e introducimos el numero
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
				
		// Rellenamos el carton con todos los numeros generados de forma aleatoria
		int contador = 0;
		for(int i = 0; i < this.carton[0].length; i++) {
			for(int j = 0; j < this.carton.length; j++) {
				this.carton[j][i] = numerosGenerados[contador];
				contador++;
			}
		}
	}

	public void mostrarCarton() {
		// Muestra el carton del jugador
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
		// Resetea los valores marcados en el carton
		this.aciertos = 0;
		for(int i = 0; i < this.carton[0].length; i++) {
			for(int j = 0; j < this.carton.length; j++) {
				this.carton[j][i] = Math.abs(this.carton[j][i]);
			}
			contadorAciertosLinea[i] = 0;
		}
		
	}
	
	public boolean marcarCarton(int numeroExtraido) {
		// Marca un numero del carton
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
		// Valida si el carton tiene linea valida o no
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
		// Comprueba si hay bingo o no
		if(this.aciertos == (this.carton.length * this.carton[0].length)) {
			this.bingo = true;
			return true;
		}
		return false;
	}
	
	public int getAciertos() {
		// Devuelve el numero de aciertos
		return this.aciertos;
	}
	
	public int getIdJugador() {
		// Devuelve el ID del jugador
		return this.idJugador;
	}
}

