#include <iostream>
#include <queue>

using namespace::std;

int M, N;
int tmt[1001][1001];
int num[1001][1001];

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};

queue<pair<int, int>> q;


void bfs(int y, int x){
   
    for(int i=0; i<4; i++){
        int ny = y+ud[i];
        int nx = x+lr[i];
        tmt[y][x] = 1;
        
        if(ny>=0 && nx>=0 && ny<N && nx<M){
            if(tmt[ny][nx] == 0 && num[ny][nx] == 0){
                q.push({ny, nx});
                num[ny][nx] = num[y][x] + 1;
            }
        }
    }
    
}

int main(){
    scanf("%d %d", &M, &N);
    
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            scanf("%d", &tmt[i][j]);
        }
    }
    int t1 = 0;
    
     for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if(tmt[i][j] == 1){
                num[i][j] = 0;
                bfs(i, j);
            }else if(tmt[i][j] == 0){
                t1++;
            }
        }
    }
    
    if(t1 == 0){
        printf("0\n");
        return 0;
    }
    
    while(!q.empty()){
        int ty = q.front().first;
        int tx = q.front().second;
        q.pop();
        bfs(ty,tx);
        
    }
    
    int result = 0;
    for(int i=0; i<N; i++){
       for(int j=0; j<M; j++){
           if(result<num[i][j])
               result = num[i][j];
           else if(tmt[i][j] == 0){
               printf("-1\n");
               return 0;
           }
               
       }
   }
    
    printf("%d\n", result);
    
    
}
