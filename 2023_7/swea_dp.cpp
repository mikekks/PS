#include <iostream>
#include <algorithm>
#include <vector>
#include <cstring>

#define MAX 1005

using namespace std;

int result;
int dp[101][MAX];
int N, K;
vector<pair<int, int>> Order;

int solve(){
    for(int i=1; i<=N; i++){
        for(int j=1; j<=K; j++){
            int cur_cost = Order[i-1].second;
            int cur_value = Order[i-1].first;
            
            if(j - cur_cost >= 0){
                dp[i][j] = max(dp[i-1][j], dp[i-1][j-cur_cost] + cur_value);
            }
            else{
                dp[i][j] = dp[i-1][j];
            }
        }
    }
    
    return dp[N][K];
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
        memset(dp, '\0', sizeof(dp));
        result = 0;
        
        cin >> N >> K;
        
        for(int i=0; i<N; i++){
            int v, c;
            cin >> v >> c;
            Order.push_back({c,v});
        }
        
        result = solve();
    
        cout << "#" << test_case << " " << result << '\n';

    }
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}

