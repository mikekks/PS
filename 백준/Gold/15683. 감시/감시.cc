#include <iostream>
#include <string>
#include <sstream>
#include <cmath>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

#define MAX 10

int map[MAX][MAX];
int N, M;
int ans = 987654321;
vector<pair<int, int>> cctv;

int ud[4] = {-1,0,1,0};
int lr[4] = {0,1,0,-1};

void select(int y, int x, int arrow){
    int cy = y;
    int cx = x;
    arrow %= 4;

    while (true) {
        if(map[cy][cx] == 6) break;
        
        if(cy < 1 || cx < 1 || cy > N || cx > M) break;
        
        if(map[cy][cx] == 0){
            map[cy][cx] = -1;
        }
        
        cy += ud[arrow];
        cx += lr[arrow];
    }
    
}

void dfs(int cur){
    
    if(cur == cctv.size()){
        int cnt = 0;
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                if(!map[i][j]) cnt++;
            }
        }
        ans = min(ans, cnt);
        return;
    }
    
    int cy = cctv[cur].first;
    int cx = cctv[cur].second;
    
    int tmp[MAX][MAX];
    
    for(int i=0; i<4; i++){
        for(int j=1; j<=N; j++)
            for(int k=1; k<=M; k++)
                tmp[j][k] = map[j][k];
        
        if(map[cy][cx] == 1){
            select(cy, cx, i);
        }
        else if(map[cy][cx] == 2){
            select(cy, cx, i);
            select(cy, cx, i+2);
        }
        else if(map[cy][cx] == 3){
            select(cy, cx, i);
            select(cy, cx, i+1);
        }
        else if(map[cy][cx] == 4){
            select(cy, cx, i);
            select(cy, cx, i+1);
            select(cy, cx, i+2);
        }
        else if(map[cy][cx] == 5){
            select(cy, cx, i);
            select(cy, cx, i+1);
            select(cy, cx, i+2);
            select(cy, cx, i+3);
        }
        
        dfs(cur+1);
        
        for(int j=1; j<=N; j++)
            for(int k=1; k<=M; k++)
                map[j][k] = tmp[j][k];

    }
    
}

int main() {

    cin >> N >> M;
    for(int i=1; i<=N; i++){
        for(int j=1; j<=M; j++){
            cin >> map[i][j];
            if(map[i][j] != 0 && map[i][j] != 6){
                cctv.push_back({i, j});
            }
        }
    }
    
    dfs(0);
    
    cout << ans << '\n';
    return 0;
}


