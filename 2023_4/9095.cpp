#include <iostream>

int T;
int n;
int cnt;

void dfs(int cur){
    if(cur == n){
        cnt++;
        return;
    }else if(cur > n){
        return;
    }
    
    for(int i=1; i<=3; i++){
        dfs(cur+i);
    }
}

int main(){
    
    scanf("%d", &T);
    
    for(int i=0; i<T; i++){
        scanf("%d", &n);
        cnt = 0;
        dfs(0);
        printf("%d\n", cnt);
    }
}
