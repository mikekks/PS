#include <iostream>
#include <string>
#include <sstream>
#include <cmath>
#include <vector>
#include <algorithm>
#include <queue>
#include <cstring>
#include <climits>

using namespace std;

#define MAX 15

int N;
int map[MAX][MAX];
int selected[MAX];
int l[20];
int r[20];
int ans[2];


void dfs(int y, int x, int cnt, int oper){
    if(x > N){
        y++;
        if(x%2 == 0) x = 1;
        else x = 0;
    }
    
    if(y > N){
        ans[oper] = cnt > ans[oper] ? cnt : ans[oper];
        return;
    }
    
    if(map[y][x] && !l[x-y+N-1] && !r[y+x]){
        l[x-y+N-1] = 1;
        r[y+x] = 1;
        dfs(y, x+2, cnt+1, oper);
        l[x-y+N-1] = 0;
        r[y+x] = 0;
    }
    dfs(y, x+2, cnt, oper);
    
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    
    cin >> N;
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            cin >> map[i][j];
        }
    }
    
    dfs(1,1,0,0);
    dfs(1,2,0,1);
    
    
    cout << ans[0] + ans[1] << '\n';
    
    return 0;
}

