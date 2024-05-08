#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>
#include <list>
#include <sstream>
#include <cmath>

#define MAX 100005
#define INF 1000000005

using namespace std;

int N, M, H;
int map[31][11];  // y축=H, x축=번호
int total;
bool pos = true;

int getNext(int y, int x){
    if(map[y][x] == x-1){
        return x-1;
    }
    else if(map[y][x] == x+1){
        return x+1;
    }
    
    return 0;
}

bool checkPos(){
    for(int i=1; i<=N; i++){
        int cur = i;
        for(int j=1; j<=H; j++){
            if(map[j][cur] != 0){
                int nx = getNext(j, cur);
                cur = nx;
            }
        }
        if(cur != i){
            return false;
        }
    }
    
    return true;
}

void dfs(int cur, int cnt){
    if(cnt >= 4){
        return;
    }
    
    if(checkPos()){
        if(cnt < total){
            total = cnt;
        }
        return;
    }
    
    for(int i=cur; i<=H; i++){
        for(int j=1; j<N; j++){
            if(map[i][j] != 0) continue;
            if(map[i][j+1] != 0) continue;
            
            map[i][j] = j+1;
            map[i][j+1] = j;
            dfs(i, cnt+1);
            map[i][j] = 0;
            map[i][j+1] = 0;
        }
    }
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N >> M >> H;
    
    for(int i=0; i<M; i++){
        int a,b;
        cin >> a >> b;
        map[a][b] = b+1;
        map[a][b+1] = b;
    }
    
    total = INF;
    dfs(1, 0);
    
    
    if(total == INF){
        cout << -1 << '\n';
    }
    else{
        cout << total << '\n';
    }
    
}
