#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>
#include <list>
#include <sstream>
#include <cmath>

#define MAX 15

using namespace std;

int N, M;
int map[MAX][MAX];
int area[MAX][MAX];
int root[MAX];

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};
int separator = 1;
int ans = 0;

priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<>> q;

void findBridge(int y, int x, int dir, int cur_separator){
    int ty = y;
    int tx = x;
    int cost = 0;
    
    while(ty >= 1 && tx >= 1 && ty <= N && tx <= M){
        ty += ud[dir];
        tx += lr[dir];
        cost++;
        
        if(area[ty][tx] != 0){
            if(cost-1 > 1 && area[ty][tx] != cur_separator){
                q.push({cost-1, {cur_separator, area[ty][tx]}});
            }
            break;
        }
    }
}

void findCase(int y, int x){
    for(int i=0; i<4; i++){
        int ny = y + ud[i];
        int nx = x + lr[i];
        
        if(ny < 1 || nx < 1 || ny > N || nx > M) continue;
        
        if(map[ny][nx] == 0){
            findBridge(y, x, i, area[y][x]);
        }
    }
}

///////////////////////

int findRoot(int a){
    if(root[a] == a){
        return a;
    }
    
    return root[a] = findRoot(root[a]);
}

void connect(int a, int b){
    a = findRoot(a);
    b = findRoot(b);
    
    if(a == b) return;
    root[b] = a;
}

bool isConnected(int a, int b){
    a = findRoot(a);
    b = findRoot(b);
    
    if(a == b) return true;
    
    return false;
}

///////////////////////

void dfs(int y, int x){
    
    for(int i=0; i<4; i++){
        int ny = y + ud[i];
        int nx = x + lr[i];
        
        if(ny < 1 || nx < 1 || ny > N || nx > M) continue;
        
        if(map[ny][nx] == 1 && area[ny][nx] == 0){
            area[ny][nx] = separator;
            dfs(ny, nx);
        }
    }
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N >> M;
    
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=M; j++){
            cin >> map[i][j];
        }
    }
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=M; j++){
            if(map[i][j] == 1 && area[i][j] == 0){
                area[i][j] = separator;
                dfs(i, j);
                separator++;
            }
        }
    }
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=M; j++){
            if(area[i][j] != 0){
                findCase(i, j);
            }
        }
    }
    
    for(int i=1; i<separator; i++){
        root[i] = i;
    }
    
    while(!q.empty()){
        int cost = q.top().first;
        int area1 = q.top().second.first;
        int area2 = q.top().second.second;
        q.pop();
        
        if(!isConnected(area1, area2)){
            connect(area1, area2);
            ans += cost;
        }
    }
    
    bool isConnect = true;
    
    for(int i=1; i<separator; i++){
        if(!isConnected(1, i)){
            isConnect = false;
        }
    }
    
    if(isConnect){
        cout << ans << '\n';
    }
    else{
        cout << -1 << '\n';
    }
   
}
