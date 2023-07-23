#include <iostream>
#include <cstring>
#include <queue>
#include <vector>

using namespace std;

int Map[301][301];
int result;
int N;

int ud[8] = {-1,-1,-1,0,0,1,1,1};
int lr[8] = {-1,0,1,-1,1,-1,0,1};
int visited[301][301];

vector<pair<int, int>> pro;

bool check(int y, int x){
    if(Map[y][x] == -1)
        return false;
    
    bool check = true;
    for(int i=0; i<9; i++){
        int ty = y+ud[i];
        int tx = x+lr[i];
        bool tmpcheck = false;
        
        if(ty < 1 || ty >N || tx < 1 || tx > N)
            continue;
        
        if(Map[ty][tx] == 0){
            tmpcheck = true;
        }
        check &= tmpcheck;
    }
    return check;
}

void bfs(int y, int x){
    
    if(Map[y][x] == -1 || visited[y][x] == 2)
        return;
    
    queue<pair<int, int>> q;
    
    q.push({y,x});
    visited[y][x] = 2;
    result++;
    
    while(!q.empty()){
        
        int cy = q.front().first;
        int cx = q.front().second;
        q.pop();
        
        if(Map[cy][cx] == -1)
            continue;
        
        bool tmpCheck = check(cy,cx);
        
        if(tmpCheck){
            for(int i=0; i<9; i++){
                int ty = cy+ud[i];
                int tx = cx+lr[i];
                
                if(ty < 1 || ty >N || tx < 1 || tx > N)
                    continue;
                
                if(visited[ty][tx] != 2){
                    visited[ty][tx] = 2;
                    q.push({ty,tx});
                }
            }
        }
    }
    
}

int main(int argc, char** argv)
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
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
        memset(visited, '\0', sizeof(visited));
        pro.clear();
        result = 0;

        cin >> N;
        
        for(int i=1; i<=N; i++){
            string s;
            cin >> s;
            for(int j=0; j<N; j++){
                if(s[j] == '*'){
                    Map[i][j+1] = -1;
                }
                else{
                    Map[i][j+1] = 0;
                }
                
            }
        }
        
        
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                bool tmp = check(i,j);
                if(tmp){
                    pro.push_back({i,j});
                }
            }
        }
        
        for(int i=0; i<pro.size(); i++){
            int ty = pro[i].first;
            int tx = pro[i].second;
            bfs(ty, tx);
        }
        
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                bfs(i,j);
            }
        }
        
        cout << "#" << test_case << " " << result << '\n';
    }
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}
