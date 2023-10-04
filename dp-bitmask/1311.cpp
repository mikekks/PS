#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>
#include <map>


#define MAX 1<<19
#define INF 987654321
#define MOD 9901

using namespace std;

typedef long long ll;

int T;
int N, M;
int dp[21][1 << 21];
int Map[21][21];

ll ans;




int dfs(int idx, int visited){
    if(visited == (1<<N) -1) return 0;
    if(dp[idx][visited] != 0) return dp[idx][visited];
    
    dp[idx][visited] = INF;
    
    for(int i=0; i<N; i++){
        if(visited & (1<<i)) continue;
        
        int tmp = dfs(idx+1, visited | (1<<i)) + Map[idx][i+1];
        dp[idx][visited] = min(tmp, dp[idx][visited]);
    }
    return dp[idx][visited];
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N;
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            cin >> Map[i][j];
        }
    }
    
    cout << dfs(1, 0) << '\n';
    
    
    
    
    return 0;
}
