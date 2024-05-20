#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>
#include <list>
#include <sstream>
#include <cmath>

using namespace std;

#define MAX 505
int R, C;
bool isBlank[10005][505];
bool isVisited[10005][505];
int ans;

int move_arr[3] = {-1,0,1};

bool process(int y, int x){
    isVisited[y][x] = true;
    
    if(x == C){
        return true;
    }
    
    for(int i=0; i<3; i++){
        int ny = y + move_arr[i];
        int nx = x+1;
        
        if(ny < 1 || nx < 1 || ny > R || nx > C) continue;
        
        if(isVisited[ny][nx] == false && isBlank[ny][nx]){
            bool result = process(ny, nx);
            if(result){
                return true;
            }
        }
    }
    
    return false;
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> R >> C;
    
    for(int i=1; i<=R; i++){
        for(int j=1; j<=C; j++){
            char tmp;
            cin >> tmp;
            if(tmp == 'x'){
                isBlank[i][j] = false;
            }
            else{
                isBlank[i][j] = true;
            }
        }
    }
    
    
    for(int i=1; i<=R; i++){
        if(process(i, 1)){
            ans++;
        }
    }
    printf("%d\n", ans);
    
}
