#include <cstring>
#include <vector>
#include <iostream>
#include <cmath>
#include <algorithm>

#define MAX 100005

using namespace std;

int N, M, x, y, cnt;
int cur[3];
int Map[25][25];


int dis[7];

int checkValue_y(int y){
    if(y >= M){
        return y-1;
    }
    if(y < 0){
        return y+1;
    }
    return y;
}

int checkValue_x(int x){
    if(x >= N){
        return x-1;
    }
    if(x < 0){
        return x+1;
    }
    return x;
}

int main(){
    cin >> N >> M >> x >> y >> cnt;
    
    
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cin >> Map[i][j];
        }
    }
    
    for(int i=0; i<cnt; i++){
        int cmd = 0;
        cin >> cmd;
        
        int tmp = 0;
        if(cmd == 1){
            y++;
            tmp = checkValue_y(y);
            if(y != tmp){
                y = tmp;
                continue;
            }
            y = tmp;
        }
        else if(cmd == 2){
            y--;
            tmp = checkValue_y(y);
            if(y != tmp){
                y = tmp;
                continue;
            }
            y = tmp;
        }
        else if(cmd == 3){
            x--;
            tmp = checkValue_x(x);
            if(x != tmp){
                x = tmp;
                continue;
            }
            x = tmp;
        }
        else if(cmd == 4){
            x++;
            tmp = checkValue_x(x);
            if(x != tmp){
                x = tmp;
                continue;
            }
            x = tmp;
        }
        
        if(cmd == 1){
            int prev1 = dis[1];
            int prev3 = dis[3];
            int prev4 = dis[4];
            int prev6 = dis[6];
            
            dis[1] = prev4;
            dis[3] = prev1;
            dis[4] = prev6;
            dis[6] = prev3;
            
        }
        else if(cmd == 2){
            int prev1 = dis[1];
            int prev3 = dis[3];
            int prev4 = dis[4];
            int prev6 = dis[6];
            
            dis[1] = prev3;
            dis[3] = prev6;
            dis[4] = prev1;
            dis[6] = prev4;
        }
        else if(cmd == 4){
            int prev1 = dis[1];
            int prev2 = dis[2];
            int prev5 = dis[5];
            int prev6 = dis[6];
            
            dis[1] = prev2;
            dis[2] = prev6;
            dis[5] = prev1;
            dis[6] = prev5;
            
        }
        else if(cmd == 3){
            int prev1 = dis[1];
            int prev2 = dis[2];
            int prev5 = dis[5];
            int prev6 = dis[6];
            
            dis[1] = prev5;
            dis[2] = prev1;
            dis[5] = prev6;
            dis[6] = prev2;
        }
        
        cout << dis[1] << '\n';
        
        // 해당 칸이 0인 경우
        if(Map[x][y] == 0){
            Map[x][y] = dis[6];
        }
        else{   // 해당 칸이 0인 아닌 경우
            dis[6] = Map[x][y];
            Map[x][y] = 0;
        }
        
    }
}
