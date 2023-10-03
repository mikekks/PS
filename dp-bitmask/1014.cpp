#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>
#include <map>


#define MAX 15
#define INF 1

using namespace std;

typedef long long ll;

int T;
int N, M;
int Map[MAX][MAX];
int dp[MAX][1024];

int ans;

bool row_check(int n){
    for(int i=0; i<M-1; i++){
        int tmp = (3<<i);
        if((n & tmp) == tmp) return false;
    }
    return true;
}

bool brokencheck(int y, int cur){
    
    for(int i=1; i<=M; i++){
        if(Map[y][i] == 0 && (cur & (1 << (M-i)))) return false;
    }
    return true;
}

int countAns(int n){
    int cnt = 0;
    while(n != 0){
        if(n%2 == 1)
            cnt++;
        n = n >> 1;
        
    }
    return cnt;
}

bool prevCheck(int cur, int prev){
    for(int i=0; i<M; i++){
        if((1 << i) & prev){
            if(i>0 && ((1 << (i-1)) & cur)) return false;
            if ((1 << (i+1) & cur)) return false;
        }
    }
    return true;
}

void solve(){
    
    vector<pair<int, int>> v;
    
    vector<pair<int, int>> v2;

   
    for(int i=0; i<(1<<M); i++){
        if(row_check(i)){
            int cnt = countAns(i);
            v.push_back({i, cnt});
        }
    }

    
    for(int i=1; i<=N; i++){
        
        for(pair<int, int> cur : v){
            if(!brokencheck(i, cur.first)) {
                continue;
            }
            
            for(pair<int, int> prev : v){
                if(prevCheck(cur.first, prev.first)){
                    dp[i][cur.first] = max(dp[i][cur.first], dp[i-1][prev.first] + cur.second);
                    ans = max(ans, dp[i][cur.first]);
                }
            }
        }
    }
    
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> T;
    
    
    for(int i=0; i<T; i++){
        memset(Map, '\0', sizeof(Map));
        memset(dp, '\0', sizeof(dp));

        ans = 0;
        cin >> N >> M;
        
        for(int i=1; i<=N; i++){
            string tmp;
            cin >> tmp;
            for(int j=0; j<M; j++){
                if(tmp[j] == '.'){
                    Map[i][j+1] = 1;
                }
                else{
                    Map[i][j+1] = 0;
                }
            }
        }
        
        solve();
        cout << ans << '\n';
    }
    
    
    
    
    return 0;
}
