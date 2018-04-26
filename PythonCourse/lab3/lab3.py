import timeit
import zipfile
import re


def search_in_log():
    count = 0
    z = zipfile.ZipFile('access_log_Jul95.zip')
    file = z.open('access_log_Jul95')
    for line in file:
        if re.search('205\.189\.154\.54.*01/Jul/1995.*GET.*\.txt.*200 ', str(line)):
            count += 1
    print(count)
    z.close()


if __name__ == "__main__":
    search_in_log()
