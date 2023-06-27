#include <iostream>
#include <queue>

#define MAX 100001
#define INF 987654321
using namespace::std;

int N, K;
int v[MAX];
int visited[MAX];
int total;

void dij(int n){
    
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> q;
    
    q.push({0, n});
    visited[n] = 1;
    
    for(int i=0; i<MAX; i++) v[i] = INF;
    
    while(!q.empty()){
        int time = q.top().first;
        int cur = q.top().second;
        q.pop();
        
        if(cur == K){
            v[cur] = time;
            return;
        }
            
        
        if(cur < K && 2*cur < MAX && visited[2*cur] == 0){
            visited[2*cur] = 1;
            q.push({time, 2*cur});
        }
        
        
        int n1 = cur - 1;
        int n2 = cur + 1;
        
        if(n1 >= 0 && n1 < MAX && visited[n1] == 0){
            visited[n1] = 1;
            q.push({(time+1), n1});
            
        }
        
        if(n2 >= 0 && n2 < MAX && visited[n2] == 0){
            visited[n2] = 1;
            q.push({(time+1), n2});
            
            
        }
        
    }
    
   
}

int main(){
    scanf("%d %d", &N, &K);
    
    dij(N);
    
    printf("%d\n", v[K]);
}
