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

vector<ll> arr;
vector<ll> v;

ll ans;

void solve(){
    
    v.push_back(arr[0]);
    ans++;
    
    for(int i=1; i<N; i++){
        if(v.back() < arr[i]){
            ans++;
            v.push_back(arr[i]);
        }
        else{
            int idx = lower_bound(v.begin(), v.end(), arr[i]) - v.begin();
            v[idx] = arr[i];
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
    
    
    
    return 0;
}
