#include <iostream>

using namespace::std;

#define MAX 100001

int n;
int cnt;

int iN[MAX];
int posT[MAX];
int indeX[MAX];

int result[MAX];

void check(int p1, int p2, int i1, int i2){
    
    if(p1>p2 || i1>i2){
        return;
    }
    
    int root = indeX[posT[p2]];
    int left = root - i1;
    
    cout << iN[root] << ' ';
    
    check(p1, p1+left-1, i1, root-1);
    check(p1+left, p2-1, root+1, i2);
    
}

int main(){
    scanf("%d", &n);
    
    for(int i=1; i<=n; i++){
        scanf("%d", &iN[i]);
        indeX[iN[i]] = i;
    }
    
    for(int i=1; i<=n; i++){
        scanf("%d", &posT[i]);
    }
    
    check(1, n, 1, n);
    
    printf("\n");
}
