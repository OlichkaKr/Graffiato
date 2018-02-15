package lab1;

public class Plain {
	private String name = "no name";
	private double size;
	private double weight;
	private int passengers;
	private double loadCapacity;
	private static double totalLoadCapacity = 0;
	
	public Plain() {		
	}
	
	public Plain(String name, double size, double weight, int passengers) {
		setName(name);
		setSize(size);
		setWeight(weight);
		setPassengers(passengers);	
	}
	
	public Plain(String name, double size, double weight, int passengers, double loadCapacity) {
		setName(name);
		setSize(size);
		setWeight(weight);
		setPassengers(passengers);
		setLoadCapacity(loadCapacity);
	}
	
	public String toString() {
		return "Info about plain: \nname: " + getName() 
				+ "\nsize: " + getSize() 
				+ "\nweight: " + getWeight() 
				+ "\npassengers: " + getPassengers() 
				+ "\nload capacity: " + getLoadCapacity()
				+ "\n";	
	}

	public static void printStaticSum() {
		System.out.print("Load capacity of all plains are " + totalLoadCapacity + " tons\n");
	}
	
	public void printSum() {
		System.out.println("Load capacity of the plain " + getName() + " is " + getLoadCapacity() + " tons\n");
	}
	
	public void resetValues(String name, double size, double weight, int passengers, double loadCapacity) {
		setName(name);
		setWeight(weight);
		setSize(size);
		setPassengers(passengers);
		setLoadCapacity(loadCapacity);
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}
	
	public double getLoadCapacity() {
		return loadCapacity;
	}
	
	public void setLoadCapacity(double loadCapacity) {
		totalLoadCapacity -= this.loadCapacity;
		totalLoadCapacity += loadCapacity;
		this.loadCapacity = loadCapacity;
	}
}
