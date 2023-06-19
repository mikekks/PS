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
int visited[100001];
int longest;
int longest_node;
vector<pair<int, int>> map[100001];

void dfs(int n, int w){
    
    if(visited[n] != 0)
        return;
    
    visited[n] = 1;
    
    if(longest < w){
        longest = w;
        longest_node = n;
    }
    
    for(int i=0; i<map[n].size(); i++)
        dfs(map[n][i].first, w+map[n][i].second);
}

int main(void)
{
    scanf("%d", &N);
    
    for(int i=1; i<=N; i++){
        int tmp = 1;
        scanf("%d", &tmp);
        
        while(1)
        {
            int node2, cost;
            scanf("%d", &node2);
            if(node2 == -1)
                break;
            
            scanf("%d", &cost);
            map[tmp].push_back({node2, cost});
        }
        
    }
    
    dfs(1, 0);
    memset(visited, '\0', sizeof(visited));
    
    dfs(longest_node, 0);
    
    printf("%d\n", longest);

}
