#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 505
#define INF 987654321

using namespace std;

int N, M;
vector<pair<long long, pair<int, int>>> map;   // cost, 출발도시, 도착도시
long long D[MAX];

bool bellman(int init){
    bool ret = true;
    
    D[init] = 0;
    
    for(int i=1; i<=N; i++){
        for(int j=0; j<map.size(); j++){
            int start = map[j].second.first;
            int end = map[j].second.second;
            long long cost = map[j].first;
            
            if(D[start] == INF) continue;
            
            if(D[start]+cost < D[end]){
                D[end] = D[start]+cost;
                
                if(i == N) ret = false;
            }
        }
    }
    
    
    return ret;
}

int main() {
    cin >> N >> M;
    
    for(int i=0; i<M; i++){
        long long a,b,cost;
        cin >> a >> b >> cost;
        
        map.push_back({cost, {a, b}});
    }
    
    
    for(int i=1; i<=N; i++) D[i] = INF;

    bool ret = bellman(1);
    
    
    if(!ret){
        cout << "-1\n";
        return 0;
    }
    else{
        for(int i=2; i<=N; i++){
            if(D[i] == INF){
                cout << "-1\n";
                continue;
            }
            cout << D[i] << '\n';
        }
    }
    
    
}
