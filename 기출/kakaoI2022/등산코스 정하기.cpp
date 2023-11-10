#include <string>
#include <vector>
#include <queue>
#include <cstring>
#include <algorithm>

#define INF 987654321

using namespace std;

int dist[50001];
int N;
bool isGate[50001];
bool isSummit[50001];
vector<pair<int, int>> Map[50001];

int ans = INF;
int peak = INF;

pair<int, int> dijk(int cur){
    // 현재 최대 등산로 cost, 현재 위치
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> q; 
    int tans = INF;
    int tpeak = INF;
    
    for(int i=0; i<50001; i++){
        dist[i] = INF;
    }
    
    dist[cur] = 0;
    q.push({0, cur});
    
    while(!q.empty()){
        int maxCost = q.top().first;
        int curNode = q.top().second;
        q.pop();
        
        if(dist[curNode] < maxCost) continue;
        if(tans < maxCost) continue;
        if(ans < dist[curNode]) continue;
        
        // 산봉우리 체크
        if(isSummit[curNode]){
            if(tans > maxCost){
                tans = maxCost;
                tpeak = min(curNode, tpeak);
            }
            continue;
        }
        
        for(int i=0; i<Map[curNode].size(); i++){
            int next = Map[curNode][i].first;
            int next_cost = Map[curNode][i].second;
            
            if(tans < max(maxCost, next_cost)) continue;
            
            // 출입문 체크
            if(isGate[next]) continue;
            
            if(dist[next] > max(maxCost, next_cost)){
                dist[next] = max(maxCost, next_cost);
                q.push({dist[next], next});
            }
        }
        
    }
    
    return {tpeak, tans};
}

vector<int> solution(int n, vector<vector<int>> paths, vector<int> gates, vector<int> summits) {
    vector<int> answer;
    N = n;

    
    for(int i=0; i<paths.size(); i++){
        int s = paths[i][0];
        int e = paths[i][1];
        int cost = paths[i][2];
        Map[s].push_back({e, cost});
        Map[e].push_back({s, cost});
    }
    
    for(int i=0; i<gates.size(); i++){
        isGate[gates[i]] = true;
    }
    
    for(int i=0; i<summits.size(); i++){
        isSummit[summits[i]] = true;
    }
    
    
    for(int i=0; i<gates.size(); i++){
        pair<int, int> tmp = dijk(gates[i]);
        if(ans > tmp.second){
            ans = tmp.second;
            peak = tmp.first;
        }else if(ans == tmp.second){
            peak = min(tmp.first, peak);
        }
    }
    
    answer.push_back(peak);
    answer.push_back(ans);
    return answer;  // 산봉우리 번호, intensity의 최솟값
}
