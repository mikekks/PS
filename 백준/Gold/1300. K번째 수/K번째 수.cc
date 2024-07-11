#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

using namespace std;

long long N, K;

int main() {
    cin >> N >> K;
    
    
    long long l = 1;
    long long r = N * N;
    
    K = 1000000000 < K ? 1000000000 : K;
    
    while(l <= r){
        long long m = (l+r)/2;
        long long cnt = 0;
        
        for(int i=1; i<=N; i++){
            cnt += N < (m/i) ? N : (m/i);
        }
        
        if(cnt < K)
            l = m+1;
        else
            r = m-1;
        
    }
    
    cout << l << '\n';
    
}
