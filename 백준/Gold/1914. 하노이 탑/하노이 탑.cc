#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>

using namespace std;

void HanoiTower(int num, int from, int by, int to)
{
    if (num == 1) printf("%d %d\n", from, to);
    else
    {
        HanoiTower(num - 1, from, to, by);
        printf("%d %d \n", from, to);
        HanoiTower(num - 1, by, from, to);
    }
}

int main(void)
{
    int N;
    cin >> N;

    string a = to_string(pow(2, N));
    
    int x = a.find('.');
    a = a.substr(0, x);
    a[a.length() - 1] -= 1;

    cout << a << endl;

    if(N <= 20)
        HanoiTower(N, 1, 2, 3);

    return 0;
}
