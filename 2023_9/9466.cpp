#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>

using namespace std;

int T, N;
int InOrder[100001];

int solve(){
    memset(InOrder, '\0', sizeof(InOrder));
    vector<int> v;
    cin >> N;
    v.resize(N+1);
    
    for(int i=1; i<=N; i++){
        cin >> v[i];
        InOrder[v[i]]++;
    }
    
    queue<int> q;
    
    for(int i=1; i<=N; i++){
        if(InOrder[i] == 0) q.push(i);
    }
    
    int ans = 0;
    while(!q.empty()){
        int cur = q.front();
        q.pop();
        ans++;
        
        InOrder[v[cur]]--;
        if(InOrder[v[cur]] == 0){
            q.push(v[cur]);
        }
    }
    
    return ans;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> T;
    
    for(int i=0; i<T; i++){
        int ret = solve();
        cout << ret << '\n';
    }
    
    
    return 0;
}
