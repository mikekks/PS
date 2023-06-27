#include <iostream>
#include <queue>
#include <list>
#include <climits>
#include <algorithm>
#include <set>
#include <cstring>

#define INF 987654321;
using namespace std;

int n,m,r;
int Map[101][101];
int Item[101];
int total;
int D[101];

void bfs(int start){
    priority_queue<pair<int, int>> q;
   
    q.push({start, 0});
    int sum = 0;
    
    for(int i=1; i<=n; i++) D[i] = INF;
    D[start] = 0;
    
    while(!q.empty()){
        int cur = q.top().first;
        int w = -q.top().second;

        q.pop();

        for(int i=1; i<=n; i++){
            if(Map[cur][i] != 0){
                if(D[i] > w+Map[cur][i]){
                    D[i] = w+Map[cur][i];
                    q.push({i, -(w+Map[cur][i])});
                }
                
            }
        }
        
    }
    for(int i=1; i<=n; i++){
        if(D[i] <= m) sum += Item[i];
    }
    
    if(sum > total){
        total = sum;
    }
    
}

int main(){
    scanf("%d %d %d", &n, &m, &r);

    for(int i=1; i<=n; i++){
        scanf("%d", &Item[i]);
    }
    
    for(int i=0; i<r; i++){
        int t1,t2, w;
        scanf("%d %d %d", &t1, &t2, &w);
        Map[t1][t2] = w;
        Map[t2][t1] = w;

    }
    
    for(int i=1; i<=n; i++){
        bfs(i);
    }
    
    printf("%d\n", total);
}
