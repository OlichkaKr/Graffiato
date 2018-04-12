class Plane:
    __name = "no name"
    __size, __weight, __passengers, __load_capacity = 0, 0, 0, 0
    __total_load_capacity = 0

    def __init__(self, name="no name", size=0, weight=0, passengers=0, load_capacity=0):
        self.__name = name
        self.__size = size
        self.__weight = weight
        self.__passengers = passengers
        self.__load_capacity = load_capacity
        Plane.__total_load_capacity += self.__load_capacity

    def to_string(self):
        print("Name: " + self.__name + ", size: " + str(self.__size)
              + ", weight: " + str(self.__weight) + ", passengers: "
              + str(self.__passengers) + ", load capacity: " + str(self.__load_capacity))

    def print_sum(self):
        print("The load capacity of the plane " + self.__name + " is " + str(self.__load_capacity))

    @staticmethod
    def print_static_sum():
        print("The total load capacity of all planes is " + str(Plane.__total_load_capacity))


if __name__ == "__main__":
    gulfstream = Plane()
    boeing = Plane("Boeing", 200, 1, 150)
    airbus = Plane("Airbus", 500, 5, 1000, 750)

    gulfstream.to_string()
    boeing.to_string()
    airbus.to_string()

    Plane.print_static_sum()
    boeing.print_sum()
    airbus.print_sum()
