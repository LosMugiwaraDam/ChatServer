package util;

import java.io.IOException;
import java.util.Scanner;

public class Io {// FUNCIONES DE UTILIDAD
	static Scanner scan = new Scanner(System.in);

	public static void Sop(String texto) {// IMPRIME EN PANTALLA
		System.out.print(texto);
	}

	public static int opMenu(String[] menu, String pred) {// IMPRIME UN MENU Y PIDE UNA OPCION
		Io.Sop("\n\n");
		for (int i = 0; i < menu.length; i++) {
			Sop("\n[" + (i + 1) + "]-" + menu[i]);
		}
		if (pred != "")
			Sop("\n[0]-" + pred);
		return (leerInt("\n\nElija una opcion:\n"));
	}

	public static char leerChar(String texto) {// PIDE UN CARACTER
		Sop(texto);
		return (scan.nextLine().charAt(0));
	}

	public static int leerInt(String texto) {// PIDE UN NUMERO
		Sop(texto);
		int num = scan.nextInt();
		scan.nextLine();
		return (num);
	}

	public static boolean leerBool(String texto) {// PIDE UN BOOLEANO
		Sop(texto);
		char c = leerChar("Y para true N para false");
		c = Character.toUpperCase(c);
		return ('Y' == c ? true : 'N' == c);
	}

	public static String leerString(String texto) {// PIDE UN TEXTO
		Sop(texto);
		return (scan.nextLine());
	}

	public static void pausa() {// PAUSA
		Sop("\n\n\nPulse enter para continuar...");
		scan.nextLine();
	}

	public static void clear() {// BORRA LA PANTALLA
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} catch (IOException | InterruptedException ex) {
		}
	}
}