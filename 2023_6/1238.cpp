#include <iostream>
#include <algorithm>
#include <cstring>
#include <cmath>
#include <queue>
#include <climits>
#include <vector>

#define INF INT_MAX
using namespace std;

int N, M, X;
int result;

vector<pair<int, int>> map[1001];
vector<int> dist;
int resdist[1001];

void dijkstar(int S){
    
    dist.clear();
    dist.resize(N+1, INF);
    
    dist[S] = 0;
    
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> q;
    
    q.push({S, 0});

    while(!q.empty()){
        int tn = q.top().first;
        int tw = q.top().second;
        q.pop();
        
        if(tw > dist[tn]) continue;
        
       
        for(int i=0; i<map[tn].size(); i++){
            int next = map[tn][i].first;
            int nw = map[tn][i].second + tw;
            
            if(nw<dist[next]){
                dist[next] = nw;
                q.push({next, nw});
            }
        }
    }

}



int main(void)
{
    scanf("%d %d %d", &N ,&M, &X);
    
    for(int i=0; i<M; i++){
        int start = 0, end = 0, w = 0;
        scanf("%d %d %d", &start, &end, &w);
        map[start].push_back({end, w});
    }
    
    for(int i=1; i<=N; i++){
        dijkstar(i);
        resdist[i] = dist[X];
        
    }
    
    dijkstar(X);
    
    for(int i=1; i<=N; i++){
        resdist[i] += dist[i];
        if(result< resdist[i])
            result = resdist[i];
    }
    
    printf("%d\n", result);
   
}
