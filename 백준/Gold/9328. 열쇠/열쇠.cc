#include <iostream>
#include <string>
#include <sstream>
#include <cmath>
#include <vector>
#include <algorithm>
#include <queue>
#include <cstring>

using namespace std;

#define MAX 105


char map[MAX][MAX];
int ud[4] = {-1,0,0,1};
int lr[4] = {0,-1,1,0};
bool isProcess[MAX][MAX];
bool key[30];

int ans = 0;
int T, H, W;


bool bfs(int y, int x){
    queue<pair<int, int>> q;
    bool isVisit[MAX][MAX];
    memset(isVisit, false, sizeof(isVisit));
    isVisit[y][x] = true;
    q.push({y, x});
    
    while (!q.empty()) {
        int cy = q.front().first;
        int cx = q.front().second;
        q.pop();
        
        if(cy == 1 || cx == 1 || cy == H || cx == W){
            return true;
        }
        
        for(int i=0; i<4; i++){
            int ty = cy+ud[i];
            int tx = cx+lr[i];
            
            if(ty < 1 || tx < 1 || ty > H || tx > W) continue;
            if(map[ty][tx] == '*') continue;
            if(isVisit[ty][tx]) continue;
            
            if(map[ty][tx] == '.'){    // $ 조건 어때?
                isVisit[ty][tx] = true;
                q.push({ty, tx});
            }
            else if(map[ty][tx] >= 'A' && map[ty][tx] <= 'Z'){
                if(key[map[ty][tx]+32-97]){
                    isVisit[ty][tx] = true;
                    q.push({ty, tx});
                }
                
            }
            else if(map[ty][tx] >= 'a' && map[ty][tx] <= 'z'){
                isVisit[ty][tx] = true;
                q.push({ty, tx});
            }
            else if(map[ty][tx] == '$'){
                isVisit[ty][tx] = true;
                q.push({ty, tx});
            }
            
        }
    }
    
    return false;
}

int main() {
    
    cin >> T;
    
    while (T-->0) {
        cin >> H >> W;
        
        memset(map, '\0', sizeof(map));
        memset(key, false, sizeof(key));
        
        vector<pair<int, int>> v;
        vector<pair<int, int>> papers;
        
        for(int i=1; i<=H; i++){
            for(int j=1; j<=W; j++){
                cin >> map[i][j];
                if(map[i][j] == '$'){
                    papers.push_back({i, j});
                }
                else if(map[i][j] >= 'a' && map[i][j] <= 'z'){
                    v.push_back({i, j});
                }
                
            }
        }
        
        string tmp_key;
        cin >> tmp_key;
        
        if(tmp_key[0] != '0'){
            for(int i=0; i<tmp_key.size(); i++){
                key[tmp_key[i] - 97] = true;
            }
        }
        
        // 반복
        bool check = true;
        while(check){
            check = false;
            
            for(int i=0; i<v.size(); i++){
                int cy = v[i].first;
                int cx = v[i].second;
                if(cy != 0 && cx != 0 && bfs(cy, cx)){
                    key[map[cy][cx]-97] = true;
                    check = true;
                    v[i].first = 0;
                    v[i].second = 0;
                }
            }
            
            for(int i=0; i<papers.size(); i++){
                int cy = papers[i].first;
                int cx = papers[i].second;
                if(cy != 0 && cx != 0 && bfs(cy, cx)){
                    papers[i].first = 0;
                    papers[i].second = 0;
                    ans++;
                }
            }
            
        }
        
        cout << ans << '\n';
        ans = 0;
    }
    return 0;
}


