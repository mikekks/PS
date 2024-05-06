#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>
#include <list>
#include <sstream>
#include <cmath>

#define MAX 15

using namespace std;

int N;
int mid;
int area[MAX];
bool map[MAX][MAX];
int visited[MAX];
int answer = 987654321;

bool check_connect(vector<int> list, bool divide){
    
    bool tmp_visit[MAX];
    memset(tmp_visit, false, sizeof(tmp_visit));
    
    queue<int> q;
    q.push(list[0]);
    tmp_visit[list[0]] = true;
    int cnt = 1;
    
    while (!q.empty()) {
        int cur = q.front();
        q.pop();
        
        for(int i=1; i<=N; i++){
            if(map[cur][i] && tmp_visit[i] == false && visited[i] == divide){
                tmp_visit[i] = true;
                q.push(i);
                cnt++;
            }
        }
    }
    
    if(cnt != list.size()) return false;
    
    return true;
    
}

bool check(){
    vector<int> a,b;
    
    for(int i=1; i<=N; i++){
        if(visited[i]) a.push_back(i);
        else b.push_back(i);
    }
    
    if(a.size() == 0 || b.size() == 0) return false;
    
    if(!check_connect(a, true)){
        return false;
    }
    
    if(!check_connect(b, false)){
        return false;
    }
    
    return true;
    
}

void cal(){
    int a = 0, b = 0;
    
    for(int i=1; i<=N; i++){
        if(visited[i]) a += area[i];
        else b += area[i];
    }
    
    int diff = abs(a-b);
    
    answer = diff < answer ? diff : answer;
}

void dfs(int cur, int cnt){
    if(cnt >= 1){
        if(check()){
            cal();
        }
    }
    
    for(int i=cur; i<=N; i++){
        if(visited[i]) continue;
        visited[i] = true;
        dfs(i, cnt+1);
        visited[i] = false;
    }
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N;
    for(int i=1; i<=N; i++){
        cin >> area[i];
    }
    
    for(int i=1; i<=N; i++){
        int cnt;
        cin >> cnt;
        for(int j=0; j<cnt; j++){
            int p;
            cin >> p;
            map[i][p] = true;
            map[p][i] = true;
        }
    }
    
    dfs(1, 0);
    
    if(answer == 987654321){
        cout << -1 << '\n';
    }
    else{
        cout << answer << '\n';
    }
    
   
}
