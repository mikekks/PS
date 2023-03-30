#include <iostream>
#include <deque>

#define MAX 100001

using namespace::std;

int N, K;
int v[MAX];

int bfs(){
    deque<int> q;
    q.push_back(N);
    v[N] = 1;
    while(!q.empty()){
        int tx = q.front();
        q.pop_front();
        
        if(tx == K) return v[K] - 1;
        
        if(tx*2 < MAX && !v[tx*2]){
            q.push_front(tx*2);
            v[tx*2] = v[tx];
        }
        if(tx+1 < MAX && !v[tx+1]){
            q.push_back(tx+1);
            v[tx+1] = v[tx]+1;
        }
        if(tx-1 < MAX && !v[tx-1]){
            q.push_back(tx-1);
            v[tx-1] = v[tx]+1;
        }
       
    }
    return 0;
}

int main(){
    scanf("%d %d", &N, &K);
    
    printf("%d\n", bfs());
}
