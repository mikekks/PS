#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>

#define MAX 32001
#define INF 987654321

using namespace::std;
int N, M;

vector<int> After[MAX];
vector<int> Before[MAX];
int in_degree[MAX];
int out_degree[MAX];

int visited[MAX];
vector<int> sol;
queue<int> q;

int main(){
    
    scanf("%d %d", &N, &M);
    
    for(int i=0; i<M; i++){
        int c1,c2;
        scanf("%d %d", &c1, &c2);
        After[c1].push_back(c2);
        Before[c2].push_back(c1);
        in_degree[c2]++;
        out_degree[c1]++;
        
    }
    
    for(int i=1; i<=N; i++){
        if(in_degree[i] == 0)
            q.push(i);
    }
    
    
    while(!q.empty()){
        int cur = q.front();
        visited[cur] = 1;
        q.pop();
        sol.push_back(cur);
        
        for(int i=0; i<After[cur].size(); i++){
            int next = After[cur][i];
            in_degree[next]--;
            
            if(in_degree[next] == 0)
                q.push(next);
        }
    }
    
    for(int i=0; i<sol.size(); i++){
        printf("%d ", sol[i]);
    }
    
    for(int i=1; i<=N; i++){
        if(visited[i] == 0)
            printf("%d ", i);
    }
    
    return 0;
}
