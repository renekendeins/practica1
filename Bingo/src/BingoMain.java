import java.util.Random;
import java.util.Scanner;

public class BingoMain {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int numberBalls = 0;
		int numberPlayers = 0;
		int numberRows = 0;
		int numberColumns = 0;
		int method = 0;
		int[][][] players;
		boolean[] bombo = null;				
				
		System.out.println("---BINGO---");		
		
		numberBalls = generateBalls(method);
		
		numberPlayers = generatePlayers(method);
		
		do {
			numberRows = generateRowsColumns(0, "fila");
			numberColumns = generateRowsColumns(1, "columna");
			if (numberRows * numberColumns < 15 || numberRows * numberColumns > 25) {
				System.out.println("El conjunto de lineas y columnas es incorrecto. El conjunto tiene que ser ente 15 y 25.");
				System.out.println("----------------------------------------------");
			}									
		}
		while (numberRows * numberColumns < 15 || numberRows * numberColumns > 25);		
		
		bombo = bombo(bombo, numberBalls);
		
		players = new int[numberPlayers][numberRows][numberColumns];
		
		game(numberPlayers, numberRows, numberColumns, numberBalls, players, bombo);
	}
	
	private static void game(int numberPlayers, int numberRows, int numberColumns, int numberBalls, int[][][] players, boolean[] bombo) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		int numPartida = 1;
		int numberTaken;
		boolean boot = true;
		String answer = " ";
		boolean[] check = new boolean[1];
		boolean[] bingo;	
		int jugador = 1;
				
		while (boot || answer.equalsIgnoreCase("Si")) {			
			for (int i = 0; i < players.length; i++) {
				players[i] = generateCartons(players[i], numberBalls);
			}
			for (int p = 0; p < players.length; p++) {
				System.out.println("---PAPELETA JUGADOR " + jugador + "---");
				jugador++;
				printTables(players[p]);
			}
			jugador = 0;
			bingo = new boolean[numberPlayers];
			check[0] = false;
			answer = " ";
			System.out.println();
			System.out.println();
			System.out.println("---PARTIDA " + numPartida + "---");
			while (checkBingoArray(bingo) == false) {
				System.out.println("---SE SACA BOLA---");
				numberTaken = takeBall(numberBalls, bombo);
				System.out.println("HA SALIDO LA BOLA: " + numberTaken);
				checkPlayers(players, numberRows, numberColumns, numberTaken, bingo, check);
				System.out.println(" ");
			}
			System.out.println("---PARTIDA ACABADA---");
			System.out.println(" ");
			statistics(numberPlayers, numberRows, numberColumns, players, bombo);
			System.out.print("Quieres hacer otra partida (Si/No): ");
			answer = scanner.next();
			while (!answer.equalsIgnoreCase("Si") && !answer.equalsIgnoreCase("No")) {
				System.out.println("---INTENTELO DE NUEVO. RESPONDA SI/NO---");
				System.out.print("Respuesta: ");
				answer = scanner.next();
				System.out.println(" ");	
			}
			if (answer.equalsIgnoreCase("No")) {
				boot = false;
				System.out.println("---PARTIDAS JUGADAS: " + numPartida + "---");
			}
			numPartida++;
		}		
	}	
	//Setting para decidir cuantas bolas  --NECESARIO--
	private static int generateBalls(int method) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		int numberBalls;
		
		System.out.print("Indica cuantas bolas quieres que se juegen. El rango tiene que ser entre 70-90: ");
		numberBalls = scanner.nextInt();
		System.out.println(" ");
		
		while (numberBalls < 70 || numberBalls > 90) {
			System.out.println("Numero introducido de bolas incorrecto. El valor tiene que ser entre 70 y 90");
			System.out.println("----------------------------------------------");
			System.out.print("Indica cuantas bolas quieres que se juegen. El rango tiene que ser entre 70-90: ");
			numberBalls = scanner.nextInt();
			System.out.println(" ");
		}
		return numberBalls;
	}
	//Setting para decidir cuantos jugadores  --NECESARIO--
	private static int generatePlayers(int method) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		int numberPlayers = 0;
		
		System.out.print("Cuantos jugadores quieres que juegen. Hay un minimo de 2 y un maximo de 10: ");
		numberPlayers = scanner.nextInt();
		System.out.println(" ");
		
		while (numberPlayers < 2 || numberPlayers > 10) {
			System.out.println("Numero introducido de jugadores incorrecto. El valor tiene que ser entre 2 y 10");
			System.out.println("----------------------------------------------");
			System.out.print("Cuantos jugadores quieres que juegen. Hay un minimo de 2 y un maximo de 10: ");
			numberPlayers = scanner.nextInt();
			System.out.println(" ");
		}
		
		return  numberPlayers;
	}
	//Setting de filas y columnas  --NECESARIO--
	private static int generateRowsColumns(int select, String type) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		int dimension = 0;
		
		System.out.println("Recuerda que el conjunto de las filas y columnas tiene que ser entre 15 y 25");		
	
			System.out.print("Indica el numero de filas. Rango entre 1 y 25: ");
			dimension = scanner.nextInt();
			System.out.println(" ");
			
			while (dimension < 1 || dimension > 25) {
				System.out.println("Numero introducido de " + type + " incorrecto. El valor tiene que ser entre 1 y 25");
				System.out.println("----------------------------------------------");
				System.out.print("Indica el numero de " + type +  " . Rango entre 1 y 25: ");
				dimension = scanner.nextInt();
				System.out.println(" ");
			}
			return dimension;						
	}
	//Genera los cartones  --NECESARIO-- 
	private static int[][] generateCartons(int[][] players, int numberBalls)  {
		Random rdm = new Random();
		int number = 0;
		
		for (int i = 0; i < players.length; i++) {			
			for (int j = 0; j < players[i].length; j++) {
				number = rdm.nextInt(numberBalls);
				while (repeatedNumbers(number, players)) {
					number = rdm.nextInt(numberBalls);
				}
				players[i][j] = number;
			}
		}
		return players;
	}
	//Revisa para que no se repitan numeros en los cartones
	private static boolean repeatedNumbers(int number, int[][] players) {
		for (int i = 0; i < players.length; i++) {
			for (int j = 0; j < players[i].length; j++) {
				if (number == players[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	//Pone todos los valores del bombo a true  --NECESARIO--
	private static boolean[] bombo(boolean[] bombo, int numberBalls) {
		bombo = new boolean[numberBalls];
		
		for (int i = 0; i < numberBalls; i++) {
			bombo[i] = true;
		}
		return bombo;
	}
	//Imprime los cartones  --NECESARIO--
	private static void printTables(int[][] players) {
		for (int i = 0; i < players.length; i++) {
			System.out.print("|");
			for (int j = 0; j < players[i].length; j++) {
				if (players[i][j] < 10) {
					System.out.print(Math.abs(players[i][j]) + " |");
				}
				else {
					System.out.print(Math.abs(players[i][j]) + "|");
				}
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}
	//Imprime el carton de los jugadores que han sacado bingo
	private static void printWinners(int[][][] players, int row, int column) {
		for (int p = 0; p < players.length; p++) {	
			if (checkBingo(players[p]) == true) {
				System.out.println("---PAPELETA JUGADOR " + (p+1) + "---");
				for (int i = 0; i < row; i++) {
					for (int j = 0; j < column; j++) {
						if (players[p][i][j] < 0) {
							System.out.print(Math.abs(players[p][i][j]) + " |");
						}
						else if (players[p][i][j] < -10) {
							System.out.print(Math.abs(players[p][i][j]) + " |");
						}
					}
					System.out.println(" ");
				}
			}
		}
	}
	//Saca bola del bombo  --NECESARIO--
	private static int takeBall(int numberBalls, boolean[] bombo) {
		Random rdm = new Random();
		int number;		
		
		number = rdm.nextInt(numberBalls);
		while (bombo[number] == false) {
			number = rdm.nextInt(numberBalls);
		}
		return number;
	}
	//Mira en el carton del jugador  --NECESARIO--
	private static void checkPlayers(int[][][] players, int row, int column, int ballTaken, boolean[] bingo, boolean check[]) {
		for (int p = 0; p < players.length; p++) {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					if (verifyPaper(players[p], ballTaken) == true) {						
						System.out.println("El jugador " + (p+1) + " ha sacado el numero");
					}
					if (checkLine(players[p], check) == true) {
						System.out.println("EL JUGADOR " + (p+1) + " HA SACADO LINIA");
					}					
				}
			}
			if (checkBingo(players[p]) == true) {
				bingo[p] = true;
				System.out.println("EL JUGADOR " + (p+1) + " HA SACADO BINGO");											
			}
		}
	}
	//Mira si el jugador tiene el numero  --NECESARIO--
	private static boolean verifyPaper(int[][] player, int ball) {
		for (int i = 0; i < player.length; i++) {
			for (int j = 0; j < player[i].length; j++) {
				if (player[i][j] == ball) {
					player[i][j] = ball *= -1;
					return true;
				}
			}
		}
		return false;
	}
	//Imprime las bolas que han sido utilizadas  --NECESARIO--
	private static void printBallsTaken(boolean[] bombo) {
		int contador = 0;	
		
		for (int i = 0; i < bombo.length; i++) {
			if (bombo[i] == false) {
				contador++;
				System.out.print(bombo[i] + "   ");
			}
			if (contador == 5) {
				contador = 0;
				System.out.println(" ");
			}
		}
	}
	//Verifica si el jugador ha sacado LINIA  --NECESARIO-- 
	private static boolean checkLine(int[][] player, boolean[] check) {
		int counter = 0;
		
		if (check[0] == false) {
			for (int i = 0; i < player.length; i++) {
				for (int j = 0; j < player[i].length; j++) {
					if (player[i][j] < 0) {
						counter++;
					}
					if (counter == player[i].length) {
						check[0] = true;
						return true;
					}
				}
			} 
		}
		return false;
	}
	//Verifica si el jugador ha sacado BINGO  --NECESARIO-- SACAR LOS DOS ULTIMOS PARAMETROS
	private static boolean checkBingo(int[][] players) {
				
		for (int i = 0; i < players.length; i++) {
			for (int j = 0; j < players[i].length; j++) {
				if (players[i][j] > 0) {
					return false;
				}
			}
		}
		return true;
	}
	//Devuelve true si el jugador tiene bingo
	private static boolean checkBingoArray(boolean[] bingo) {
		for (int i = 0; i < bingo.length; i++) {
			if (bingo[i] == true) {
				return true;
			}
		}
		return false;
	}
	//Mira cuantos enciertos ha hecho cada jugador
	private static void scorePlayers(int[][][] players, int row, int column) {
		int count = 0;
		
		for (int p = 0; p < players.length; p++) {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {					
					if (players[p][i][j] < 0) {
						count++;
					}
				}				
			}
			if (count != row * column) {
				System.out.println("El jugador " + (p+1) + " ha tenido " + count + " enciertos");
			}
			count = 0;
		}
	}
	//Statistics
	private static void statistics(int numberPlayers, int numberRows, int numberColumns, int[][][] players, boolean[] ballsTaken) {
		System.out.println("---GANADORES---");
		printWinners(players, numberRows, numberColumns);
		System.out.println(" ");
		System.out.println("---BOLAS UTILIZADAS---");
		printBallsTaken(ballsTaken);
		System.out.println(" ");
		scorePlayers(players, numberRows, numberColumns);
		System.out.println(" ");
	}
}