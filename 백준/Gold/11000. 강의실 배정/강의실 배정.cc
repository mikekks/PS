#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 200005

using namespace std;
int N;
int ans = 987654321;

vector<pair<int, int>> orders;


int main() {
    cin >> N;
    
    for(int i=0; i<N; i++){
        int s,t;
        cin >> s >> t;
        
        orders.push_back({s,t});
    }
    
    sort(orders.begin(), orders.end());
    
    priority_queue<int, vector<int>, greater<>> q;
    
    q.push(orders[0].second);
    
    for(int i=1; i<N; i++){
        int cur = orders[i].first;
        if(cur < q.top()){
            q.push(orders[i].second);
        }
        else {
            q.pop();
            q.push(orders[i].second);
        }
        
    }
    
    cout << q.size() << '\n';
}
