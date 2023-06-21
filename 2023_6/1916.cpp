#include <iostream>
#include <algorithm>
#include <cstring>
#include <cmath>
#include <queue>
#include <climits>
#include <vector>

#define INF INT_MAX
#define MAX 1001
using namespace std;

int N, M;
vector<pair<int, int>> bus[100001];
int Min;
int Price[100001];

void bfs(int start, int end){
    
    fill(Price, Price + 100001, INF);
    priority_queue<pair<int, int>, vector<pair<int, int> >, greater<pair<int, int> > > pq;

    for(int i=0; i<bus[start].size(); i++){
        pq.push({bus[start][i].first, bus[start][i].second});
    }
    
    while(!pq.empty()){
        int Cur = pq.top().first;
        int Curcost = pq.top().second;
        pq.pop();
        
        if(Price[Cur] > Curcost){
            Price[Cur] = Curcost;
        }
        else{
            continue;
        }
        
        for(int i=0; i<bus[Cur].size(); i++){
            int t_cost = Curcost + bus[Cur][i].second;
            pq.push({bus[Cur][i].first, t_cost});
        }
        
        
    }
    
}

int main(void)
{
    scanf("%d %d", &N, &M);
    
    for(int i=0; i<M; i++){
        int s, e, w;
        scanf("%d %d %d", &s, &e, &w);
        bus[s].push_back({e, w});
    }
    
    int st, ed;
    
    scanf("%d %d", &st, &ed);
    
    bfs(st, ed);
    printf("%d\n", Price[ed]);

        
}
