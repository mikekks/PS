#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>
#include <list>
#include <sstream>
#include <cmath>

#define MAX 205
#define INF 987654321

using namespace std;
int K;
int H, W;
int map[MAX][MAX];
int cMap[MAX][MAX][35];
int ans = -1;

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};

int night_ud[8] = {-1,-2,-2,-1,1,2,2,1};
int night_lr[8] = {-2,-1,1,2,-2,-1,1,2};

struct monkey{
    int y;
    int x;
    int horse;
    int cost;
};

struct compareTo{
    bool operator()(const monkey& m1, const monkey& m2) {
        return m1.cost > m2.cost;
    }
};

void dijk(){
    
    priority_queue<monkey, vector<monkey>, compareTo> q;
    q.push({1,1,K});
    
    while (!q.empty()) {
        int cy = q.top().y;
        int cx = q.top().x;
        int h = q.top().horse;
        int cost = q.top().cost;
        q.pop();
        
        if(cost > cMap[cy][cx][h]) continue;
        
        if(cy == H && cx == W){
            cMap[H][W][h] = cost;
            continue;
        }
        
        for(int i=0; i<4; i++){
            int ty = cy+ud[i];
            int tx = cx+lr[i];
            
            if(ty < 1 || tx < 1 || ty > H || tx > W) continue;
            if(map[ty][tx] == 1) continue;
            
            if(cost+1 < cMap[ty][tx][h]){
                cMap[ty][tx][h] = cost+1;
                q.push({ty,tx,h,cost+1});
            }
        }
        
        if(h <= 0) continue;

        for(int i=0; i<8; i++){
            int ty = cy+night_ud[i];
            int tx = cx+night_lr[i];

            if(ty < 1 || tx < 1 || ty > H || tx > W) continue;
            if(map[ty][tx] == 1) continue;

            if(cost+1 < cMap[ty][tx][h-1]){
                cMap[ty][tx][h-1] = cost+1;
                q.push({ty,tx,h-1,cost+1});
            }
        }
        
    }
    
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> K;
    cin >> W >> H;
    
    for(int i=1; i<=H; i++){
        for(int j=1; j<=W; j++){
            cin >> map[i][j];
            for(int k=0; k<=K; k++){
                cMap[i][j][k] = INF;
            }
        }
    }
    
    dijk();
    
    ans = INF;
    for(int i=0; i<=K; i++){
        if(cMap[H][W][i] < ans){
            ans = cMap[H][W][i];
        }
    }
    
    if(ans == INF){
        ans = -1;
    }
    
    cout << ans << '\n';
    
}
