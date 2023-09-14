#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>

using namespace std;

typedef long long ll;

int N;
vector<vector<ll>> arr;
ll total;


ll getMax(vector<vector<ll>> board)
{
    ll res = 0;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            res = max(res, board[i][j]);
        }
    }
    return res;
}


// 왼쪽 이동
vector<vector<ll>> dfs_l(vector<vector<ll>> cMap){
    vector<vector<bool>> tMap;
    
    tMap.resize(N);
    for(int i=0; i<N; i++) tMap[i].resize(N, false);
    
    
    for(int i=0; i<N; i++){
        for(int j=1; j<N; j++){
            if(cMap[i][j] == 0) continue;
            
            for(int k=j-1; k>=0; k--){
                if(cMap[i][k] == cMap[i][k+1] && !tMap[i][k]){
                    cMap[i][k] *= 2;
                    cMap[i][k+1] = 0;
                    tMap[i][k] = true;
                    break;
                }
                else if(cMap[i][k] == 0){
                    cMap[i][k] = cMap[i][k+1];
                    cMap[i][k+1] = 0;
                }
                else{
                    break;
                }
            }
            
            
        }
        
    }
    
    return cMap;
}

// 오른쪽 이동
vector<vector<ll>> dfs_r(vector<vector<ll>> cMap){
    vector<vector<bool>> tMap;
    
    tMap.resize(N);
    for(int i=0; i<N; i++) tMap[i].resize(N, false);
    
    
    for(int i=0; i<N; i++){
        for(int j=N-2; j>=0; j--){
            if(cMap[i][j] == 0) continue;
            
            for(int k=j+1; k<N; k++){
                if(cMap[i][k] == cMap[i][k-1] && !tMap[i][k]){
                    cMap[i][k] *= 2;
                    cMap[i][k-1] = 0;
                    tMap[i][k] = true;
                    break;
                }
                else if(cMap[i][k] == 0){
                    cMap[i][k] = cMap[i][k-1];
                    cMap[i][k-1] = 0;
                }
                else{
                    break;
                }
            }
            
            
        }
        
    }
    
    return cMap;
}


vector<vector<ll>> dfs_d(vector<vector<ll>> cMap){
    vector<vector<bool>> tMap;
    
    tMap.resize(N);
    for(int i=0; i<N; i++) tMap[i].resize(N, false);
    
    
    for(int j=0; j<N; j++){
        for(int i=N-2; i>=0; i--){
            if(cMap[i][j] == 0) continue;
            
            for(int k=i+1; k<N; k++){
                if(cMap[k][j] == cMap[k-1][j] && !tMap[k][j]){
                    cMap[k][j] = 2*cMap[k][j];
                    cMap[k-1][j] = 0;
                    tMap[k][j] = true;
                    break;
                }
                else if(cMap[k][j] == 0){
                    cMap[k][j] = cMap[k-1][j];
                    cMap[k-1][j] = 0;
                }
                else{
                    break;
                }
            }
            
            
        }
        
    }
    
    return cMap;
}

vector<vector<ll>> dfs_u(vector<vector<ll>> cMap){
    vector<vector<bool>> tMap;
    
    tMap.resize(N);
    for(int i=0; i<N; i++) tMap[i].resize(N, false);
    
    
    for(int j=0; j<N; j++){
        for(int i=1; i<N; i++){
            if(cMap[i][j] == 0) continue;
            
            for(int k=i-1; k>=0; k--){
                if(cMap[k][j] == cMap[k+1][j] && !tMap[k][j]){
                    cMap[k][j] = 2*cMap[k][j];
                    cMap[k+1][j] = 0;
                    tMap[k][j] = true;
                    break;
                }
                else if(cMap[k][j] == 0){
                    cMap[k][j] = cMap[k+1][j];
                    cMap[k+1][j] = 0;
                }
                else{
                    break;
                }
            }
            
            
        }
        
    }
    
    return cMap;
}


void dfs(int cnt, vector<vector<ll>> Map){
    total = max(total, getMax(Map));
    
    if(cnt == 5) return;
    
    dfs(cnt+1, dfs_u(Map));
    dfs(cnt+1, dfs_d(Map));
    dfs(cnt+1, dfs_l(Map));
    dfs(cnt+1, dfs_r(Map));
    
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N;
    
    arr.resize(N);
    for(int i=0; i<N; i++) arr[i].resize(N);
    
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            cin >> arr[i][j];
        }
    }
    
    dfs(0, arr);
    
    cout << total << '\n';
    
    return 0;
}
