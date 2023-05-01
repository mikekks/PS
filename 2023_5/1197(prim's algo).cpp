#include <iostream>
#include <queue>

#define MAX 10001

using namespace std;

int V,E;
vector<pair<int, int>> W[MAX];
long long total;

priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
int visited[MAX];

int main(){
    scanf("%d %d", &V, &E);
    
    for(int i=0; i<E; i++){
        int t1 = 0;
        int t2 = 0;
        int wei = 0;
        scanf("%d %d %d", &t1, &t2, &wei);
        W[t1].push_back({t2, wei});
        W[t2].push_back({t1, wei});

    }
    
    pq.push({0, 1});
    
    while(!pq.empty()){
        int tw = pq.top().first;
        int tn = pq.top().second;
        
        pq.pop();
        
        if(visited[tn] == 1) continue;
        
        visited[tn] = 1;
        total += tw;
        
        for (int i = 0; i < W[tn].size(); i++)
        {
            int nn = W[tn][i].first;
            int nw = W[tn][i].second;

            pq.push(make_pair(nw, nn));
        }
    }
    
    
    
    printf("%lld\n", total);
}
