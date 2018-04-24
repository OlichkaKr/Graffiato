from Plane import Plane


class CompanyManager:
    __total_capacity, __total_load_capacity = 0, 0

    def __init__(self, plane_list=None):
        self.plane_list = plane_list

    def search_fuel_consumption(self, fuel_consumption):
        result = []
        for plane in self.plane_list:
            if plane.fuel_consumption == int(fuel_consumption):
                result.append(plane)
        return result

    def sort_by_flight_range(self, comparison):
        if comparison.lower() == 'increase':
            self.plane_list = sorted(self.plane_list, key=lambda plane: plane.flight_range)
        else:
            self.plane_list = sorted(self.plane_list, key=lambda plane: plane.flight_range, reverse=True)

    def total_capacity(self):
        for plane in self.plane_list:
            self.__total_capacity += plane.capacity
        return self.__total_capacity

    def total_load_capacity(self):
        for plane in self.plane_list:
            self.__total_load_capacity += plane.load_capacity
        return self.__total_load_capacity
