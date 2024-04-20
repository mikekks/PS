#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <cmath>
#include <algorithm>
#include <cstring>
#include <stack>

#define MAX 105

using namespace std;

int N;
int ans = 987654321;

int map[MAX][MAX];

vector<pair<int, int>> group[10000];
int grpNum = 1;

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};
bool isVisit[MAX][MAX];

void bfs(int y, int x){
    queue<pair<int, int>> q;
    
    q.push({y,x});
    isVisit[y][x] = true;
    group[grpNum].push_back({y,x});
    
    while (!q.empty()) {
        int cy = q.front().first;
        int cx = q.front().second;
        q.pop();
        
        for(int i=0; i<4; i++){
            int ty = cy + ud[i];
            int tx = cx + lr[i];
            
            if(ty < 1 || tx < 1 || ty > N || tx > N){
                continue;
            }
            
            if(!isVisit[ty][tx] && map[ty][tx] == 1){
                isVisit[ty][tx] = true;
                group[grpNum].push_back({ty,tx});
                q.push({ty,tx});
            }
        }
    }
    
    grpNum++;
}

void check(int grpA, int grpB){
    
    for(int i=0; i<group[grpA].size(); i++){
        int ay = group[grpA][i].first;
        int ax = group[grpA][i].second;
        
        for(int j=0; j<group[grpB].size(); j++){
            int by = group[grpB][j].first;
            int bx = group[grpB][j].second;
            
            int d = abs(ay-by) + abs(ax-bx) - 1;
            if(d < ans){
                ans = d;
            }
        }
    }
}


int main(){
    cin >> N;
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            cin >> map[i][j];
        }
    }
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            if(map[i][j] == 1 && !isVisit[i][j]){
                bfs(i, j);
            }
        }
    }
    
    for(int i=1; i<grpNum; i++){
        for(int j=1; j<grpNum; j++){
            if(i == j) continue;
            
            check(i, j);
        }
    }
    
    cout << ans << '\n';

   
}
