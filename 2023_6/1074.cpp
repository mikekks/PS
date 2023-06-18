#include <iostream>
#include <algorithm>
#include <cstring>
#include <cmath>

#define INF INT_MAX
using namespace std;

int N,r,c;
int cnt;

void div(int n, int y, int x, int i){
    
    if(n == 1){
        if(y == r && c == x){
            printf("%d\n", i);
            return;
        }
        
    }
    
    int tn = n/2;
    
    if(r<y+n/2 && c<x+n/2){
        div(n/2, y, x, i);
    }
    else if(r<y+n/2 && c>=x+n/2){
        div(n/2, y, x+n/2, i+tn*tn);
    }
    else if(r>=y+n/2 && c<x+n/2){
        div(n/2, y+n/2, x, i+2*tn*tn);
    }
    else if(r>=y+n/2 && c>=x+n/2){
        div(n/2, y+n/2, x+n/2, i+3*tn*tn);
    }


    
}

int main(void)
{
    scanf("%d %d %d", &N, &r, &c);
    int tn = pow(2, N);
    div(tn, 0, 0, 0);
}
