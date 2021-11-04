package Modelo;
public class Ordenando {
	public Ordenando() {
		
	}
	
	public int[] burbuja(int[] arreglo)
    {
		for (int x = 0; x < arreglo.length; x++) {
	        for (int y = 0; y < arreglo.length - 1; y++) {
	            int elementoActual = arreglo[y],
	                    elementoSiguiente = arreglo[y + 1];
	            if (elementoActual > elementoSiguiente) {
	                // Intercambiar
	                arreglo[y] = elementoSiguiente;
	                arreglo[y + 1] = elementoActual;
	            }
	        }
	    }
		return arreglo;
    }
}
