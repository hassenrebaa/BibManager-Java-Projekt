package pk1;

public class Einf {
	public void ausgeben(int n)
	{ 
		for (int i=1 ; i<=n;i++)
		{
			if(i%3==0)
			// erste nachrichte 
				System.out.println("Pinggg..");
			if (i%5==0)
			// zweite nachrichte 
			
				System.out.println("Ponggggg..");
			if ((i%3==0)&&(i%5==0))
			
			// dritte nachrichte
				System.out.println("PinggggPonggggg...");
			else System.out.println(i);
		}


}}