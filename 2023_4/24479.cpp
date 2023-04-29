#include <iostream>
#include <cstdio>
#include <list>
#include <set>
#include <vector>
#include <algorithm>

using namespace std;

int visited[100001] = {0, };
vector<int> map[100001];
int result[100001] = {0, };
int cnt = 0;

void dfs(int r, int N){
    visited[r] = 1;
    cnt++;
    result[r] = cnt;
    
    for(int i=0; i<map[r].size(); i++){
        int tmp = map[r][i];
            if(visited[tmp] != 1)
                dfs(tmp, N);
        
    }
}


int main() {
    int N,M,R;
    
    scanf("%d %d %d", &N, &M, &R);
    
    
    for(int i=1; i<=M; i++){
        int x,y;
        scanf("%d %d", &x, &y);
        map[x].push_back(y);
        map[y].push_back(x);
    }
    
    for (int i = 1; i <= N; i++) {
		sort(map[i].begin(), map[i].end());
	}
    
    
    dfs(R, N);
    
    for(int i=1; i<=N; i++){
        printf("%d\n", result[i]);
    }
    
}
