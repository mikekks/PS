#include <iostream>
#include <queue>

#define MAX 100001
#define INF 987654321
using namespace::std;

int N, K;
int v[MAX];

int total;

void dij(int n){
    
    priority_queue<pair<int, int>> q;
    
    q.push({0, n});
    
    for(int i=0; i<MAX; i++) v[i] = INF;
    
    v[n] = 0;
    
    while(!q.empty()){
        int time = -q.top().first;
        int cur = q.top().second;
        q.pop();
        
        if(v[K] <= time)
            continue;
        
        if(cur<K && 2*cur < MAX){
            if(v[2*cur] > time){
                q.push({-time, 2*cur});
                v[2*cur] = time;
            }
        }
        
        int n1 = cur - 1;
        int n2 = cur + 1;
        
        if(n1 >= 0 && n1 < MAX){
            if(v[n1] > time+1){
                v[n1] = time+1;
                q.push({-(time+1), n1});
            }
        }
        
        if(n2 >= 0 && n2 < MAX){
            if(v[n2] > time+1){
                v[n2] = time+1;
                q.push({-(time+1), n2});
            }
        }
        
    }
    
   
}

int main(){
    scanf("%d %d", &N, &K);
    
    dij(N);
    
    printf("%d\n", v[K]);
}
