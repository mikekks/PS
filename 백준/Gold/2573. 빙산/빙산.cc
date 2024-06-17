#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 305


using namespace std;

int N, M;
int map[MAX][MAX];
int tmp[MAX][MAX];
bool isVisit[MAX][MAX];

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};
bool isSingle = true;

int ans = 0;


void bfs(int y, int x){
    queue<pair<int, int>> q;
    
    q.push({y,x});
    isVisit[y][x] = true;
    
    while (!q.empty()) {
        int cy = q.front().first;
        int cx = q.front().second;
        q.pop();
        
        for(int i=0; i<4; i++){
            int ny = cy+ud[i];
            int nx = cx+lr[i];
            
            if(ny <= 1 || nx <= 1 || ny >= N || nx >= M) continue;
            
            if(isVisit[ny][nx]) continue;
            
            if(map[ny][nx] == 0) continue;
            
            q.push({ny,nx});
            isVisit[ny][nx] = true;
        }
    }
    

}

void melting(){
    memset(tmp, 0, sizeof(tmp));
    
    for(int i=2; i<=N-1; i++){
        for(int j=2; j<=M-1; j++){
            if(map[i][j] != 0){
                int cnt = 0;
                for(int k=0; k<4; k++){
                    int ni = i+ud[k];
                    int nj = j+lr[k];
                    if(map[ni][nj] == 0) cnt += 1;
                }
                int val = map[i][j] - cnt;
                if(val > 0) tmp[i][j] = val;
            }
        }
    }
    
    for (int i=2; i<=N-1; i++) {
        for (int j=2; j<=M-1; j++) {
            map[i][j] = tmp[i][j];
        }
    }
}

int main() {

    cin >> N >> M;
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=M; j++){
            cin >> map[i][j];
        }
    }
    
    int year = 0;
    
    while(true){
        // 두 덩어리 확인
        memset(isVisit, false, sizeof(isVisit));
        int area = 0;
        
        for(int i=2; i<=N-1; i++){
            for(int j=2; j<=M-1; j++){
                if(map[i][j] != 0 && isVisit[i][j] == false){
                    bfs(i, j);
                    area++;
                }
            }
        }
        
        if(area >= 2){
            ans = year;
            break;
        }
        else if(area == 0){
            ans = 0;
            break;
        }
        
        // 일년 녹기
        melting();
        year++;
    }
    
    
    
    cout << ans << '\n';
}
