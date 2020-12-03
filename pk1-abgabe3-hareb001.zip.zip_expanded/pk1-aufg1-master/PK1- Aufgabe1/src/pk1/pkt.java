package pk1;

import java.util.Scanner;

public class pkt {

	public static void main(String[] args) {
	
	Einf c = new Einf() ; 
	Scanner x = new Scanner (System.in) ;
	System.out.println("whälen sie  einer ZAHL zwischen 1 UND 2");
	int m =x.nextInt() ;
     

	switch (m)
	{ 
	
	case 1 : 
		System.out.println("Zahl.. ");
		int n =x.nextInt() ; 
		c.ausgeben(n);
		break ; 
	case 2 :
	c.ausgeben(50); 
	break;
	default : System.out.println("Erreur..");
	}
	
	} 

}
