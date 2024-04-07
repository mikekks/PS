#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <cmath>
#include <algorithm>
#include <cstring>

#define MAX 1005
#define INF 987654321

using namespace std;

int N;
int cost[MAX][3];

int dp[MAX][3];

int main(){
    cin >> N;
    
    for(int i=1; i<=N; i++){
        int r,g,b;
        cin >> r >> g >> b;
        
        cost[i][0] = r;
        cost[i][1] = g;
        cost[i][2] = b;
    }
    
    int ans = INF;
    
    for(int start=0; start<=2; start++){
        memset(dp, '\0', sizeof(dp));
        
        for(int cur=0; cur<=2; cur++){
            if(cur != start){
                dp[1][cur] = INF;
            }
            else{
                dp[1][cur] = cost[1][cur];
            }
        }
        
        for(int i=2; i<=N; i++){
            dp[i][0] = min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
            dp[i][1] = min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
            dp[i][2] = min(dp[i-1][1], dp[i-1][0]) + cost[i][2];
        }
        
        for(int end=0; end<=2; end++){
            if(end != start){
                ans = min(dp[N][end], ans);
            }
        }
    }
 
    
    cout << ans << '\n';
}
