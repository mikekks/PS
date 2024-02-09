#include <string>
#include <vector>
#include <iostream>

#define MAX 55

using namespace std;

int R,C,T;
int Map[MAX][MAX];
int changeValue[MAX][MAX];
int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};

int ret;
vector<int> airPos;


void diffuse(){

    for(int i=0; i<R; i++){
        for(int j=0; j<C; j++){
            int out = Map[i][j] / 5;
            
            if(Map[i][j] == -1){
                continue;
            }
            
            for(int k=0; k<4; k++){
                int ty = i + ud[k];
                int tx = j + lr[k];
                
                if(ty < 0 || tx < 0 || ty >= R || tx >= C){  // 칸이 존재하지 않는 경우
                    continue;
                }
                
                if(Map[ty][tx] == -1){
                    continue;
                }
                
                changeValue[ty][tx] += out;
                changeValue[i][j] -= out;
            }
            
        }
    }
    
    for(int i=0; i<R; i++){
        for(int j=0; j<C; j++){
            Map[i][j] += changeValue[i][j];
            changeValue[i][j] = 0;
        }
    }
    
}

void operAirCleaner(){
    int up_cy = airPos[0];
    int down_cy = airPos[1];
    
    ret -= Map[up_cy-1][0];
    ret -= Map[down_cy+1][0];
    
    for(int i = up_cy-1; i>0; i--){
        Map[i][0] = Map[i-1][0];
    }
    
    for(int i = 0; i<C-1; i++){
        Map[0][i] = Map[0][i+1];
    }
    
    for(int i=0; i<up_cy; i++){
        Map[i][C-1] = Map[i+1][C-1];
    }
    
    for(int i=C-1; i>1; i--){
        Map[up_cy][i] = Map[up_cy][i-1];
    }
    Map[up_cy][1] = 0;
    
    
    
    for(int i = down_cy+1; i<R-1; i++){
        Map[i][0] = Map[i+1][0];
    }
    
    for(int i = 0; i<C-1; i++){
        Map[R-1][i] = Map[R-1][i+1];
    }
    
    for(int i=R-1; i>=down_cy; i--){
        Map[i][C-1] = Map[i-1][C-1];
    }
    
    for(int i=C-1; i>1; i--){
        Map[down_cy][i] = Map[down_cy][i-1];
    }
    Map[down_cy][1] = 0;
    
}

int main(){
    cin >> R >> C >> T;
    
    for(int i=0; i<R; i++){
        for(int j=0; j<C; j++){
            int tmp;
            cin >> tmp;
            if(tmp == -1){
                airPos.push_back(i);
            }
            else{
                ret += tmp;
            }
            
            Map[i][j] = tmp;
        }
    }
    
    while(T > 0){
        // 확산
        diffuse();
        
        // 공기청정기 작동
        operAirCleaner();
        
        T--;
    }
    
    cout << ret << '\n';
    
}
