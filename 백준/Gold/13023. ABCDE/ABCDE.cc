#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 2005

using namespace std;
int N, M;

bool isVisit[MAX];

vector<int> map[MAX];
bool isSuccess;

void dfs(int cur, int cnt){
    
    if(cnt == 5){
        isSuccess = true;
        return;
    }
    
    for(int i=0; i<map[cur].size(); i++){
        int next = map[cur][i];
        if(!isVisit[next]){
            isVisit[next] = true;
            dfs(next, cnt+1);
            isVisit[next] = false;
        }
        
        if(isSuccess) return;
    }
}

int main() {
    cin >> N >> M;
    
    for(int i=0; i<M; i++){
        int a,b;
        cin >> a >> b;
        map[a].push_back(b);
        map[b].push_back(a);
    }
    
    for(int i=0; i<N; i++){
        dfs(i, 0);
    }
    
    if(isSuccess) cout << 1 << '\n';
    else cout << 0 << '\n';
}
