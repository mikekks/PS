#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>
#include <vector>
#include <algorithm>
#include <cstring>
#include <queue>
#include <stack>

#define MAX 130
#define INF 987654321

using namespace std;

// 18 : 24 - 18 : 43
// 22 : 12 -

int map[MAX][MAX];
int cost_map[MAX][MAX];
int N, M, T;
int ans = INF;
int cnt = 0;

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};

void dijk(){
    priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<>> q;
    q.push({map[0][0], {0, 0}});
    cost_map[0][0] = map[0][0];
    
    while (!q.empty()) {
        int cost = q.top().first;
        int cy = q.top().second.first;
        int cx = q.top().second.second;
        q.pop();
        
        if(cy == N-1 && cx == N-1){
            ans = cost < ans ? cost : ans;
            continue;
        }
        
        if(cost > cost_map[cy][cx]) continue;
        
        for(int i=0; i<4; i++){
            int ny = cy + ud[i];
            int nx = cx + lr[i];
            
            if(ny < 0 || nx < 0 || ny > N-1 || nx > N-1) continue;
            
            if(cost + map[ny][nx] < cost_map[ny][nx]){
                q.push({cost + map[ny][nx], {ny, nx}});
                cost_map[ny][nx] = cost + map[ny][nx];
            }
        }
    }
    
}

int main(void)
{
    int cnt = 1;
    while (true) {
        
        memset(map, '\0', sizeof(map));
        ans = INF;
        
        cin >> N;
        
        if(N == 0)
            break;
        
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                cin >> map[i][j];
                cost_map[i][j] = INF;
            }
        }
        
        dijk();
        
        cout << "Problem " << cnt++ << ": " << ans << '\n';
        
    }
    
    return 0;
}
