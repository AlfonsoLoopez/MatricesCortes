package Modelo;
import java.util.Scanner;
public class Principal {
		
	static Scanner Lector = new Scanner(System.in);
	public static void main(String[] args) {
		//Variables
		Matriz max = new Matriz();
		Ordenando burbuja = new Ordenando();
		int pos[], pos2[];
		int ren, fila = 0, min = 0,cortes = 0;
		int cortpal[][];
		boolean Concortes = false;
		String palabras[];
		String Pri[][], Seg[][], Hijo[][];
		String datos="";

		//Inicializando vector de randoms
		pos = new int[6];
		pos2 = new int[6];
		//Registrando las cadenas de aminoacidos
		System.out.println("Ingrese la cantidad de cadenas que desea ingresar: ");
		ren = Lector.nextInt();
		palabras = new String [ren];
		for(int i = 0; i<ren; i++) {
			System.out.println("Ingrese la " +(i+1) +" cadena de amionacidos: ");
			palabras[i] = Lector.next();
			palabras[i] = palabras[i].toUpperCase();
		}
		//Obteniendo numero maximo de cortes
		min = max.CanCortes(palabras);
		
		//Obteniendo el numero de filas
		fila = max.Canmax(palabras);
		
		//Primera matriz
		Pri = new String[ren][fila];
		Pri = max.Creando(ren, fila);
		
		//Llenando la primera matriz con los elementos
		for(int i = 0; i<palabras.length; i++) {
			Pri = max.llenando(palabras[i], i);
		}
		
		//Inserando los gaps en posiciones random en la matriz padre
		for(int i = 0; i<ren;i++) {
			pos = max.randoms(fila);
			pos = burbuja.burbuja(pos);
			Pri = max.Intercalar(i, pos, Pri);
		}
		
		//Imprimiendo la matriz padre
		datos = max.toString(Pri, "Primera");
		System.out.println(datos);
		
		//Segunda matriz
		Seg = new String[ren][fila];
		Seg = max.Creando(ren, fila);
		
		//Llenando elementos de la segunda matriz
		for(int i = 0; i<palabras.length; i++) {
			Seg = max.llenando(palabras[i], i);
		}
		
		//Insertando los gaps en posiciones aleatorias en la segunda matriz
		for(int i = 0; i<ren;i++) {
			pos2 = max.randoms(fila);
			pos2 = burbuja.burbuja(pos2);
			Seg = max.Intercalar(i, pos2, Seg);
		}
		
		//Imprimiendo la segunda matriz
		datos = max.toString(Seg, "Segunda");
		System.out.println(datos);
		
		//Mostrando el minimo de cortes permitidos
		System.out.println("Máximo numero de cortes permitidos: " +min +"\n");
		
		//Pidiendo el numero de cortes condicionado
		do {
		System.out.println("Ingrese el numero de cortes que desea: \nMinimo: 2");
		cortes = Lector.nextInt();
		
		if(min >= cortes && cortes > 1) {
			Concortes = true;
			System.out.println("Cortes dentro del rango!\n");
		}
		else
		{
			System.out.println("Numero de cortes fuera de rango!\n");
		}
		}while(Concortes != true);
		
		
		//Creando el hijo
		Hijo = new String[ren][fila*2];
		
		//Creando una matriz con el numero de elementos por corte
		cortpal = new int[palabras.length][2];
		cortpal = max.CortHijo(palabras, cortes);
		
		//Mostrando el numero de elementos por corte
		datos ="Numero de aminoacidos: \n";
		for(int i = 0; i<cortpal.length; i++) {
			datos += cortpal[i][0] +", " +cortpal[i][1] +"\n";
		}
		System.out.println(datos);
		
		//Llenando la matriz hijo y mostrandola
		Hijo = max.CreandoHijo(ren, fila*2);
		Hijo = max.Llenandohijo(Pri, Seg, Hijo, cortpal, cortes);
		Hijo = max.EditarHijo(Hijo);
		datos = max.toString(Hijo, "Hijo ");
		System.out.println(datos);
	}
}
