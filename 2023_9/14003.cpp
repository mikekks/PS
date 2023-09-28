#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>
#include <map>


#define MAX 1000005

using namespace std;

typedef long long ll;

int N, M;

int cy, cx;
vector<ll> arr;
vector<ll> v;

vector<ll> v_ans;

ll v_idx[MAX];

ll ans;

void solve(){
    
    v.push_back(arr[0]);
    v_idx[0] = 1;

    ans++;
    
    for(int i=1; i<N; i++){
        if(v.back() < arr[i]){
            v.push_back(arr[i]);
            v_idx[i] = v.size();
            ans++;
        }
        else{
            int idx = lower_bound(v.begin(), v.end(), arr[i]) - v.begin();

            v[idx] = arr[i];
            v_idx[i] = idx+1;
        }
    }
    
    int cnt = v.size();
    
    for(int i=N-1; i>=0; i--){
        if(cnt == v_idx[i]){
            v_ans.push_back(arr[i]);
            cnt--;
        }
    }
    
    
    
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N;
    
    for(int i=0; i<N; i++){
        ll tmp;
        cin >> tmp;
        arr.push_back(tmp);
    }
    
    solve();
    
    cout << ans << '\n';
    
    for(int i=v_ans.size() - 1; i>=0; i--)
        cout << v_ans[i] << " ";
    cout << '\n';
    
    return 0;
}
