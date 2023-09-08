#include <iostream>
#include <vector>

using namespace::std;

int b[501][501];
int dp[501][501];
int N;

int Max(int a, int b){
    return a>b ? a : b;
}

int main(){
    scanf("%d", &N);
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=i; j++){
            cin>>b[i][j];
        }
    }
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=i; j++){
            dp[i][j] = Max(dp[i-1][j], dp[i-1][j-1]) + b[i][j];
        }
    }
    int r = 0;
    for(int i=1; i<=N; i++){
        if(r<dp[N][i]){
            r = dp[N][i];
        }
    }
    
    printf("%d\n", r);
}
