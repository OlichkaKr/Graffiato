package lab1;

public class Main {

	public static void main(String[] args) {
		
		Plain gulfstream = new Plain();
		Plain boeing = new Plain("Boeing", 5, 14, 100);
		Plain airbus = new Plain("Airbus", 12, 47, 1000, 15);
		
		System.out.println(gulfstream.toString());
		System.out.println(boeing.toString());
		System.out.println(airbus.toString());
		
		Plain.printStaticSum();
		airbus.printSum();
		
		gulfstream.resetValues("Gulfstream", 5, 5, 20, 10);
		airbus.resetValues("Airbus", 10, 30, 500, 10);
		boeing.setLoadCapacity(15);
		
		System.out.println(gulfstream.toString());
		System.out.println(boeing.toString());
		System.out.println(airbus.toString());
		
		Plain.printStaticSum();
		gulfstream.printSum();

	}

}
