#include <iostream>
#include <string>
#include <sstream>
#include <cmath>
#include <vector>
#include <algorithm>
#include <queue>
#include <cstring>

using namespace std;

#define MAX 25

int map[MAX][MAX];
pair<int, int> guest[MAX][MAX];
bool isVisit[MAX][MAX];

int N, M;
int total = 0;
int ans = 987654321;
int fuel = 0;

int ud[4] = {-1,0,0,1};
int lr[4] = {0,-1,1,0};

struct Taxi{
    int y;
    int x;
};


Taxi taxi;

struct INFO{
    int y;
    int x;
    int d;
};

bool compareINFO(const INFO& a, const INFO& b) {
    if (a.d != b.d)
        return a.d < b.d;
    if (a.y != b.y)
        return a.y < b.y;
    return a.x < b.x;
}


pair<int, int> findNext(){
    memset(isVisit, false, sizeof(isVisit));
    
    queue<pair<pair<int, int>, pair<int, int>>> q;
    q.push({{taxi.y, taxi.x}, {0, fuel}});
    isVisit[taxi.y][taxi.x] = true;
    
    vector<INFO> v;
    
    while (!q.empty()) {
        int cy = q.front().first.first;
        int cx = q.front().first.second;
        int d = q.front().second.first;
        int cur_fuel = q.front().second.second;
        q.pop();
        
        if(guest[cy][cx].first != 0 && guest[cy][cx].second != 0){
            v.push_back({cy,cx,d});
        }
        
        if(cur_fuel < 0) continue;
        
        for(int i=0; i<4; i++){
            int ty = cy+ud[i];
            int tx = cx+lr[i];
            
            if(ty < 1 || tx < 1 || ty > N || tx > N) continue;
            
            if(map[ty][tx] == 1 || isVisit[ty][tx]) continue;
            
            q.push({{ty, tx}, {d+1, cur_fuel-1}});
            isVisit[ty][tx] = true;
        }
        
    }
    
    if(v.size() == 0) return {0,0};
    
    sort(v.begin(), v.end(), compareINFO);
    
    fuel -= v[0].d;
    return {v[0].y, v[0].x};
}

bool move(int target_y, int target_x){
    memset(isVisit, false, sizeof(isVisit));
    
    int dst_y = guest[target_y][target_x].first;
    int dst_x = guest[target_y][target_x].second;
    
    queue<pair<pair<int, int>, pair<int, int>>> q;
    q.push({{target_y, target_x}, {0, fuel}});
    
    while (!q.empty()) {
        int cy = q.front().first.first;
        int cx = q.front().first.second;
        int d = q.front().second.first;
        int cur_fuel = q.front().second.second;
        q.pop();
        
        if(cur_fuel < 0) continue;
        
        if(cy == dst_y && cx == dst_x){
            fuel -= d;
            fuel += d*2;
            taxi.y = cy;
            taxi.x = cx;
            
            guest[target_y][target_x].first = 0;
            guest[target_y][target_x].second = 0;
            total--;
            return true;
        }
        
        for(int i=0; i<4; i++){
            int ty = cy+ud[i];
            int tx = cx+lr[i];
            
            if(ty < 1 || tx < 1 || ty > N || tx > N) continue;
            
            if(map[ty][tx] == 0 && !isVisit[ty][tx]){
                isVisit[ty][tx] = true;
                q.push({{ty, tx}, {d+1, cur_fuel-1}});
            }
        }
    }
    
    return false;
}

int main() {

    cin >> N >> M >> fuel;
    total = M;
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            cin >> map[i][j];
        }
    }
    int a,b;
    cin >> a >> b;
    taxi.y = a;
    taxi.x = b;
    
    for(int i=0; i<M; i++){
        int sy,sx,ey,ex;
        cin >> sy >> sx >> ey >> ex;
        guest[sy][sx].first = ey;
        guest[sy][sx].second = ex;
    }
    
    while(total > 0){
        pair<int, int> next = findNext();
        if(next.first == 0 && next.second == 0){
            cout << -1 << '\n';
            return 0;
        }
        
        bool success = move(next.first, next.second);
        if(!success){
            cout << -1 << '\n';
            return 0;
        }
    }
    
    cout << fuel << '\n';
    return 0;
}


