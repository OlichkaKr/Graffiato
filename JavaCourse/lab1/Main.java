package lab1;

public class Main {

	public static void main(String[] args) {
		
		Plain plain1 = new Plain();
		Plain plain2 = new Plain("Boeing", 5, 14, 100);
		Plain plain3 = new Plain("Airbus", 12, 47, 1000, 15);
		
		System.out.println(plain1.toString());
		System.out.println(plain2.toString());
		System.out.println(plain3.toString());
		
		Plain.printStaticSum();
		plain3.printSum();
		
		plain1.resetValues("Gulfstream", 5, 5, 20, 10);
		plain3.resetValues("Airbus", 10, 30, 500, 10);
		plain2.setLoadCapacity(15);
		
		System.out.println(plain1.toString());
		System.out.println(plain2.toString());
		System.out.println(plain3.toString());
		
		Plain.printStaticSum();
		plain1.printSum();

	}

}
