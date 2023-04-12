#include <iostream>
#include <vector>
#include <climits>
#include <queue>
#include <cstring>

#define MAX 20001
#define INF 999999999

using namespace::std;

vector<pair<int, int>> graph[MAX];
int d[MAX];
int allowed[MAX];

int V,E,K;

int main(){
    scanf("%d %d", &V, &E);
    scanf("%d", &K);
    
    
    
    for(int i=0; i<E; i++){
        int t1,t2,t3;
        scanf("%d %d %d", &t1, &t2, &t3);
        graph[t1].push_back({t2,t3});
    }
    
    for(int i=1; i<=V; i++){
        d[i] = INF;
    }
    
    priority_queue<pair<int, int>> pq;
    pq.push({0,K});
    d[K] = 0;
    
    while(!pq.empty()){
        int wei = -pq.top().first;
        int cur = pq.top().second;
        pq.pop();
        
        for(int i=0; i<graph[cur].size(); i++){
            int n = graph[cur][i].first;
            int nwei = graph[cur][i].second;
            
            if(d[n] > wei + nwei){
                d[n] = wei + nwei;
                pq.push({-d[n], n});
            }
        }
    }
    
    for(int i=1; i<=V; i++){
        if(d[i] == INF)
            printf("INF\n");
        else
            printf("%d\n", d[i]);
    }
}
