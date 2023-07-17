

#include <iostream>
#include <algorithm>
#include <cstring>
#include <queue>
#include <vector>

#define MAX 100001
#define INF 987654321

using namespace std;

long long A, B;
long long dp[55];

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> A >> B;
    
    dp[0] = 1;
    long long t1,t2;
    
    for(long long i = 1; i<55; i++){
        dp[i] = dp[i-1]*2 + (1LL << i);
    }
    
    long long pos = A-1;
    t1 = pos & 1;
    
    for(long long i = 54; i>0; i--){
        if(pos & (1LL << i)){
            t1 += dp[i-1] + (pos-(1LL << i) + 1);
            pos -= (1LL << i);
        }
        
    }
    
    pos = B;
    t2 = pos & 1;
    
    for(long long i = 54; i>0; i--){
        if(pos & (1LL << i)){
            t2 += dp[i-1] + (pos-(1LL << i) + 1);
            pos -= (1LL << i);
        }
        
    }
    
    cout << t2-t1 << '\n';
    
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}

