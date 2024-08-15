#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>
#include <vector>
#include <algorithm>
#include <cstring>
#include <queue>
#include <stack>

#define MAX 1500005
#define INF 987654321

using namespace std;

// 13: 00 ~

pair<int, int> schedule[MAX];  // 시간, 보상
int N;
int ans = 0;
int dp[MAX];

int main(void)
{
    cin >> N;
    
    for(int i=1; i<=N; i++){
        int t,p;
        cin >> t >> p;
        schedule[i] = {t, p};
    }
    
    int curMax = 0;
    
    for(int i=1; i<=N+1; i++){  // i-1번째에서 최댓값
        curMax = max(curMax, dp[i]);
        
        int prev = dp[i+ schedule[i].first];
        int cur = schedule[i].second + curMax;
        if(i+schedule[i].first > N+1) continue;
        

        
        dp[i+ schedule[i].first] = max(cur, prev);
        ans = dp[i+ schedule[i].first] > ans ? cur : ans;
        
    }
    
    
    cout << ans << '\n';
    
    
    return 0;
}
