#include <math.h>
#include <stdio.h>

int main(void)
{
    double BeginOfRange, EndOfRange, step, result, error;
    printf ("State begin of range: ");
    scanf ("%lf", &BeginOfRange);
    printf ("State end of range: ");
    scanf ("%lf", &EndOfRange);
    printf ("State step: ");
    scanf ("%lf", &step);
    printf ("State error(pohubky): ");
    scanf ("%lf", &error);
    double x, sum;
    int k;
    for (x=BeginOfRange; x >= BeginOfRange && x <= EndOfRange; x=x+step)
    {
        k=0;
        sum=0;
        do
        {
            result = x/(pow(2*k+1,3))*sin(2*k+1);
            sum+=result;
            k++;
        }
        while (fabs(result)>error);
        printf ("x=%lf  sum=%lf  result=%lf\n", x, sum, result);
    }
}
