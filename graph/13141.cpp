#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>
#include <map>


#define INF 987654321

using namespace std;

typedef long long ll;

int T;
int N, M;
vector<pair<int, int>> Map[201];
int visited[201];  // 몇 초에 해당 정점을 방문했는지
int dist[201];

double dijk(int start){
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> q;  // 현재시간, 정점
    
    dist[start] = 0;
    visited[start] = 0;
    q.push({0, start});
    double ans = 0;
    
    for(int i=0; i<201; i++){
        visited[i] = -1;
        dist[i] = INF;
    }
    
    while (!q.empty()) {
        int cTime = q.top().first;
        int cNode = q.top().second;
        
        
        q.pop();
        
        if(visited[cNode] == -1){
            visited[cNode] = cTime;
        }
        
        for(int i=0; i<Map[cNode].size(); i++){
            int nNode = Map[cNode][i].first;
            int nTime = Map[cNode][i].second;
            
            if(visited[nNode] != -1){  // 방문한 적이 있는 정점이면

                double required_time = ((double)nTime - (double)(visited[cNode] - visited[nNode]))/2;
                
                if(required_time + visited[cNode] > ans){
                    ans = required_time + visited[cNode];
                }
                continue;
            }
            
            if(dist[nNode] > nTime + cTime){
                dist[nNode] = nTime + cTime;
                q.push({nTime + cTime, nNode});
            }
            
            
            
        }
    }
    return ans;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N >> M;
    
    for(int i=0; i<M; i++){
        int S,E,L;
        cin >> S >> E >> L;
        Map[S].push_back({E, L});
        Map[E].push_back({S, L});
    }
    
    double ans = INF;
    
    // 어디를 태울지?
    for(int i=1; i<=N; i++){
        double ret = dijk(i);
        if(ret < ans){
            ans = ret;
        }
    }
    
    cout << fixed;
    cout.precision(1);
    cout << ans << '\n';

    
    return 0;
}
