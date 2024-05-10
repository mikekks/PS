#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>
#include <list>
#include <sstream>
#include <cmath>

#define MAX 55
#define INF 987654321

using namespace std;
int N, M;
int map[MAX][MAX];
int spreadMap[MAX][MAX];
int emptyCnt;
int ans = INF;
vector<pair<int, int>> virus;
bool visited[MAX];

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};

void spread(queue<pair<int, int>> q){
    int cnt = 0;
    int time = 0;
    
    while (!q.empty()) {
        int cy = q.front().first;
        int cx = q.front().second;
        q.pop();
        
        for(int i=0; i<4; i++){
            int ty = cy+ud[i];
            int tx = cx+lr[i];
            
            if(ty < 1 || tx < 1 || ty > N || tx > N) continue;
            if(map[ty][tx] == 1 || spreadMap[ty][tx] != -1) continue;
            
            spreadMap[ty][tx] = spreadMap[cy][cx] + 1;
            if(map[ty][tx] == 0){
                cnt++;
                time = spreadMap[ty][tx];
            }
            q.push({ty, tx});
        }
    }
    
    if(emptyCnt == cnt){
        ans = time < ans ? time : ans;
    }
    
}

void selectVirus(int cur, int cnt){
    if(cnt == M){
        queue<pair<int, int>> q;
        memset(spreadMap, -1, sizeof(spreadMap));
        
        for(int i=0; i<virus.size(); i++){
            int ty = virus[i].first;
            int tx = virus[i].second;
            if(visited[i]){
                q.push({ty, tx});
                spreadMap[ty][tx] = 0;
            }
        }
        
        spread(q);
        return;
    }
    
    for(int i=cur; i<virus.size(); i++){
        if(!visited[i]){
            visited[i] = true;
            selectVirus(i+1, cnt+1);
            visited[i] = false;
        }
    }
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N >> M;
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            cin >> map[i][j];
            if(map[i][j] == 0){
                emptyCnt++;
            }
            else if(map[i][j] == 2){
                virus.push_back({i, j});
            }
        }
    }
    
    selectVirus(0, 0);
    
    if(ans == INF){
        ans = -1;
    }
    
    cout << ans << '\n';
}
