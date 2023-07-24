#include <iostream>
#include <cstring>
#include <queue>
#include <algorithm>
#include <vector>

using namespace std;

long long result;
int N;
int p[100001][25];
int d[100001];
int visited[100001];
vector<vector<int>> c;


void findRoot(){
    for(int i=1; i<=20; i++){
        for(int j=1; j<=N; j++){
            p[j][i] = p[p[j][i-1]][i-1];
        }
    }
}


int movePos(int x, int y){
    if(d[y]>d[x]) swap(x,y);
    
    for(int i=19; i>=0; i--){  // depth 맞춰주기
            if((d[x]-d[y])>=(1<<i))
                x = p[x][i];
    }
    
    if(x==y) return x;  // 부모-자식인 경우
    
    for(int i=19;i>=0;i--){  // depth 맞춰줬으니까 동시에 서로 올라가면서 찾기
        if(p[x][i] != p[y][i]){
            x = p[x][i];
            y = p[y][i];
        }
    }
    
    return p[x][0];
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
        
        memset(d,0,sizeof(d));
        memset(p,0,sizeof(p));
        memset(visited, '\0', sizeof(visited));
        c.clear();
        
        cin >> N;
        result = 0;
        c.resize(N+5);
        
        for(int i=2; i<=N; i++){
            int tmp;
            cin >> tmp;
            //p[i][0] = tmp;
            c[tmp].push_back(i);
        }
        
        for(int i=1; i<=N; i++) sort(c[i].begin(), c[i].end());
        
        queue<pair<int, int>> q;
        q.push({1, 0});
        visited[1] = 1;
        
        while(!q.empty()){  // depth 먼저 구하기
            int cur = q.front().first;
            int depth = q.front().second;
            q.pop();
            d[cur] = depth;
            
            for(int i=0; i<c[cur].size(); i++){
                int next = c[cur][i];
                if(visited[next] == 0){
                    visited[next] = 1;
                    p[next][0] = cur;  // 바로 위 부모
                    q.push({next, depth+1});
                }
            }
        }
        
        
        findRoot();
        
        memset(visited, '\0', sizeof(visited));
        vector<int> Order;
        queue<int> nq;
        nq.push(1);
        visited[1] =  1;
        
        while(!nq.empty()){
            int cur = nq.front();
            nq.pop();
            
            Order.push_back(cur);
            for(int i=0; i<c[cur].size(); i++){
                int next = c[cur][i];
                if(visited[next] == 0){
                    visited[next] = 1;
                    nq.push(next);
                }
            }
            
        }
        
        for(int i=0; i<N-1; i++){
            int _root = movePos(Order[i], Order[i+1]);
            long long a = d[Order[i]] - d[_root];
            long long b = d[Order[i+1]] - d[_root];
            result += a + b;
        }
        
        
        cout << "#" << test_case << " " << result << '\n';
    }
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}
