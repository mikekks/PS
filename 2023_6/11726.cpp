#include <iostream>
#include <algorithm>
#include <cstring>
#include <cmath>

#define INF INT_MAX
using namespace std;

int N;
int cnt;

int dp[1001];

int main(void)
{
    scanf("%d", &N);
    
    dp[0] = 0;
    dp[1] = 1;
    dp[2] = 2;
    
    for(int i=3; i<=N; i++){
        dp[i] = (dp[i-1]+dp[i-2]) % 10007;
    }
    
    printf("%d\n", dp[N]);
}
