#include <iostream>
#include <cstring>
#include <queue>
#include <algorithm>
#include <vector>

#define MAX 100001

using namespace std;

int N;
int M;
int p[MAX][25];

int d[MAX];
int visited[MAX];

vector<vector<int>> Map;

void init_lca(){
    
    for(int i=1; i<=21; i++){
        for(int j=1; j<=N; j++){
            p[j][i] = p[p[j][i-1]][i-1];
        }
    }
}

int lca(int a, int b){
    if(d[b]>d[a]) swap(a,b);
    
    for(int i=20; i>=0; i--){
        if(d[a]-d[b] >= (1 << i))
            a = p[a][i];
    }
    
    if(a == b) return a;
    
    for(int i=19; i>=0; i--){
        if(p[a][i] != p[b][i]){
            a = p[a][i];
            b = p[b][i];
        }
    }
    
    return p[a][0];
}

int main(int argc, char** argv)
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N;
    
    Map.resize(N+3);
    
    for(int i=0; i<N-1; i++){
        int t1,t2;
        cin >> t1 >> t2;
        Map[t1].push_back(t2);
        Map[t2].push_back(t1);
    }
    
    queue<pair<int, int>> iq;
    visited[1] = 1;
    iq.push({1, 0});
    
    
    while(!iq.empty()){
        int cur = iq.front().first;
        int depth = iq.front().second;
        iq.pop();
        
        d[cur] = depth;
        
        for(int i=0; i<Map[cur].size(); i++){
            int next = Map[cur][i];
            if(visited[next] == 0){
                visited[next] = 1;
                p[next][0] = cur;
                iq.push({next, depth+1});
            }
        }
    }
    //memset(visited, '\0', sizeof(visited));
    
    init_lca();
    
    cin >> M;
    
    for(int i=0; i<M; i++){
        int v1,v2;
        cin >> v1 >> v2;
        int root = lca(v1,v2);
        cout << root << '\n';
    }
    
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}
