#include <iostream>
#include <iomanip>
#include <cmath>

const int SizeOfMatrix = 5;

using namespace std;

class Matrix
{
   private:
        double Array[SizeOfMatrix][SizeOfMatrix];
   public:
        void InputMatrixFromConsole();
        void OutputMatrixToConsole();
        void BubbleSortOfMatrix();
        void ExtraTasks();
};

int main(void)
{
    Matrix Array;
    Array.InputMatrixFromConsole();
    Array.OutputMatrixToConsole();
    Array.BubbleSortOfMatrix();
    Array.ExtraTasks();
}

void Matrix::InputMatrixFromConsole()
{
    for (int i = 0; i < SizeOfMatrix; i++)
    {
        for (int j = 0; j < SizeOfMatrix; j++)
        {
            cout << "M[" << i + 1 << "][" << j + 1 << "] = ";
            cin >> Array[i][j];
        }
    }
}

void Matrix::OutputMatrixToConsole()
{
    cout << endl << "Old matrix:" << endl;
    for (int i = 0; i < SizeOfMatrix; i++)
    {
        for (int j = 0; j < SizeOfMatrix; j++)
        {
            cout << setw(7)<< Array[i][j];
        }
        cout << endl;
    }
}

void Matrix::BubbleSortOfMatrix()
{
    for (int j = 0; j < SizeOfMatrix; j++)
    {
        for (int k = SizeOfMatrix - 1; k > 0; k--)
        {
            for (int i = 0; i < k; i++)
            {
                if (Array[i][j] > Array[i+1][j])
                {
                    int temporaryVariable = Array[i][j];
                    Array[i][j] = Array[i+1][j];
                    Array[i+1][j] = temporaryVariable;
                }
            }
        }
    }
    cout << "\n" << "New matrix:" << endl;
    for (int i = 0; i < SizeOfMatrix; i++)
    {
        for (int j = 0; j < SizeOfMatrix; j++)
        {
            cout << setw(7) << Array[i][j];
        }
        cout << endl;
    }
}

void Matrix::ExtraTasks()
{
    double product, sum, GeometryMean, ArithmeticMean;
    cout << endl << "Geometry mean:" << endl;
    sum = 0;
    double *pGeometryMean = &GeometryMean;

    //Search a geometry mean of the elements in each string of matrix
    for (int i = 0; i < SizeOfMatrix; i++)
    {
        product = 1;
        for (int j = 0; j < SizeOfMatrix; j++)
            product = product * Array[i][j];

        if (product < 0)
        {
            double PositiveGeometryMean = pow(fabs(product), 1/5.);
            *pGeometryMean = PositiveGeometryMean * (-1);
            cout << setw(5) << *pGeometryMean;
        }
        else
        {
            *pGeometryMean = pow(product, 1/5.);
            cout << setprecision(3) << setw(7) << *pGeometryMean << "    ";
        }
        //Search sum of the geometry means
        sum = sum + *pGeometryMean;
    }
    ArithmeticMean = sum / 4;
    cout << endl << endl << "Sum:  " << ArithmeticMean << endl;
}

