package Modelo;
import java.util.Random;
public class Matriz {
	Random ram =new Random();
	int i, j, k; //Ciclos for
	int pos[];
	int cortar[][];
	int posi, fila, reng, num;
	String mat[][];
	char pal[];
	char x;
	String datos="", aux="";
	
	public Matriz() {	
		pos = new int[6];
	}
	
	//Generar randoms
	public int[] randoms(int f) {
		//llenando posiciones random
				for(i = 0; i<pos.length; i++) {
					pos[i] = -1;
				}
		//Generando posiciones randon para '-'
				pos[0]= ram.nextInt(f);
				for(i = 1; i<pos.length; i++) {
					num = ram.nextInt(f);
					for(j = 0; j<=i;j++) {
						if(pos[j]!= num) {
							if(pos[j]==-1) {
								pos[j]=num;
							}
						}
						else
						{
							j=i+1;
							i--;
						}
					}
				}
		return pos;
	}
	
	//obtendiendo cantidad maxima de caracteres
	public int Canmax(String[] palabras) {
			int can = palabras[0].length();
			for( i = 1; i<palabras.length; i++) {
				pal = palabras[i].toCharArray();
				if(pal.length >can) {
					can = pal.length;
					}
				}
			can+=6;
			return can;
		}
	
	//obteniendo cantidad maxima de cortes
	public int CanCortes(String[] palabras) {
		int min = palabras[0].length();
		for(i = 1; i<palabras.length; i++) {
			pal = palabras[i].toCharArray();
			if(min>pal.length) {
				min = pal.length;
			}
		}
		return min;
	}
	
	//Creando las Matrices
	public String[][]Creando(int a, int b){
		mat = new String[a][b];
		for(i= 0; i<a; i++) {
			for(j = 0; j<b; j++) {
				mat[i][j] = "-";
			}
		}
		return mat;
	}
	
	//Llenando las matrices
	public String [][]llenando(String a, int con) {
		pal = a.toCharArray();
		for(i = 0; i<pal.length;i++) {
			mat[con][i] = String.valueOf(pal[i]);
		}
	 return mat;
	}
	
	//Intercalando letras y guinoes
	public String[][]Intercalar(int posi,int[]b, String mati[][]){
		for(i = b.length-1; i>=0; i--) {
			 j = mati[posi].length-1;
			num = b[i];
			while(num<j) {
				mati[posi][j] = mati[posi][j-1];
				j--;
			}
			mati[posi][j] ="-";
		}
		return mati;
	}
	
	//Imprimir matriz
	public String toString(String [][]a, String name) {
		datos="";
		datos+="Matriz "+name +": \n";
		for(i = 0; i<a.length;i++) {
			datos+=a[i][0];
			for(j= 1; j<a[i].length;j++) {
				datos+=a[i][j];
			}
			datos+="\n";
		}
		datos+="";
		return datos;
	}
	
	//matriz de cortes
	public int[][] CortHijo(String palabras[], int cortes) {
		cortar = new int[palabras.length][2];
		int res = 0;
		int mod = 0;
		for(i = 0; i<palabras.length; i++) {
			pal = palabras[i].toCharArray();
			res = pal.length / cortes;
			mod = pal.length % cortes;
			cortar[i][0] = res;
			cortar[i][1] = mod;
		}
		return cortar; 
	}
	
	//Creando hijo
	public String[][]CreandoHijo(int a, int b){
		mat = new String[a][b];
		for(i= 0; i<a; i++) {
			for(j = 0; j<b; j++) {
				mat[i][j] = "*";
			}
		}
		return mat;
	}
	
	//Llenando matriz hijo
	public String[][]Llenandohijo(String primero[][], String segundo[][], String hijo[][], int numlet[][], int cortes){
		int index1=0,index2=0,LetM1=0,LetM2=0; 
		String turno = "M1";
		int j,x=0 ,i, k;
    
		for (i = 0; i < primero.length; i++) {
			j =0;
			turno = "M1";
			do {
				for (k = 0; k < cortes; k++) {
					if(k+1<cortes) {
						LetM2= numlet[i][0];
					}
					
					else
					{
						LetM2 = numlet[i][0] + numlet[i][1];
					}
            
				if (turno == "M1") {
					do {
						x=index1;
						hijo[i][j]=primero[i][x];
						j++;
						if (primero[i][x] != "-" ) {
                            LetM1++;
						}
						index1++;
					} while (LetM1<LetM2);             
					
					LetM1=0;
                    do {                    
                    	x=index2;
                    	if (segundo[i][x] != "-" ) {
                            LetM1++;
                        }
                    	index2++;
                    } while (LetM1<LetM2);
                    LetM1=0;
                    
                    turno="M2";
                }
				
            	else {
                  do {                    
                    x=index2;  
                    hijo[i][j]=segundo[i][x];
                    j++;
                    if (segundo[i][x] != "-" ) {
                            LetM1++;
                    }
                    index2++;
                } while (LetM1<LetM2);
                
                LetM1=0;
                do {                    
                    x=index1;
                    if (primero[i][x] != "-" ) {
                            LetM1++;
                    }
                    index1++;                                                            
                } while (LetM1<LetM2);
                
                LetM1=0;
                turno="M1";
                }                                
             }
			
			index1=0;index2=0;
			} while (j+1<cortes);
		}
	return hijo;
	}

	//Eliminando * de mas en la matriz hijo y haciendola cuadrada con gaps
	public String[][] EditarHijo(String[][] hijo){
		int max = 0, cont = 0;
		for(int i = 0; i<hijo.length; i++) {
			cont = 0;
			for(int j = 0; j< hijo[i].length; j++) {
				if(hijo[i][j] == "*") {
					hijo[i][j] ="";
				}
				else
				{
					cont++;
				}
			}
			if(max<cont) {
				max=cont;
			}
		}
		
		for(int k = 0; k<hijo.length;k++) {
			for(int l = 0; l<max;l++) {
				if(hijo[k][l]=="") {
					hijo[k][l] = "-";
				}
			}
		}
		
		return hijo;
	}
	
}
