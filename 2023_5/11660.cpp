#include <iostream>
#include <vector>

#define MAX 1025

using namespace::std;

int N,M;
int b[MAX][MAX];
int dp[MAX][MAX];

int main(){
    scanf("%d %d", &N, &M);
    for(int i=1; i<=N; i++){
       for(int j=1; j<=N; j++){
            scanf("%d", &b[i][j]);
        }
    }
    
    for(int i=1; i<=N; i++){
       for(int j=1; j<=N; j++){
           dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1] + b[i][j];
        }
    }
    
    for(int i=0; i<M; i++){
        int x1,x2,y1,y2;
        int result = 0;
        scanf("%d %d %d %d", &x1, &y1, &x2, &y2);
        result = dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1];
        printf("%d\n", result);
    }
    
}
