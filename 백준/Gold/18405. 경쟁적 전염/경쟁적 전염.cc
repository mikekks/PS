#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>
#include <vector>
#include <algorithm>
#include <cstring>
#include <queue>
#include <stack>

#define MAX 205
#define INF 987654321

using namespace std;

// 13: 00 ~


int N, K;
int s, ey, ex;
int ans = 0;
int map[MAX][MAX];
vector<pair<int, pair<int, int>>> arr;

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};

int main(void)
{
    cin >> N >> K;
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            cin >> map[i][j];
            
            if(map[i][j] != 0) arr.push_back({map[i][j], {i, j}});
        }
    }
    
    cin >> s >> ey >> ex;
    
    sort(arr.begin(), arr.end());
    int cur = 0;
    
    while(cur < s){
        int curSize = arr.size();
        for(int i=0; i<curSize; i++){
            int cy = arr[i].second.first;
            int cx = arr[i].second.second;
            int vNum = arr[i].first;
            
            for(int k=0; k<4; k++){
                int ny = cy+ud[k];
                int nx = cx+lr[k];
                
                if(map[ny][nx] != 0) continue;
                if(ny < 1 || nx < 1 || ny > N || nx > N) continue;
                
                map[ny][nx] = vNum;
                arr.push_back({vNum, {ny, nx}});
            }
        }
        
        if(map[ey][ex] != 0) break;
        cur++;
    }
    
    cout << map[ey][ex] << '\n';
    
    
    return 0;
}
