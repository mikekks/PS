#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>
#include <list>
#include <sstream>
#include <cmath>

using namespace std;

#define MAX 55

char map[MAX][MAX];
int water[MAX][MAX];
bool isVisit[MAX][MAX];

int R, C;
int sy, sx, ey, ex;
int ans = 987654321;

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};

struct thing{
    int y;
    int x;
    int cost;
};

queue<pair<int, pair<int, int>>> q;
queue<pair<int, int>> water_q;

void bfs_water(){
    
    while (!water_q.empty()) {
        int size = (int) water_q.size();
        
        for(int k=0; k<size; k++){
            int cy = water_q.front().first;
            int cx = water_q.front().second;
            water_q.pop();
            
            for(int i=0; i<4; i++){
                int ty = cy+ud[i];
                int tx = cx+lr[i];
                int tc = water[cy][cx]+1;
                
                if(ty < 1 || tx < 1 || ty > R || tx > C) continue;
                
                if(map[ty][tx] == '.'){
                    if(water[ty][tx] > tc){
                        water[ty][tx] = tc;
                        water_q.push({ty, tx});
                    }
                }
                
            }
        }
        
    }
}

bool bfs(){
    
    q.push({0, {sy, sx}});
    bool result = false;
    isVisit[sy][sx] = true;
    
    while (!q.empty()) {
        int cy = q.front().second.first;
        int cx = q.front().second.second;
        int cost = q.front().first;
        q.pop();
        
        if(cy == ey && cx == ex){
            ans = cost < ans ? cost : ans;
            return true;
        }
        
        for(int i=0; i<4; i++){
            int ty = cy+ud[i];
            int tx = cx+lr[i];
            int tc = cost+1;
            
            if(ty < 1 || tx < 1 || ty > R || tx > C) continue;
            
            if(isVisit[ty][tx]) continue;
            
            if(water[ty][tx] > tc){
                if(map[ty][tx] == '.' || map[ty][tx] == 'D'){
                    isVisit[ty][tx] = true;
                    q.push({tc, {ty, tx}});
                }
            }
            
        }
    }
    
    
    return result;
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> R >> C;
    
    for(int i=1; i<=R; i++){
        for(int j=1; j<=C; j++){
            water[i][j] = 987654321;
        }
    }
    
    
    for(int i=1; i<=R; i++){
        for(int j=1; j<=C; j++){
            cin >> map[i][j];
            if(map[i][j] == 'D'){
                ey = i;
                ex = j;
            }
            else if(map[i][j] == 'S'){
                sy = i;
                sx = j;
            }
            else if(map[i][j] == '*'){
                water_q.push({i, j});
                water[i][j] = 0;
            }
        }
    }
    
    
    bfs_water();
    
    if(bfs()){
        printf("%d\n", ans);
    }
    else{
        cout << "KAKTUS" << '\n';
    }
    
}
