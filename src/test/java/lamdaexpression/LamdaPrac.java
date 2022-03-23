package lamdaexpression;

@FunctionalInterface
interface Cab{
	
	public String bookCab(String source, String destination);
}

 

public class LamdaPrac {
	
	public static void main(String[] args) {
		
		Cab d = (source,destination) ->{ System.out.println("Cab is booked from"+source+"to"+destination);
		return ("price : 5000 rs");
		};
		System.out.println(d.bookCab("GKP", "LKO"));
	}
}
