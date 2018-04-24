from CompanyManager import CompanyManager
from Plane import Civil, Military


class View:
    def __init__(self):
        self.menu = {"1": "  1 - Create Manager",
                     "2": "  2 - Print List Of Planes",
                     "3": "  3 - Sort By Flight Range",
                     "4": "  4 - Find Plane",
                     "5": "  5 - Total Capacity",
                     "6": "  6 - Total Load Capacity",
                     "E": "  E - exit"}

    def output_menu(self):
        print('\nMENU:\n')
        for key, value in self.menu.items():
            print(value)

    @staticmethod
    def manager(number):
        plane_list = [Civil("Gulfstream", 300, 3, 100, 400),
                      Military("Su", 200, 1, 1000, 150),
                      Civil("Airbus", 500, 5, 1000, 750)]
        company_manager = CompanyManager(plane_list)

        if number == "1":
            print("Successfully!!!")
        elif number == "2":
            for plane in company_manager.plane_list:
                print(plane.to_string())
        elif number == "3":
            comparison = input("How do you want to sort planes? (increase/decrease) ")
            company_manager.sort_by_flight_range(comparison)
            for plane in company_manager.plane_list:
                print("Flight range of the plain " + plane.name + " is " + str(plane.flight_range))
        elif number == "4":
            fuel_consumption = input("Which fuel consumption you find? ")
            result = company_manager.search_fuel_consumption(fuel_consumption)
            for plane in result:
                print("Fuel consumption of the plane " + plane.name + " is " + str(plane.fuel_consumption))
            else:
                print("There is no plane with this fuel consumption")
        elif number == "5":
            print(company_manager.total_capacity())
        elif number == "6":
            print(company_manager.total_load_capacity())
        elif number == "e":
            exit(0)
        else:
            print("Error! Menu has not this point")

    @staticmethod
    def show():
        num = "0"
        view = View()
        while num != "e":
            view.output_menu()
            num = input("Please, select menu point: ").lower()
            View.manager(num)
            num = input("\n  M - return menu\n  E - exit\n").lower()


if __name__ == "__main__":
    View.show()
