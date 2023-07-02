#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>

#define MAX 1001

using namespace::std;

int T, N, K, W;
int D[MAX];
vector<int> Order[MAX];
int dpdany[MAX];
int total;
int dp[MAX];

int main(){
    
    scanf("%d", &T);
    
    for(int i=0; i<T; i++){
        
        queue<int> q;
        scanf("%d %d", &N, &K);
        
        for(int i=1; i<=N; i++){
            scanf("%d", &D[i]);
        }
        
        for(int i=1; i<=N; i++){
            Order[i].clear();
            dpdany[i] = 0;
        }
        
        for(int i=1; i<=K; i++){
            int X, Y;
            scanf("%d %d", &X, &Y);
            Order[X].push_back(Y);
            dpdany[Y]++;
        }
        
        scanf("%d", &W);
        
        for(int i=1; i<=N; i++){
            if(dpdany[i] == 0){
                q.push(i);
            }
            dp[i] = D[i];
        }
        
        while(!q.empty()){
            int cur = q.front();
            q.pop();
            
            for(int i=0; i<Order[cur].size(); i++){
                int next = Order[cur][i];
                dpdany[next]--;
                
                if(dp[cur] + D[next] > dp[next]){
                    dp[next] = dp[cur] + D[next];
                }
                
                if(dpdany[next] == 0){
                    q.push(next);
                }
            }
        }
        
        printf("%d\n", dp[W]);
        
    }
    
    return 0;
}
