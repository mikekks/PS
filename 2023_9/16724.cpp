#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>
#include <map>


#define MAX 1005

using namespace std;

typedef long long ll;

int N, M;

int cy, cx;
int visited[MAX][MAX];

ll ans;

pair<int, int> P[MAX][MAX];

void find_cycle(int y, int x){
    
    if(visited[y][x] == 0){
        visited[y][x] = 1;
        find_cycle(P[y][x].first, P[y][x].second);
        visited[y][x] = 2;
    }else if(visited[y][x] == 1){
        ans++;
    }
    
}



void solve(){
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if(visited[i][j] == 0)
                find_cycle(i, j);
        }
    }
    
    
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N >> M;
    
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            char arrow;
            cin >> arrow;
            if(arrow == 'U'){
                P[i][j] = {i-1, j};
            }
            else if(arrow == 'D'){
                P[i][j] = {i+1, j};
            }
            else if(arrow == 'L'){
                P[i][j] = {i, j-1};
            }
            else if(arrow == 'R'){
                P[i][j] = {i, j+1};
            }
        }
    }
    
    solve();
    
    cout << ans << '\n';
    
    return 0;
}
