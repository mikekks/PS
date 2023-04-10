#include <iostream>
#include <vector>
#include <queue>
#define MAX 100001

using namespace std;

int n;
int visited[MAX];
int ans;
int node;

vector<pair<int, int>> tree[MAX];

void dfs(int v, int d){
    visited[v] = 1;
    
    if(d > ans){
        node = v;
        ans = d;
    }
    
    for(int i=0; i<tree[v].size(); i++){
        int tv = tree[v][i].first;
        int tw = tree[v][i].second + d;
        
        if(visited[tv] == 0){
            visited[tv] = 1;
            dfs(tv, tw);
            visited[tv] = 0;
        }
    }
}

int main(){
    scanf("%d", &n);
    
    for(int i=1; i<n; i++){
        int t1,t2,w;
        scanf("%d %d %d", &t1, &t2, &w);
        
        tree[t1].push_back({t2, w});
        tree[t2].push_back({t1, w});
    }
    
    
    dfs(1, 0);
    ans = 0;
    for(int i=1; i<=n; i++)
        visited[i] = 0;
    dfs(node, 0);
    
    printf("%d\n", ans);
}
