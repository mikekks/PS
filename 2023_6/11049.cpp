#include <iostream>
#include <queue>
#include <climits>
#include <algorithm>

using namespace std;

int N;
int map[501][2];
int dp[501][501];

int main(){
    scanf("%d", &N);
    
    for(int i=1; i<=N; i++){
        int r,c;
        scanf("%d %d", &r, &c);
        map[i][0] = r;
        map[i][1] = c;
        
    }
    
    for(int i=1; i<=N; i++){
        for(int j=1; i+j<=N; j++){
            dp[j][i+j] = INT_MAX;
            for(int k=j; k<=i+j; k++){
                dp[j][i+j] = min(dp[j][i+j], dp[j][k] + dp[k+1][i+j] + map[j][0] * map[k][1] * map[i+j][1]);

            }
        }
    }
    
    cout << dp[1][N];
    return 0;
}
