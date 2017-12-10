#include <iostream>
#include <iomanip>
#include <cmath>

const int kSizeOfMatrix = 5;

using namespace std;

class Matrix {
   private:
        double array[kSizeOfMatrix][kSizeOfMatrix];
   public:
        void InputMatrixFromConsole();
        void OutputMatrixToConsole();
        void BubbleSortOfMatrix();
        void ExtraTasks();
};

int main(void) {
    Matrix Array;
    cout << "Enter the elements of matrix M: " << endl;
    Array.InputMatrixFromConsole();
    Array.OutputMatrixToConsole();
    Array.BubbleSortOfMatrix();
    Array.ExtraTasks();
}

void Matrix::InputMatrixFromConsole() {
    for (int i = 0; i < kSizeOfMatrix; i++) {
        for (int j = 0; j < kSizeOfMatrix; j++) {
            cout << "M[" << i + 1 << "][" << j + 1 << "] = ";
            cin >> array[i][j];
        }
    }
}

void Matrix::OutputMatrixToConsole() {
    cout << endl << "Old matrix:" << endl;
    for (int i = 0; i < kSizeOfMatrix; i++) {
        for (int j = 0; j < kSizeOfMatrix; j++) {
            cout << setw(7)<< array[i][j];
        }
        cout << endl;
    }
}

void Matrix::BubbleSortOfMatrix() {
    for (int j = 0; j < kSizeOfMatrix; j++) {
        for (int k = kSizeOfMatrix - 1; k > 0; k--) {
            for (int i = 0; i < k; i++) {
                if (array[i][j] > array[i+1][j]) {
                    int temporary_variable = array[i][j];
                    array[i][j] = array[i+1][j];
                    array[i+1][j] = temporary_variable;
                }
            }
        }
    }
    cout << "\n" << "New matrix:" << endl;
    for (int i = 0; i < kSizeOfMatrix; i++) {
        for (int j = 0; j < kSizeOfMatrix; j++) {
            cout << setw(7) << array[i][j];
        }
        cout << endl;
    }
}

void Matrix::ExtraTasks() {
    double product, sum, geometry_mean, arithmetic_mean;
    cout << endl << "Geometry mean:" << endl;
    sum = 0;
    double *pgeometry_mean = &geometry_mean;

    //Search a geometry mean of the elements in each string of matrix
    for (int i = 0; i < kSizeOfMatrix; i++) {
        product = 1;
        for (int j = 0; j < kSizeOfMatrix; j++)
            product = product * array[i][j];

        if (product < 0) {
            double positive_geometry_mean = pow(fabs(product), 1/5.);
            *pgeometry_mean = positive_geometry_mean * (-1);
            cout << setw(5) << *pgeometry_mean;
        } else {
            *pgeometry_mean = pow(product, 1/5.);
            cout << setprecision(3) << setw(7) << *pgeometry_mean << "    ";
        }
        //Search the sum of the geometry means
        sum = sum + *pgeometry_mean;
    }
    arithmetic_mean = sum / 4;
    cout << endl << endl << "Sum:  " << arithmetic_mean << endl;
}

