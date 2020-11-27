package itconvergence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Solucion {

	public int solucion(int[][] A, int N, int M) {
		List<Long> prod = new ArrayList<Long>();
		int indiceProd = 0;
						
		for (int i = 1; i < N-1; i++) {			
			for (int j = 1; j < M; j++) {				
				prod.add( indiceProd, new Long( producto( A, i, j, 0, 0, j, i-1 ) ) );
				prod.add( indiceProd + 1, new Long( producto(A, i, j, 0, i + 1, j, N-1) ) );
				indiceProd = indiceProd + 2;
			}			
		}
		
		for( int j = 1; j < M; j++ ) {
			prod.add( indiceProd, new Long( producto( A, 0, j, 0, 1, j, N-1 ) ) );
			
			prod.add( indiceProd + 1, new Long( producto( A, N-1, j, 0, 0, j, N-2 ) ) );
			indiceProd = indiceProd + 2;
		}
		for( int j = 0; j < M-1; j++ ) {
		
			prod.add( indiceProd , new Long( producto( A, 0, j, j, 1, M-1, N-1 ) ) );
			prod.add( indiceProd +1, new Long( producto( A, N-1, j, j, 0, M-1, N-2 ) ) );
			indiceProd = indiceProd + 2 ;
		}
		
		for( int i = N-2 ; i > 0; i--) {
			for(int j= 0; j < M-1; j++) {
				prod.add( indiceProd, new Long( producto( A, i, j, j, 0, M-1, i-1)) );
				prod.add( indiceProd+1, new Long( producto( A, i, j, j, i+1, M-1, N-1)) );
				indiceProd = indiceProd + 2;
			}
		}
		
		Long caminoMasCeros = masCeros( prod );
		System.out.println(" camino con mas ceros : " + caminoMasCeros );
		int cantCeros = contarCeros( caminoMasCeros );
		return cantCeros;
	}

	private long producto(int[][] A, int i, int j, int indiceInferiorK, int indiceInferiorR,
													int indiceSuperiorK, int indiceSuperiorR) {
		long a = 1,b = 1;
		for (int k = indiceInferiorK; k <= indiceSuperiorK; k++) {
			a = A[i][k] * a;
		}
		for (int r = indiceInferiorR; r <= indiceSuperiorR; r++) {
			b = A[r][j] * b;
		}
		return a * b;
	}
	
	private int contarCeros( Long numero ) {
		int ceros = 0;
		while( Math.floorMod( numero, 10L) == 0L) {
			numero = numero / 10L;
			ceros++;
		}
		return ceros;
	}
	
	private Long masCeros(List<Long> prod) {
		int indice = 0, cerosTotal = 0, ceros = 0, i = 0;
		
		for (Iterator iterator = prod.iterator(); iterator.hasNext();) {
			Long long1 = (Long) iterator.next();
			ceros = 0;
			while( Math.floorMod( long1, 10L) == 0L) {
				long1 = long1 / 10L;
				ceros++;
			}
			if( ceros > cerosTotal ) {
				cerosTotal = ceros;
				indice = i;
			}
			i++;
		}
		return prod.get( indice) ;
	}
	
	
	
	
	public static void main(String[] args) {
		int[][] matriz = { { 10, 100, 10 },
							{ 1, 10, 1 },
							{ 1, 10, 1 }
		};
		int[][] matriz2 = { { 10, 100 },
							{ 1, 10 }
		};
		int[][] matriz3 = { { 6, 25, 4, 10 },
							{12, 25, 1, 15},
							{7, 15, 15, 5}
		};
		int[][] matriz4 = { { 6, 25, 4},
				{12, 25, 1},
				{7, 15, 15}
		};
		int[][] matriz5 = { { 6, 25 },
							{ 12, 25 },
							{ 7, 15 }
		};
		
		
		Solucion s = new Solucion();
		System.out.println( " solucion matriz " + s.solucion(matriz, 3, 3) );
		System.out.println( " solucion matriz2 " + s.solucion(matriz2, 2, 2) );
		System.out.println( " solucion matriz3 " + s.solucion(matriz3, 3, 4) );
		System.out.println( " solucion matriz4 " + s.solucion(matriz4, 3, 3) );
		System.out.println( " solucion matriz5 " + s.solucion(matriz5, 3, 2) );

	}
}
