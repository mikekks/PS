#include <iostream>
#include <algorithm>
#include <cstring>
#include <cmath>
#include <queue>
#include <climits>
#include <vector>

#define INF INT_MAX

using namespace std;

int N;
int dp[1001];

int main(void)
{
    scanf("%d", &N);
    
    dp[1] = 1;
    dp[2] = 3;
    
    for(int i=3; i<=N; i++){
        dp[i] += (dp[i-1] + 2*dp[i-2]) % 10007;
    }
    
    printf("%d\n", dp[N]);
        
}
