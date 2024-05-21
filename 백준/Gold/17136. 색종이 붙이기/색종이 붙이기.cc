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

int map[11][11];
int ans = 987654321;
int paper_cnt[5];


bool checkPaper(int sy, int ey, int sx, int ex){
    for(int i=sy; i<=ey; i++){
        for(int j=sx; j<=ex; j++){
            if(map[i][j] == 0) return false;
        }
    }
    
    return true;
}

void updatePaper(int sy, int ey, int sx, int ex, int oper){
    for(int i=sy; i<=ey; i++){
        for(int j=sx; j<=ex; j++){
            map[i][j] = oper;
        }
    }
}

void dfs(int y, int x, int cnt){
    int i = y;
    int j = x;
    bool find = false;
    
    for(; i<=10; i++){
        for(; j<=10; j++){
            if(map[i][j] == 1){
                find = true;
                break;
            }
            
            if(i == 10 && j == 10){
                ans = cnt < ans ? cnt : ans;
            }
        }
        
        if(find) break;
        j = 0;
    }
    
    if(cnt > ans){
        return;
    }
    
    y = i;
    x = j;
    
    for(int k=4; k>=0; k--){
        int ey = y + k;
        int ex = x + k;
        
        if(ey < 1 || ex < 1 || ey > 10 || ex > 10) continue;
        
        if(paper_cnt[k] >= 5) continue;
        
        if(checkPaper(y, ey, x, ex)){
            updatePaper(y, ey, x, ex, 0);
            paper_cnt[k]++;
            dfs(y, x, cnt+1);
            updatePaper(y, ey, x, ex, 1);
            paper_cnt[k]--;
        }
    }
    
}


int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);
    cout.tie(NULL);
    
    for(int i=1; i<=10; i++){
        for(int j=1; j<=10; j++){
            cin >> map[i][j];
        }
    }
    
    dfs(1, 1, 0);
    
    if(ans == 987654321) ans = -1;
    
    printf("%d\n", ans);
    
}
