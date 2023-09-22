#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>

#define MAX 100005

using namespace std;

typedef long long ll;

int N, S;

bool Map[11][11];
bool tmp_Map[11][11];
int ans = 987654321;

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};

int cnt;


void changeStatus(int y, int x){
    tmp_Map[y][x] = !tmp_Map[y][x];
    
    for(int i=0; i<4; i++){
        int ty = y + ud[i];
        int tx = x + lr[i];
        
        if(ty >= 10 || ty < 0 || tx >= 10 || tx < 0) continue;
        
        tmp_Map[ty][tx] = !tmp_Map[ty][tx];
    }
}

bool rowCheck(){
    
    for(int i=1; i<10; i++){
        for(int j=0; j<10; j++){
            bool prev = tmp_Map[i-1][j];
            if(prev){
                changeStatus(i, j);
                cnt++;
            }
        }
    }
    
    for(int i=0; i<10; i++){
        if(tmp_Map[9][i]) return false;
    }
    
    return true;
}

void solve(){
    
    for(int status=0; status<1024; status++){
        
        cnt = 0;
        
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                tmp_Map[i][j] = Map[i][j];
            }
        }
        
        for(int i=0; i<10; i++){
            if(status & (1 << i)){  // 선택하는 경우
                cnt++;
                changeStatus(0, i);
            }
        }
        
        bool ret = rowCheck();
        
        if(ret){
            ans = min(ans, cnt);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    for(int i=0; i<10; i++){
        string tmp;
        cin >> tmp;
        for(int j=0; j<10; j++){
            
            if(tmp[j] == '#'){
                Map[i][j] = false;
            }
            else{
                Map[i][j] = true;
            }
            
        }
    }
    
    solve();
    
    if(ans == 987654321)
        ans = -1;
    
    cout << ans << '\n';
        
    return 0;
}
