#include <iostream>
#include <queue>

#define MAX 100001
#define INF 987654321
using namespace::std;

int N, E;
int u, v;
int D[801];
vector<pair<int, int>> Map[801];

int dijk(int start, int end){
    
    
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> q;
    
    q.push({0, start});
    
    for(int i=1; i<=N; i++) D[i] = -1;
    D[start] = 0;
    
    while(!q.empty()){
        int W = q.top().first;
        int cur = q.top().second;
        q.pop();
        
        if(D[end] <= W && D[end] != -1){
            continue;
        }
        
        for(int i=0; i<Map[cur].size(); i++){
            int next = Map[cur][i].first;
            int tmp_w = Map[cur][i].second;
            
            if(D[next] > W+tmp_w || D[next] == -1){
                D[next] = W+tmp_w;
                q.push({W+tmp_w, next});
            }
        }
    }
    
    return D[end];
}

int main(){
    scanf("%d %d", &N, &E);
    
    for(int i=0; i<E; i++){
        int a,b,c;
        scanf("%d %d %d", &a, &b, &c);
        Map[a].push_back({b, c});
        Map[b].push_back({a, c});
    }
    
    scanf("%d %d", &u, &v);
    
    int tmp1[3] = {dijk(1, u), dijk(u, v), dijk(v, N)};
    int t1 = 0;
    int tmp2[3] = {dijk(1, v), dijk(v, u), dijk(u, N)};
    int t2 = 0;
    bool check1 = true;
    bool check2 = true;
    
    for(int i=0; i<3; i++){
        if(tmp1[i] != -1){
            t1 += tmp1[i];
        }
        else{
            check1 = false;
        }
        if(tmp2[i] != -1){
            t2 += tmp2[i];
        }
        else{
            check2 = false;
        }
    }
    
    if(!check1 && !check2){
        printf("-1\n");
        return 0;
    }
    
    if(t1 >= t2){
        printf("%d\n", t2);
    }
    else{
        printf("%d\n", t1);
    }
    
}
