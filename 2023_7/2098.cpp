#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>

#define MAX 16
#define INF 987654321

using namespace::std;
int N;
int W[MAX][MAX];
int dp[MAX][1<<MAX];
int result;
int totalBit;

int travel(int cur, int curBit){
    
    if(curBit == totalBit){ // 모두 다 순회한 경우
        if(W[cur][0] == 0) return INF;
        else return W[cur][0];
    }
    
    if(dp[cur][curBit] != -1) // 이미 방문
        return dp[cur][curBit];
    
    dp[cur][curBit] = INF;
    
    for(int i=0; i<N; i++){
        if(W[cur][i] == 0) continue;  // 가는 방법이 없는 경우
        if((curBit & (1<<i)) == (1<<i)) continue; // 이미 방문한 경우
        
        dp[cur][curBit] = min(dp[cur][curBit], W[cur][i] + travel(i, curBit | 1<<i));
    }
    return dp[cur][curBit];
}

int main(){
    
    scanf("%d", &N);
    
    for(int i=0; i<N; i++)
        for(int j=0; j<N; j++)
            scanf("%d", &W[i][j]);
    
    totalBit = (1<<N)-1;  // 모두 다 순회한 경우
    memset(dp, -1, sizeof(dp));  // 방문 표시
    
    result = travel(0, 1);
    printf("%d\n", result);
    return 0;
}
