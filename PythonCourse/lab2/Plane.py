class Plane:
    def __init__(self, name = "", capacity = 0, load_capacity = 0, fuel_consumption = 0, flight_range = 0):
        self.name = name
        self.capacity = capacity
        self.load_capacity = load_capacity
        self.fuel_consumption = fuel_consumption
        self.flight_range = flight_range

    def to_string(self):
        return "Name: " + self.name + ", capacity: " + str(self.capacity) + ", load_capacity: " + \
               str(self.load_capacity) + ", fuel_consumption: " + str(self.fuel_consumption) + ", flight_range: " + \
               str(self.flight_range)


class Civil(Plane):
    def __init__(self, name = "", capacity = 0, load_capacity = 0, fuel_consumption = 0,
                 flight_range = 0, plane_type = "Civil"):
        self.plane_type = plane_type
        Plane.__init__(self, name, capacity, load_capacity, fuel_consumption, flight_range)

    def to_string(self):
        return Plane.to_string(self) + ", plane type: " + self.plane_type


class Military(Plane):
    def __init__(self, name = "", capacity = 0, load_capacity = 0, fuel_consumption = 0,
                 flight_range = 0, plane_type = "Military"):
        Plane.__init__(self, name, capacity, load_capacity, fuel_consumption, flight_range)
        self.plane_type = plane_type

    def to_string(self):
        return Plane.to_string(self) + ", plane type: " + self.plane_type
