#include <cstring>
#include <vector>
#include <algorithm>
#include <queue>
#include <cmath>
#include <iostream>

using namespace std;

int Map[55][55];
int N, L, R;
int union_map[55][55];
bool isVisited[55][55];
bool isEnd;

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};


void bfs(int iy, int ix){
    
    queue<pair<int, int>> q;
    q.push({iy, ix});
    isVisited[iy][ix] = true;
    vector<pair<int, int>> targets;
    
    int total = 0;
    int div = 0;
    
    while(!q.empty()){
        int cy = q.front().first;
        int cx = q.front().second;
        q.pop();
        
        total += Map[cy][cx];
        div += 1;
        targets.push_back({cy, cx});
        
        
        for(int i=0; i<4; i++){
            int ty = cy + ud[i];
            int tx = cx + lr[i];
            
            if(ty >= N || tx >= N || ty < 0 || tx < 0){
                continue;
            }
            
            if(isVisited[ty][tx]) continue;
            
            int diff = abs(Map[cy][cx] - Map[ty][tx]);
            if(diff >= L && diff <= R){
                isVisited[ty][tx] = true;
                q.push({ty, tx});
            }
        }
    }
    
    int result = total / div;
    
    if(div != 1){
        isEnd = false;
    }
    
    for(int i=0; i<targets.size(); i++){
        int cy = targets[i].first;
        int cx = targets[i].second;
        
        union_map[cy][cx] = result;
    }
}

int main(){
    cin >> N >> L >> R;
    
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            cin >> Map[i][j];
        }
    }
    
    int ans = 0;
    
    while(true){
        isEnd = true;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(isVisited[i][j]){
                    continue;
                }
                bfs(i, j);
            }
        }
        
        if(isEnd){
            break;
        }
        else{
            ans++;
            memset(isVisited, '\0', sizeof(isVisited));
        }
        
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                Map[i][j] = union_map[i][j];
            }
        }
    }
    
    cout << ans << '\n';
    
}
