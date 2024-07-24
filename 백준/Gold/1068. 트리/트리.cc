#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 55
#define INF 987654321

using namespace std;

// 21:20 시작

int N;
int del;
int root;
int ans = 0;

vector<int> p[MAX];  // 벡터는 자식들의 list

void dfs(int cur){
    if(cur == del){
        return;
    }
    
    if(p[cur].size() == 0){
        ans++;
        return;
    }
    
    if(p[cur].size() == 1 && p[cur][0] == del){
        ans++;
        return;
    }
    
    for(int i=0; i<p[cur].size(); i++){
        dfs(p[cur][i]);
    }
}

int main() {
//    ios::sync_with_stdio(false);
//    cin.tie(0);
//    cout.tie(0);
    
    cin >> N;
    
    for(int i=0; i<N; i++){
        int tmp;
        cin >> tmp;
        
        if(tmp == -1){
            root = i;
            continue;
        }
        p[tmp].push_back(i);
    }
    
    cin >> del;
    
    dfs(root);
    
    cout << ans << '\n';
}
