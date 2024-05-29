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


int map[MAX][MAX];
int r_map[MAX][MAX];

int ans = 0;
int N, L;


bool processRoad(int y, int x, int h){

    for(int i=x+1; i<x+L; i++){
        if(h != map[y][i]) return false;
    }
    
    return true;
}

bool process(int i){
    int stack = 1;
    int canRoad = true;
    
    for(int j=1; j<=N-1; j++){
        if(map[i][j] == map[i][j+1]) stack++;
        else if(map[i][j] == map[i][j+1] + 1){
            if(processRoad(i, j+1, map[i][j+1])){
                j = j + L - 1;
                stack = 0;
            }
            else{
                canRoad = false;
                break;
            }
        }
        else if(map[i][j] + 1 == map[i][j+1]){
            if(stack >= L){
                stack = 1;
            }
            else{
                canRoad = false;
                break;
            }
        }
        else if(abs(map[i][j] - map[i][j+1]) >= 2){
            canRoad = false;
            break;
        }
    }
    
    return canRoad;
}


int main() {
    
    cin >> N >> L;
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            cin >> map[i][j];
            r_map[j][i] = map[i][j];
        }
    }
    
    
    // y축
    int cnt = 0;
    
    for(int i=1; i<=N; i++){
        if(process(i)){
            cnt++;
        }
    }
    
    
    // x축
    memset(map, '\0', sizeof(map));
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            map[i][j] = r_map[i][j];
        }
    }
    
    
    for(int j=1; j<=N; j++){
        if(process(j)) cnt++;
    }
    
    cout << cnt << '\n';
    return 0;
}


