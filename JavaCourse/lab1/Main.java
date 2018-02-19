package lab1;

public class Main {

	public static void main(String[] args) {
		
		Plain Gulfstream = new Plain();
		Plain Boeing = new Plain("Boeing", 5, 14, 100);
		Plain Airbus = new Plain("Airbus", 12, 47, 1000, 15);
		
		System.out.println(Gulfstream.toString());
		System.out.println(Boeing.toString());
		System.out.println(Airbus.toString());
		
		Plain.printStaticSum();
		Airbus.printSum();
		
		Gulfstream.resetValues("Gulfstream", 5, 5, 20, 10);
		Airbus.resetValues("Airbus", 10, 30, 500, 10);
		Boeing.setLoadCapacity(15);
		
		System.out.println(Gulfstream.toString());
		System.out.println(Boeing.toString());
		System.out.println(Airbus.toString());
		
		Plain.printStaticSum();
		Gulfstream.printSum();

	}

}
