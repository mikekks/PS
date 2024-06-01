#include <iostream>
#include <string>
#include <sstream>
#include <cmath>
#include <vector>
#include <algorithm>
#include <queue>
#include <cstring>

using namespace std;

#define MAX 1005


int R, C;
char map[MAX][MAX];
bool isVisit[MAX][MAX];
bool fVisit[MAX][MAX];
int fMap[MAX][MAX];
int ans = 987654321;

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};
int jy,jx;

queue<pair<int, pair<int, int>>> q;

void processFire(){
    
    while (!q.empty()) {
        int cy = q.front().second.first;
        int cx = q.front().second.second;
        int time = q.front().first;
        q.pop();
        
        for(int i=0; i<4; i++){
            int ty = cy+ud[i];
            int tx = cx+lr[i];
            
            if(ty < 1 || tx < 1 || ty > R || tx > C) continue;
            if(map[ty][tx] == '#') continue;
            if(fMap[ty][tx] != 987654321) continue;
            
            fMap[ty][tx] = time+1;
            q.push({time+1, {ty, tx}});
        }
    }
}

void bfs(int y, int x){
    priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<>> q;
    
    q.push({1, {y, x}});
    isVisit[y][x] = true;
    
    while (!q.empty()) {
        int cy = q.top().second.first;
        int cx = q.top().second.second;
        int time = q.top().first;
        q.pop();
        
        if(cy == 1 || cx == 1 || cy == R || cx == C){
            ans = time < ans ? time : ans;
            return;
        }
        
        for(int i=0; i<4; i++){
            int ty = cy+ud[i];
            int tx = cx+lr[i];
            
            if(ty < 1 || tx < 1 || ty > R || tx > C) continue;
            
            if(isVisit[ty][tx]) continue;
            
            if(map[ty][tx] == '#') continue;
            
            if(time+1 < fMap[ty][tx] && map[ty][tx] == '.'){
                q.push({time+1, {ty, tx}});
                isVisit[ty][tx] = true;
            }
        }
    }
}

int main() {
    
    cin >> R >> C;
    
    for(int i=1; i<=R; i++){
        for(int j=1; j<=C; j++){
            cin >> map[i][j];
            if(map[i][j] == 'J'){
                jy = i;
                jx = j;
            }
            else if(map[i][j] == 'F'){
                q.push({1, {i, j}});
                fMap[i][j] = 1;
            }
            fMap[i][j] = 987654321;
        }
    }
    
    processFire();
    
    bfs(jy, jx);
    
    if(ans == 987654321){
        cout << "IMPOSSIBLE" << '\n';
        return 0;
    }
    
    cout << ans << '\n';
    
    return 0;
}


