#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>
#include <vector>
#include <algorithm>
#include <cstring>
#include <queue>
#include <stack>

#define MAX 205
#define MOD 1000000000

using namespace std;

// 13: 00 ~


int N;
int dp[10][2];
long long ans;

int main(void)
{
    cin >> N;
    
    for(int i=1; i<10; i++){
        dp[i][0] = 1;
    }
    
    while(N > 1){
        dp[0][1] = dp[1][0];
        
        for(int i=1; i<9; i++){
            dp[i][1] = (dp[i-1][0] + dp[i+1][0]) % MOD;
        }
        dp[9][1] = dp[8][0];
        
        for (int i = 0; i < 10; i++)
            dp[i][0] = dp[i][1];

        N--;
    }
    
    for(int i=0; i<10; i++){
        ans = (ans + dp[i][0]) % MOD;
    }
    
    cout << ans << '\n';
    
    return 0;
}
