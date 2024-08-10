#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>
#include <vector>
#include <algorithm>
#include <cstring>
#include <queue>
#include <stack>

#define MAX 105

using namespace std;


int map[MAX][MAX];
int visited[MAX][MAX];
int N, M;
int ans = 0;
int cnt = 0;

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};

void bfs(){
    
    queue<pair<int, int>> q;
    
    visited[1][1] = true;
    q.push({1,1});
    
    while (!q.empty()) {
        int cy = q.front().first;
        int cx = q.front().second;
        q.pop();
        
        if(map[cy][cx] == 0){
            for(int i=0; i<4; i++){
                int ny = cy+ud[i];
                int nx = cx+lr[i];
                if(ny < 1 || nx < 1 || ny > N || nx > M) continue;
                if(visited[ny][nx]) continue;
                
                visited[ny][nx] = true;
                q.push({ny, nx});
            }
        }
        else{
            map[cy][cx] = 0;
        }
    }
}

int main(void)
{
    cin >> N >> M;
    
    for(int i=0; i<=N+1; i++){
        for(int j=0; j<=M+1; j++){
            map[i][j] = -1;
        }
    }
  
    for(int i=1; i<=N; i++){
        for(int j=1; j<=M; j++){
            cin >> map[i][j];
        }
    }
    
    int time = 0;
    int prev_cheese = 0;
    
    while(true){
        memset(visited, 0, sizeof(visited));
        cnt = 0;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                if(map[i][j] == 1) cnt++;
            }
        }
        
        if(cnt == 0){
            cout << time << '\n';
            cout << prev_cheese << '\n';
            break;
        }
        else{
            prev_cheese = cnt;
            bfs();
            time++;
        }
        
    }
    
    


    return 0;
}
