#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>

#define MAX 101
#define INF 987654321

using namespace::std;
int N, M;
int Usage[101];
int Costs[101];
int dp[101][10001];

int main(){
    
    scanf("%d %d", &N, &M);
    int sum = 0;
    
    for(int i=1; i<=N; i++){
        scanf("%d", &Usage[i]);
    }
    
    for(int i=1; i<=N; i++){
        scanf("%d", &Costs[i]);
        sum += Costs[i];
    }
    
    for(int i=1; i<=N; i++){
        for(int j=0; j<=sum; j++){
            if(j-Costs[i] >= 0)
                dp[i][j] = max(dp[i-1][j], dp[i-1][j-Costs[i]]+Usage[i]);
            else
                dp[i][j] = max(dp[i][j], dp[i-1][j]);
        }
    }
    
    for(int i=0; i<=sum; i++){
        if(dp[N][i] >= M){
            printf("%d\n", i);
            break;
        }
    }
    
    
    return 0;
}
