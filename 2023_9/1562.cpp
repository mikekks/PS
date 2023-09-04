#include <iostream>

#define MOD 1000000000

using namespace std;

int N;
int dp[101][10][1<<10]; // n = 100, 0~9
int ans;
int check;

int dfs(int step, int cur, int bit){
    
    if(dp[step][cur][bit])
        return dp[step][cur][bit];
    if(step == N)
        return bit == check ? 1 : 0;
    
    int tmp = 0;
    if(cur+1 < 10)
        tmp += dfs(step+1, cur+1, bit | 1 << (cur+1));
    if(cur-1 >= 0)
        tmp += dfs(step+1, cur-1, bit | 1 << (cur-1));
    
    tmp %= MOD;
    
    return dp[step][cur][bit] = tmp;
}

int main(){
    scanf("%d", &N);
    
    // dp
    check = (1 << 10) - 1;
    
    for(int i=1; i<10; i++){
        ans += dfs(1, i, 1 << i);
        ans %= MOD;
    }
    
    printf("%d\n", ans);
    return 0;
}
