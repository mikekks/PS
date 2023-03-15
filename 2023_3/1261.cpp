#include <iostream>
#include <queue>
#include <cstring>
#include <climits>

#define MAX 101

using namespace::std;

int N, M;
int map[MAX][MAX];
int wall[MAX][MAX];


int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};

void bfs(int y, int x){
    
    queue<pair<int, int>> q;
    
    q.push({y, x});
    
    while(!q.empty()){
        int ny = q.front().first;
        int nx = q.front().second;
        int cnt = wall[ny][nx];
        q.pop();
        
        if(ny == N && nx == M){
            continue;
        }
        
        
        for(int i=0; i<4; i++){
            int ty = ny + ud[i];
            int tx = nx + lr[i];

            if(ty >= 1 && tx >= 1 && ty <= N && tx <= M){
                if(map[ty][tx] == 1){
                    if(wall[ty][tx] > cnt+1){
                        wall[ty][tx] = cnt+1;
                        q.push({ty, tx});
                    }
                }else{
                    if(wall[ty][tx] > cnt){
                        wall[ty][tx] = cnt;
                        q.push({ty, tx});
                    }
                }
            }
            
        }
    }
    
}

int main(){
    scanf("%d %d", &M, &N);
    
    for(int i = 1; i<=N; i++){
            for(int j = 1; j<=M; j++){
                wall[i][j] = INT_MAX;
            }
        }
    
    for(int i=1; i<=N; i++){
        string tmp;
        cin >> tmp;
        for(int j=1; j<=M; j++){
            map[i][j] = tmp[j-1] - 48;
        }
    }
    wall[1][1] = 0;
    bfs(1,1);
    
    printf("%d\n", wall[N][M]);
}
