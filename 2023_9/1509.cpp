#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>

#define MAX 2505

using namespace std;

typedef long long ll;

string P;
int ans;

bool dp[MAX][MAX];
int arr[MAX];


void solve(){
   
    for(int i=0; i<P.size(); i++){
        dp[i][i] = true;
    }
    
    for(int i=0; i<P.size()-1; i++){
        if(P[i] == P[i+1]) dp[i][i+1] = true;
    }
    
    for(int cnt = 3; cnt <= P.size(); cnt++){
        for(int i=0; i+cnt-1< P.size(); i++){
            // i ~ i+cnt 확인
            int end = i + cnt - 1;
            if(P[i] == P[end] && dp[i+1][end-1] == true){
                dp[i][end] = true;
            }
        }
    }
    
    
    
    for(int end = 0; end < P.size(); end++){
        arr[end] = 987654321;
        for(int start = 0; start <= end; start++){
            if(dp[start][end] == true){
                arr[end] = min(arr[end], arr[start-1] + 1);
            }
        }
    }
    
    cout << arr[P.size()-1] << '\n';
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> P;
    
    solve();
        
    return 0;
}
