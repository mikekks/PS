#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 35

using namespace std;
int N;
int dp[MAX];

int main() {
    cin >> N;
    
    if(N%2 == 1){
        cout << 0 << '\n';
        return 0;
    }
    
    dp[0]=1;
    dp[1] = 0;
    dp[2] = 3;
    
    for(int i=4; i<=N; i++){
        dp[i] = dp[i-2]*3;
        
        for(int j=i-4; j>=0; j -= 2){
            dp[i] = dp[i] + (dp[j] * 2);
        }
    }
    
    cout << dp[N] << '\n';
}
