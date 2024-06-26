#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 5005
#define MOD 1000000

using namespace std;
string N;
long long dp[MAX];
long long input[MAX];

int main() {
    cin >> N;
    
    for(int i=0; i<N.size(); i++){
        input[i] = N[i] - '0';
    }
    
    if(input[0] >= 1 && input[0] <= 9){
        dp[0] = 1;
    }
    
    if(input[1] >= 1 && input[1] <= 9){
        dp[1] = dp[0];
    }
    
    if(input[0] == 1){
        dp[1] += 1;
    }
    else if(input[0] == 2){
        if(input[1] <= 6){
            dp[1] += 1;
        }
    }
    
    for(int i=2; i<N.size(); i++){
        
        if(input[i] != 0){
            dp[i] = (dp[i] + dp[i-1]) % MOD;
        }
        
        long long tmp = input[i-1] * 10 + input[i];
        
        if(10 <= tmp && tmp <= 26){
            dp[i] = (dp[i] + dp[i-2]) % MOD;
        }
    }
    
    cout << dp[N.size()-1] << '\n';
}
