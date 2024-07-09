#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 100005

using namespace std;
int N, K;
int map[MAX][2];

vector<int> ans;

int main() {
    cin >> N >> K;
    
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> q;
    q.push({0, N});
    
    for(int i=0; i<MAX; i++){
        map[i][0] = 987654321;
    }
    
    while (!q.empty()) {
        int cost = q.top().first;
        int d = q.top().second;
        q.pop();
        
        if(d == K){
            cout << cost << '\n';
            
            int cur = K;
            
            while(cur != N){
                ans.push_back(cur);
                cur = map[cur][1];
            }
            
            ans.push_back(N);
            
            break;
        }
        
        if(d-1 >= 0 && cost < map[d-1][0]){
            q.push({cost+1, d-1});
            map[d-1][0] = cost+1;
            map[d-1][1] = d;
        }
        
        if(d+1 <= 100000 && cost < map[d+1][0]){
            q.push({cost+1, d+1});
            map[d+1][0] = cost+1;
            map[d+1][1] = d;
        }
        
        if(2*d <= 100000 && cost < map[2*d][0]){
            q.push({cost+1, 2*d});
            map[2*d][0] = cost+1;
            map[2*d][1] = d;
        }
    }
    
    for(int i=ans.size()-1; i>=0; i--){
        cout << ans[i] << " ";
    }
    
}
