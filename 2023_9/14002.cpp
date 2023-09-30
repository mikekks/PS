#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>
#include <map>


#define MAX 1005

using namespace std;

typedef long long ll;

int N, M;

vector<int> arr;
vector<int> ans_v;

int dp[MAX];
int tmp;
int idx;

ll ans;

void solve(){
    
    for(int i=0; i<N; i++){
        int cur = arr[i];
        int cnt = 0;
        for(int j=0; j<i; j++){
            if(arr[j] < cur){
                cnt = max(cnt, dp[j]);
            }
        }
        dp[i] = cnt + 1;
        
        if(tmp < dp[i]){
            tmp = dp[i];
            idx = i;
        }
    }
    
    for(int i=idx; i>=0; i--){
        if(dp[i] == tmp){
            ans_v.push_back(arr[i]);
            tmp--;
        }
    }
    
    
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N;
    
    for(int i=0; i<N; i++){
        int input;
        cin >> input;
        arr.push_back(input);
    }
    
    solve();
    
    cout << ans_v.size() << '\n';
    
    for(int i=ans_v.size()-1; i>=0; i--){
        cout << ans_v[i] << " ";
    }
    cout << '\n';
    
    return 0;
}
