package BibRebb;
public class BibManagerArray  {
	private static int anz;
	private static int anz1 ; 
	BibEintrag [] bibEi  ; 
    Primaerquelle [] pri  ;
    
    public BibManagerArray()
    {
    	this.bibEi=new BibEintrag[100];
    	this.pri=new Primaerquelle[100];
    }
    
    
    
    
	public void hinzufuegen (BibEintrag element)
	{ 
		
	
			if (anz<bibEi.length)
			{ 
				bibEi[anz]=element ;
				anz++;
				
			}
			else 
				System.out.println("Fehler wrid nicht hinzugefügt") ;
		
	}
	
	
	
	public void druckeAlleEintraege() 
	{ 
		for (int i=0 ; i<anz;i++)
		{
			System.out.println("bibEi["+i+"]=  "+bibEi[i]); 
		}
	}
	
	public void sucheNeustenEintrag() 
	{ 
		int min=0;
		//int minjahr=bibEi[0].berechneAlter() ; 
		for(int i=1; i<anz ; i++)
		
		{ 
			if (bibEi[i].getJahr()>bibEi[min].getJahr())
			{  
				min=i ; 
				
			}
		}
		System.out.print( " Eintrag mit dem jüngsten Jahr   "+bibEi[min].toString());
	}
	
	
	public double berechneErscheinungsjahr()
	{ 
		double summ=0 ;  
		for(int i =0 ; i<bibEi.length;i++) {
			if(bibEi[i]!=null)
	     summ+=bibEi[i].getJahr() ; 
			else 
				summ+=0.0 ; 
		}
		 System.out.println((10)) ; 
		 return summ/anz ;
		
			
	}
	
	public void druckeZitierschluessel(Primaerquelle element )
	
	{
	  int k=0;
		
			if(anz1<pri.length)
			{ 
				pri[anz1]=element ;
				k=anz1;
				anz1++;
			}
		
		System.out.println(pri[k].erzeugeZitierschluessel());
		
	}
	
	
	
}


