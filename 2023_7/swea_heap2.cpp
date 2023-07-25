#include <iostream>
#include <queue>
#include <vector>
#include <cstring>

using namespace std;
int N;
int Map[101][101];
int dijk[101][101];

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};

int best_first(){
    
    int result = 987654321;
    
    priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<>> q;
    
    q.push({0, {1,1}});
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            dijk[i][j] = 987654321;
        }
    }
    
    dijk[1][1] = 0;
    
    while(!q.empty()){
        int cy = q.top().second.first;
        int cx = q.top().second.second;
        int cost = q.top().first;
        q.pop();
        
        
        
        if(cost >= result)
            continue;
        
        if(cy == N && cx == N){
            result = cost;
        }
        
        for(int i=0; i<4; i++){
            int ty = cy+ud[i];
            int tx = cx+lr[i];
            
            if(ty < 1 || ty > N || tx < 1 || tx > N)
                continue;
            
            int next_cost = cost + Map[ty][tx];
            if(next_cost < result){
                if(dijk[ty][tx] > next_cost){
                    dijk[ty][tx] = next_cost;
                    q.push({next_cost, {ty, tx}});
                }
            }
            
        }
        
    }
    
    return result;
}

int main(int argc, char** argv)
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    int test_case;
    int T;

    //freopen("input.txt", "r", stdin);
    cin>>T;
    /*
       여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
    */
    for(test_case = 1; test_case <= T; ++test_case)
    {
        memset(Map, '\0', sizeof(Map));
        cin >> N;
        
        for(int i=1; i<=N; i++){
            string tmp;
            cin >> tmp;
            for(int j=1; j<=N; j++){
                Map[i][j] = tmp[j-1] - 48;
            }
        }
        
        
        int result = best_first();
        
        cout << "#" << test_case << " " << result << '\n';

    }
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}
