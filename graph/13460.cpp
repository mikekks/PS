#include <cstring>
#include <vector>
#include <algorithm>
#include <queue>
#include <cmath>
#include <iostream>

using namespace std;

int N, M;

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};

bool visited[15][15][15][15];
char Map[15][15];

struct step{
    int ry, rx;
    int by, bx;
    int cnt;
};

void process(int& ry, int& rx, int idx, int& cnt){
    while (Map[ry + ud[idx]][rx + lr[idx]] != '#' && Map[ry][rx] != 'O') {
        ry += ud[idx];
        rx += lr[idx];
        cnt++;
    }
}


void bfs(int ry, int rx, int by, int bx){
    
    queue<step> q;
    q.push({ry, rx, by, bx, 0});
    visited[ry][rx][by][bx] = true;
    
    while(!q.empty()){
        int cur_ry = q.front().ry;
        int cur_rx = q.front().rx;
        int cur_by = q.front().by;
        int cur_bx = q.front().bx;
        int cur_cnt = q.front().cnt;
        q.pop();
        
        if(cur_cnt >= 10) continue;
        
        for(int i=0; i<4; i++){
            int tmp_ry = cur_ry;
            int tmp_rx = cur_rx;
            int tmp_by = cur_by;
            int tmp_bx = cur_bx;
            int red_cnt = 0;
            int blud_cnt = 0;
                        
            process(tmp_ry, tmp_rx, i, red_cnt);
            process(tmp_by, tmp_bx, i, blud_cnt);
            
            if(Map[tmp_by][tmp_bx] == 'O'){
                continue;
            }
            
            if(Map[tmp_ry][tmp_rx] == 'O'){
                cout << cur_cnt + 1 << '\n';
                return;;
            }
            
            // 겹치는 경우
            if(tmp_ry == tmp_by && tmp_rx == tmp_bx){
                if(red_cnt > blud_cnt){
                    tmp_ry -= ud[i];
                    tmp_rx -= lr[i];
                }
                else{
                    tmp_by -= ud[i];
                    tmp_bx -= lr[i];
                }
            }
            
            
            if(visited[tmp_ry][tmp_rx][tmp_by][tmp_bx]){
                continue;
            }
            visited[tmp_ry][tmp_rx][tmp_by][tmp_bx] = true;
            q.push({tmp_ry, tmp_rx, tmp_by, tmp_bx, cur_cnt + 1});
            
        }
        
    }
    
    cout << -1 << '\n';
}

int main(){
    cin >> N >> M;
    int ry=0, rx=0, by=0, bx=0;
    
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cin >> Map[i][j];
            if(Map[i][j] == 'R'){
                ry = i;
                rx = j;
            }
            if(Map[i][j] == 'B'){
                by = i;
                bx = j;
            }
        }
    }
    
    bfs(ry, rx, by, bx);
        
}
