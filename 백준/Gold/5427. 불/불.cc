#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 1005
#define INF 987654321

using namespace std;

// 1:24 시작

int T;

char map[MAX][MAX];
int fMap[MAX][MAX];
bool isVisit[MAX][MAX];

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};

int main() {
    cin >> T;
    
    while (T--) {
        int w, h;
        cin >> w >> h;
        int sy, sx;
        memset(map, '\0', sizeof(map));
        memset(fMap, '\0', sizeof(fMap));
        memset(isVisit, '\0', sizeof(isVisit));

        queue<pair<int, pair<int, int>>> q;
        
        for(int i=1; i<=h; i++){
            string tmp;
            cin >> tmp;
            for(int j=1; j<=w; j++){
                fMap[i][j] = INF; // 여기서 초기화
                
                map[i][j] = tmp[j-1];
                if(map[i][j] == '@'){
                    sy = i;
                    sx = j;
                }
                else if(map[i][j] == '*'){
                    q.push({0, {i, j}});
                    fMap[i][j] = 0;
                }
            }
        }
        
        while(!q.empty()){
            int cy = q.front().second.first;
            int cx = q.front().second.second;
            int time = q.front().first;
            q.pop();
            
            for(int i=0; i<4; i++){
                int ny = cy+ud[i];
                int nx = cx+lr[i];
                
                if(ny < 1 || nx < 1 || ny > h || nx > w) continue;
                
                if(fMap[ny][nx] == INF && map[ny][nx] != '#'){
                    q.push({time+1, {ny, nx}});
                    fMap[ny][nx] = time+1;
                }
            }
        }
        
        q.push({0, {sy, sx}});
        isVisit[sy][sx] = true;
        int ans = -1;
        
        while (!q.empty()) {
            int cy = q.front().second.first;
            int cx = q.front().second.second;
            int time = q.front().first;
            q.pop();
            
            if(ans != -1){
                break;
            }
            
            for(int i=0; i<4; i++){
                int ny = cy+ud[i];
                int nx = cx+lr[i];
                
                if(ny < 1 || nx < 1 || ny > h || nx > w){
                    ans = time+1;
                    break;
                }
                
                if(isVisit[ny][nx]) continue;
                
                if(time+1 < fMap[ny][nx] && map[ny][nx] == '.'){
                    q.push({time+1, {ny, nx}});
                    isVisit[ny][nx] = true;
                }
            }
            
        }
        
        if(ans == -1){
            cout << "IMPOSSIBLE" << '\n';
        }
        else{
            cout << ans << '\n';
        }
        
    }
    
}
