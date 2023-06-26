#include <iostream>
#include <queue>
#include <list>
#include <climits>
#include <algorithm>
#include <set>
#include <cstring>

#define INF 98765432;
using namespace std;

int N,M;
int city[51][51];
int length[51][51];
int visited[51][51];
int result = INF;
vector<pair<int, int>> chick, house;


struct node{
    int y;
    int x;
    int dis;
};


int Update(){
    
    int sum = 0;
    
    for(int i=0; i<house.size(); i++){
        int ny = house[i].first;
        int nx = house[i].second;
        int d = INF;
        
        for(int j=0; j<chick.size(); j++){
            int ty = chick[j].first;
            int tx = chick[j].second;
            
            int distance = abs(ty - ny) + abs(tx - nx);
            
            if(distance < d){
                d = distance;
            }
        }
        sum += d;
    }
    
    return sum;
}

void dfs(int y, int x, int cnt){
    
    if(M == cnt){
        int tmp = Update();
        if(tmp < result)
            result = tmp;
    }
    
    
    for(int i=y; i<=N; i++){
        for(int j=1; j<=N; j++){
            if(city[i][j] == 2 && visited[i][j] == 0){
                visited[i][j] = 1;
                chick.push_back({i, j});
                dfs(i, j, cnt+1);
                chick.pop_back();
                visited[i][j] = 0;
            }
        }
    }
    
}

int main(){
    scanf("%d %d", &N, &M);

    memset(length, CHAR_MAX, sizeof(length));
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            scanf("%d", &city[i][j]);
            
            if(city[i][j] == 1) house.push_back({i, j});
            //if(city[i][j] == 2) chick.push_back({i, j});
        }
    }
    
    dfs(0,0,0);
    
    
    printf("%d\n", result);
}
