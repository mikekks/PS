#include <iostream>
#include <cstdio>
#include <list>
#include <set>
#include <vector>
#include <algorithm>
#include <cstring>

using namespace std;
int MAX = -1000000000;
int MIN = 1000000000;
int N;
int arr[13];
int oper[5];
int tmp[13];

void cal(int V, int opers){
    if(opers == 0){
        tmp[V+1] = tmp[V] + arr[V+1];
    }else if(opers == 1){
        tmp[V+1] = tmp[V] - arr[V+1];
    }else if(opers == 2){
        tmp[V+1] = tmp[V] * arr[V+1];
    }else if(opers == 3){
        tmp[V+1] = tmp[V] / arr[V+1];
    }
}

void dfs(int index){
    if(index == N-1){
        if(tmp[N-1] >= MAX){
            MAX = tmp[N-1];
        }
        if(tmp[N-1] <= MIN){
            MIN = tmp[N-1];
            
        }
        return;
    }
    
    for(int i=0; i<4; i++){
        if(oper[i] > 0){
            oper[i] -= 1;
            cal(index, i);
            dfs(index+1);
            oper[i] += 1;
        }
    }
}


int main() {
    scanf("%d", &N);
    
    for(int i=0; i<N; i++){
        scanf("%d", &arr[i]);
    }
    for(int i=0; i<4; i++){
        scanf("%d", &oper[i]);
    }
    
    tmp[0] = arr[0];
    dfs(0);
    
    printf("%d\n", MAX);
    printf("%d\n", MIN);

}
