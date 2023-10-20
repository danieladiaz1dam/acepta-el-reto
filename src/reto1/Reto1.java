package reto1;

import java.util.Scanner;

/*
 * https://aceptaelreto.com/problem/statement.php?id=100&cat=6
 */

// Pedir un numero de 4 digitos
// Comprobar si sus digitos son todos iguales
// Ordenar sus dígitos de mayor a menor
// Ordenador sus digitos de menor a mayor
// Restarlos
// Volver a empezar hasta optener 6174
// + Contador
// Imprimir iteraciones necesarias

public class Reto1 {
	public static int[] inttoarray(int num) {
		int[] lista = new int[4];
		lista[0] = num / 1000; // 1.234 => 1
		lista[1] = num / 100 % 10; // 12.34 % 10 => 12 % 10 => 2
		lista[2] = num / 10 % 10; // 123.4 % 10 => 123 % 10 => 3
		lista[3] = num % 10; // 1234 % 10 => 4

		return lista;
	}

	public static int mayoramenor(int[] lista) {
		int index = 0, tmp;
		// Mirar la parte que nos va quedando de la lista
		while (index < lista.length) {
			// Mirar la lista desde el 0 hasta el final menos index, que son los que ya
			// hemos mirado
			for (int i = 0; i < lista.length - 1 - index; i++) {
				// Si el primero numero es mayor que el de la derecha, intercambiarlo
				// de este modo, vamos poniendo el numero mas grande en el final
				if (lista[i] < lista[i + 1]) {
					tmp = lista[i];
					lista[i] = lista[i + 1];
					lista[i + 1] = tmp;
				}
			}

			// Aumentar index, la parte que ya tenemos ordenada
			index++;
		}
		
		// Devolver todos los digitos como un numero entero
		return lista[0] * 1000 + lista[1] * 100 + lista[2] * 10 + lista[3];
	}

	public static int menoramayor(int[] lista) {
		int index = 0, tmp;
		// Mirar la parte que nos va quedando de la lista
		while (index < lista.length) {
			// Mirar la lista desde el 0 hasta el final menos index, que son los que ya
			// hemos mirado
			for (int i = 0; i < lista.length - 1 - index; i++) {
				// Si el primero numero es mayor que el de la derecha, intercambiarlo
				// de este modo, vamos poniendo el numero mas grande en el final
				if (lista[i] > lista[i + 1]) {
					tmp = lista[i];
					lista[i] = lista[i + 1];
					lista[i + 1] = tmp;
				}
			}

			// Aumentar index, la parte que ya tenemos ordenada
			index++;
		}

		// Devolver todos los digitos como un numero entero
		return lista[0] * 1000 + lista[1] * 100 + lista[2] * 10 + lista[3];
	}

	public static void main(String[] args) {
		// Declarar variables necesarias
		final int kaprekar = 6174;
		int num, contador = 0;
		int[] lista = new int[4];
		Scanner sc = new Scanner(System.in);

		// Pedir un numero de 4 digitos
		System.out.printf("Introduce un numero [0-9999]: ");
		num = sc.nextInt();

		// Mirar si tiene menos de 5 digitos
		if (num > 9999) {
			System.out.printf("%d es un numero inválido.\n");
			sc.close();
			return;
		}
		
		// Mirar si el numero ya es la constante de Kaprekar
		if (num == kaprekar) {
			System.out.printf("Esa ya es la constante de Kaprekar.");
			sc.close();
			return;
		}
		
		// Separarlo por digitos
		lista = inttoarray(num);
		
		// Mirar si es un repdigit
		if (lista[0] == lista[1] && lista[0] == lista[2] && lista[0] == lista[3]) {
			System.out.printf("Es un repdigit, necesitas 8 iteracciones.\n");
			sc.close();
			return;
		}

		// Hacer la cuenta de Kaprekar y mirar cuantas iteraciones es necesaria
		while (num != kaprekar) {
			// Es mejor llamar dos veces a la funcion o hacer una variable nueva??
			num = mayoramenor(lista) - menoramayor(lista);
			contador++;
			System.out.printf("%4d - %4d = %4d  (#%d)\n", mayoramenor(lista), menoramayor(lista), num, contador);
			// Actualizar la lista
			lista = inttoarray(num);
		}

		sc.close();
	}
}
