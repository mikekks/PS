#include <iostream>
#include <vector>
#include <queue>
#include <cstring>

using namespace::std;

int T;
int n,m,k;
int cnt;

int ba[51][51];
int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};

void bfs(int y, int x){
    queue<pair<int, int>> q;
    ba[y][x] = 0;
    cnt++;
    q.push({y,x});
    
    while(!q.empty()){
        int ty = q.front().first;
        int tx = q.front().second;
        q.pop();
       
        for(int i=0; i<4; i++){
            int ny = ty+ud[i];
            int nx = tx+lr[i];
            if(ny>=0 && nx>=0 && ny<=50 && nx<=50){
                if(ba[ny][nx] == 1){
                    ba[ny][nx] = 0;
                    q.push({ny,nx});
                }
            }
        }
        
    }
}

int main(){
    scanf("%d", &T);
    
    for(int i=0; i<T; i++){
        cnt = 0;
        scanf("%d %d %d", &m, &n, &k);
        for(int i=0; i<k; i++){
            int y,x;
            scanf("%d %d", &y, &x);
            ba[y][x] = 1;
        }
        
        for(int y=0; y<=50; y++){
            for(int x=0; x<=50; x++){
                if(ba[y][x] == 1){
                    bfs(y, x);
                }
            }
        }
        printf("%d\n", cnt);
        memset(ba, 0, sizeof(ba));
        
    }
    
 
    
}
