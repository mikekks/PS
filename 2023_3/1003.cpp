#include <iostream>

using namespace::std;

int T;
int m[41][2];

void fibo(int n){
    if(m[n][0] != 0 || m[n][1] != 0){
        return;
    }
    
    if (n == 0) {
        m[n][0] += 1;
        return;
    } else if (n == 1) {
        m[n][1] += 1;
        return;
    } else {
        fibo(n-1);
        fibo(n-2);
        m[n][0] = m[n-1][0] + m[n-2][0];
        m[n][1] = m[n-1][1] + m[n-2][1];
        return;
    }
}

int main(){
    cin >> T;
    
    for(int i=0; i<T; i++){
        int tmp;
        cin >> tmp;
        fibo(tmp);
        printf("%d %d\n", m[tmp][0], m[tmp][1]);
    }
}
