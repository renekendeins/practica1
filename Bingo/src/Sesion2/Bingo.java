
package Sesion2;
import java.util.Scanner;

public class Bingo {

	public static void main(String[] args) {
		Bombo bombo = null;
		Butlleta[] cartones = null;
		
		game(bombo, cartones);
	}
	
	public static void game(Bombo bombo, Butlleta[] cartones) {
		// Declaramos e inicializamos las variables
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int bolas = 0, filas = 0, columnas = 0;
		int numeroJugadores = 0, contadorBingos = 0, numeroExtraido = 0;
		boolean finPartida = false, bingo = false, linea = false, lineaAux = false, marcar = false;
		int minJugadores = 2, maxJugadores = 10;
		int minBolas = 70, maxBolas = 90;
		int minFilasColumnas = 15, maxFilasColumnas = 25;
		String continuar;
		
		// Introducimos el numero de jugadores
		while(numeroJugadores < minJugadores || numeroJugadores > maxJugadores) {
			System.out.println("Introduce el numero de jugadores. Minimo 2 y maximo 10");
			// Validamos que los valores introducidos sean numeros enteros
			try {
				numeroJugadores = scanner.nextInt();
				cartones = new Butlleta[numeroJugadores];
			} catch (Exception e) {
				scanner.nextLine();
				System.out.println("El numero de jugadores no es valido");
			}
		}
		
		
		while(bolas < minBolas || bolas > maxBolas) {
			System.out.println("Introduce el numero de bolas. Minimo 70 maximo 90");
			// Validamos que los valores introducidos sean numeros enteros
			try {
				bolas = scanner.nextInt();
			} catch (Exception e) {
				scanner.nextLine();
				System.out.println("Introduce una cantidad valida de bolas");
			}
			
			
		}
		
		do {
			System.out.println("El conjunto de filas y columnas tiene que ser entre 15 y 25.");
			// Validamos que los valores introducidos sean numeros enteros
			try {
				System.out.println("Introduce el numero de filas");
				filas = scanner.nextInt();
				System.out.println("Introduce el numero de columnas");
				columnas = scanner.nextInt();
				
			} catch (Exception e) {
				scanner.nextLine();
				System.out.println("Introduce un valor valido de filas y columnas");
			}
		}while(filas * columnas < minFilasColumnas || filas * columnas > maxFilasColumnas);
		
		// Establecemos los cartones segun el numero de jugadores
		for(int i = 0; i < numeroJugadores; i++) {
			cartones[i] = new Butlleta(columnas, filas, bolas, (i+1));
		}
		
		
		while(!finPartida) {
			// Mostramos los cartones
			System.out.println("----CARTONES GENERADOS -----\n");
			for(int i = 0; i < numeroJugadores; i++) {
				cartones[i].mostrarCarton();
				System.out.print("\n");
			}
			
			
			//	Mostramos la informacion de la partida
			System.out.println("\n--- INFORMACION DE LA PARTIDA ---");
			System.out.println(" Bolas: " + bolas);
			System.out.println(" Filas: " + filas + " - Columnas: " + columnas);
			System.out.println(" Numero de jugadores: " + numeroJugadores);
			System.out.println("---------------------------------\n");
						
			bombo = new Bombo(bolas);

			// El juego se empieza a ejecutar aqui en  bucle
			while(!bingo) {
				numeroExtraido = bombo.extraerBola();
				System.out.println("\nExtraemos la bola " + numeroExtraido + " del bombo\n------------\n");
				
				for(int i = 0; i < numeroJugadores; i++) {			
					// Marcamos los cartones de los jugadores con la bola extraida del bombo
					marcar = cartones[i].marcarCarton(numeroExtraido);
					if(marcar) {
						// Mostramos que el jugador tiene el numero
						System.out.println("Jugador " + cartones[i].getIdJugador() +":");
						System.out.println("Tiene el numero " + numeroExtraido);
					}
					// Comprobamos si el jugador tiene linea
					linea = cartones[i].mostrarAciertosFilas();
					
					if(linea && !lineaAux) {
						// Una vez se haya gritado linea ya no se podra gritar mas
						lineaAux = true;
						System.out.println("!!!!LINEA!!!!");
					}
					
					// Comprobamos si el jugador tiene bingo
					if(cartones[i].comprobarBingo()) {
						bingo = true;
						// Contamos los bingos que se gritan en esta jugada
						contadorBingos++;
						System.out.println("BINGO!!!!!!!!");
					}

				}
			}
			
			// Mostramos las estadisticas de la partida
			System.out.println("\n\n**************************\nESTADISTICAS DE LA PARTIDA");
			bombo.mostrarBolasUsadas();
			
			System.out.println("Se ha gritado " + contadorBingos + " bingo/s de: ");
			for(int i = 0 ; i < numeroJugadores; i++) {
				if(cartones[i].comprobarBingo()) {
					System.out.println("Jugador " + cartones[i].getIdJugador());
				}
			}
			System.out.println("\n-----------------------------");
			System.out.println("Aciertos de los no ganadores");
			for(int i = 0 ; i < numeroJugadores; i++) {
				if(!cartones[i].comprobarBingo()) {
					System.out.println("Jugador " + cartones[i].getIdJugador() + " tiene " + cartones[i].getAciertos() + " aciertos");
				}
			}
			
			System.out.println("\n**************************\n\nQuieres seguir jugando? [S/N]");
			
			// Esperamos a que el jugador decida empezar otra partida
			continuar = scanner.next();
			if(continuar.equalsIgnoreCase("N")) {
				finPartida = true;
				System.out.println("Adios!");
			}else {
				// Si hay nueva partida reseteamos los cartones y las estadisticasa
				bingo = false;
				bombo.resetearBombo();
				contadorBingos = 0;
				for(int i = 0; i < numeroJugadores; i++) {
					cartones[i].resetCarton();
				}
				for(int i=0; i<50;i++)System.out.println("\n");
			}
		}
	}
}
