
package Sesion2;
import java.util.Scanner;

public class Bingo {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int bolas = 0, filas = 0, columnas = 0;
		int numeroJugadores = 0;
		boolean finPartida = false, bingo = false, linea = false;

		Bombo bombo;
		
		
		int numeroExtraido;
		while(!finPartida) {
			Butlleta[] cartones = null;
			
			
			while(bolas < 70 || bolas > 90) {
				System.out.println("Introduce el numero de bolas. Minimo 70 maximo 90");
//				bolas = scanner.nextInt();
				bolas = 80;
			}
			
			do {
				System.out.println("El conjunto de filas y columnas tiene que ser entre 15 y 25.");
				System.out.println("Introduce el numero de filas");
//				filas = scanner.nextInt();
				filas = 3;
				System.out.println("Introduce el numero de columnas");
//				columnas = scanner.nextInt();
				columnas = 5;
			}while(filas * columnas < 15 || filas * columnas > 25);
			
			while(numeroJugadores <= 0 || numeroJugadores > 10) {
				System.out.println("Introduce el numero de jugadores. Minimo 2 y maximo 10");
//				numeroJugadores = scanner.nextInt();
				numeroJugadores = 5;
				cartones = new Butlleta[numeroJugadores];
				
				if(numeroJugadores > 0 && numeroJugadores <= 10) {
					System.out.println("----CARTONES GENERADOS -----\n");
					for(int i = 0; i < numeroJugadores; i++) {
						cartones[i] = new Butlleta(columnas, filas, bolas);
						cartones[i].mostrarCarton();
						System.out.print("\n");
					}
				}
			}
			
//			Mostramos la info
			System.out.println("\n--- INFORMACION DE LA PARTIDA ---");
			System.out.println(" Bolas: " + bolas);
			System.out.println(" Filas: " + filas + " - Columnas: " + columnas);
			System.out.println(" Numero de jugadores: " + numeroJugadores);
			System.out.println("---------------------------------\n");
			
			
			bombo = new Bombo(bolas);
			while(!bingo) {
				System.out.println("Seguimos extraiendo bolas");
				numeroExtraido = bombo.extraerBola();
				System.out.println("Bola afortunada: " + numeroExtraido);
				for(int i = 0; i < numeroJugadores; i++) {
					
					System.out.println("Jugador " + (i+1) +":");
					cartones[i].marcarCarton(numeroExtraido);
					cartones[i].mostrarAciertosFilas();
					
					
					if(cartones[i].comprobarBingo()) {
						bingo = true;
						System.out.println("BINGO!!!!!!!!");
						break;
						
					}
				}
			}
			System.out.println("Quieres seguir jugando? [S/N]");
			String continuar = scanner.next();
			if(continuar.equalsIgnoreCase("N")) {
				finPartida = true;
				System.out.println("Adios!");
			}else {
				bingo = false;
				bombo.resetearBombo();
				numeroJugadores = 0;
			}
			
			System.out.println("\n\n\n\n\n\n");
			
		}
		
		



//		
//		// TEST!!! LOS VALORES NO PUEDEN ESTAR REPETIDOS
//		int[] bolasRandom = new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
//		
//		for(int i = 0; i<bolasRandom.length; i++) {
//			boolean match = butlleta.marcarCarton(bolasRandom[i]);
//			if(match) {
//				System.out.println("El numero " + bolasRandom[i] + " coincide? " + match);
//			}
//		}
//		butlleta.mostrarAciertosFilas();
//		butlleta.comprobarBingo();
	}

}
