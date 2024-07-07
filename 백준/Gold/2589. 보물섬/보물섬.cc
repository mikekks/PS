#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 55

using namespace std;
int N, M;
int ans = 0;

char map[MAX][MAX];
bool isVisit[MAX][MAX];

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};

void bfs(int y, int x){
    for(int i=1; i<=N; i++){
        for(int j=1; j<=M; j++){
            isVisit[i][j] = false;
        }
    }
    
    priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<>> q;
    
    q.push({0, {y, x}});
    isVisit[y][x] = true;
    
    int cur_d = 0;
    
    while (!q.empty()) {
        int cy = q.top().second.first;
        int cx = q.top().second.second;
        int cost = q.top().first;
        q.pop();
        
        if(cost > cur_d){
            cur_d = cost;
        }
        
        for(int i=0; i<4; i++){
            int ty = cy+ud[i];
            int tx = cx+lr[i];
            
            if(ty < 1 || tx < 1 || ty > N || tx > M) continue;
            
            if(map[ty][tx] == 'L' && !isVisit[ty][tx]){
                q.push({cost+1, {ty, tx}});
                isVisit[ty][tx] = true;
            }
        }
        
    }
    
    if(cur_d > ans){
        ans = cur_d;
    }
    
}

int main() {
    cin >> N >> M;
    
    for(int i=1; i<=N; i++){
        string tmp;
        cin >> tmp;
        for(int j=1; j<=M; j++){
            map[i][j] = tmp[j-1];
        }
    }
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=M; j++){
            if(map[i][j] == 'L'){
                bfs(i, j);
            }
        }
    }
    
    
    cout << ans << '\n';
}
