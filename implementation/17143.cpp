#include <cstring>
#include <vector>
#include <algorithm>
#include <queue>
#include <cmath>
#include <iostream>

#define MAX 105

using namespace std;

int R, C, M;

int ud[5] = {0,-1,1,0,0};
int lr[5] = {0,0,0,1,-1};

int pos[MAX][MAX];

struct shark {
    int y;
    int x;
    int speed;
    int dir;
    int size;
    bool isDie;
};

vector<shark> sList;


void move(int idx){
    int cy = sList[idx].y;
    int cx = sList[idx].x;
    int dir = sList[idx].dir;
    int end = 0;
    
    int mod = 0;
    
    if(dir == 1 || dir == 2){
        mod = 2 * R - 2;
        if(dir == 1){
            end = 1;
        }
        else if(dir == 2){
            end = R;
        }
    }
    else{
        mod = 2 * C - 2;
        if(dir == 3){
            end = C;
        }
        else if(dir == 4){
            end = 1;
        }
    }
    
    
    int final_speed = sList[idx].speed % mod;
    
    // 현재 위치에서 해당 방향으로 final_speed 까지 간 경우에 벽을 넘는 경우, 2번의 벽 부딪히기가 가능
    if(dir == 1 || dir == 2){
        bool remain_0 = abs(end - cy) >= final_speed ? true : false;
        bool remain_1 = abs(end - cy) + R-1 >= final_speed ? true : false;
        
        if(final_speed == 0){
            if(sList[idx].size != 0){
                sList[idx].dir = (dir == 1 ? 2 : 1);
            }
        }
        else if(remain_0){
            sList[idx].y = cy + (ud[dir] * final_speed);
        }
        else if(remain_1){
            sList[idx].dir = (dir == 1 ? 2 : 1);
            sList[idx].y = end + ud[sList[idx].dir] * (final_speed - abs(end - cy));
        }
        else{
            sList[idx].y = cy + (ud[dir]*-1) * ((2*R-2) - final_speed);
        }
    }
    else{
        bool remain_0 = abs(end - cx) >= final_speed ? true : false;
        bool remain_1 = abs(end - cx) + C-1 >= final_speed ? true : false;
        
        if(final_speed == 0){
            if(sList[idx].size != 0){
                sList[idx].dir = (dir == 3 ? 4 : 3);
            }
        }
        else if(remain_0){
            sList[idx].x = cx + (lr[dir] * final_speed);
        }
        else if(remain_1){
            sList[idx].dir = (dir == 3 ? 4 : 3);
            sList[idx].x = end + lr[sList[idx].dir] * (final_speed - abs(end - cx));
        }
        else{
            sList[idx].x = cx + (lr[dir]*-1) * ((2*C-2) - final_speed);
        }
    }
    
    
    
    // 좌표 겹치는 경우 확인
    if(pos[sList[idx].y][sList[idx].x] != 0){
        if(sList[pos[sList[idx].y][sList[idx].x]].size > sList[idx].size){
            sList[idx].isDie = true;
        }
        else{
            sList[pos[sList[idx].y][sList[idx].x]].isDie = true;
            pos[sList[idx].y][sList[idx].x] = idx;
        }
    }
    else{
        pos[sList[idx].y][sList[idx].x] = idx;
    }
    
}

int main(){
    int ans = 0;
    cin >> R >> C >> M;

    sList.push_back({0, 0, 0, 0, 0});

    for(int i=0; i<M; i++){
        int r,c,s,d,z;
        
        cin >> r >> c >> s >> d >> z;
        sList.push_back({r, c, s, d, z});
    }
    
    
    for(int i=1; i<=C; i++){
        int catch_idx = 0;
        int catch_y = 2*R;
        
        for(int j=1; j<sList.size(); j++){
            if(sList[j].isDie) continue;
            
            if(i == sList[j].x){
                if(catch_y > sList[j].y){
                    catch_y = sList[j].y;
                    catch_idx = j;
                }
            }
        }
        
        ans += sList[catch_idx].size;
        sList[catch_idx].isDie = true;
        
        memset(pos, 0, sizeof(pos));
        
        for(int j=1; j<sList.size(); j++){
            if(sList[j].isDie) continue;
            
            move(j);
        }
    }
    
    cout << ans << '\n';
        
}
