#include <iostream>
#include <algorithm>
#include <string>

using namespace::std;
#define MAX 101

int N, K;
int dp[MAX][100001];
int w[MAX];
int v[MAX];

int main(){
    scanf("%d %d", &N, &K);
    
    for(int i=1; i<=N; i++){
        scanf("%d %d", &w[i], &v[i]);
    }
    
    for(int i=1; i<=N; i++){
       for(int j=1; j<=K; j++){
            if(j>=w[i]){
                dp[i][j] = max(dp[i-1][j], dp[i-1][j-w[i]]+v[i]);
            }else{
                dp[i][j] = dp[i-1][j];
            }
        }
    }
    printf("%d\n", dp[N][K]);
}
