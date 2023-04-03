// BOJ, 10m

#include <iostream>

#define MAX 1001
using namespace std;

int m[MAX][MAX];
int v[MAX];

int N, M;
int cnt;

void dfs(int x){
    if(v[x] == 0){
        v[x] = 1;
        cnt++;
    }
    
    for(int i=1; i<=N; i++){
        if(m[x][i] == 1 && v[i] == 0){
            v[i] = 1;
            dfs(i);
        }
    }
    
}

int main(){
    scanf("%d %d", &N, &M);
    
    for(int i=0; i<M; i++){
        int t1,t2;
        scanf("%d %d", &t1, &t2);
        m[t1][t2] = 1;
        m[t2][t1] = 1;
    }
    
    for(int i=1; i<=N; i++){
        dfs(i);
    }
    printf("%d\n", cnt);
}
