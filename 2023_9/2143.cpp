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

int n,m;
int T;
int A_in[MAX];
int B_in[MAX];

vector<ll> vA;
vector<ll> vB;


ll ans;

void solve(){
    
    for(int i=1; i<=n; i++){
        ll sum = A_in[i];
        vA.push_back(sum);
        for(int j=i+1; j<=n; j++){
            sum += A_in[j];
            vA.push_back(sum);

        }
    }
    for(int i=1; i<=m; i++){
        ll sum = B_in[i];
        vB.push_back(sum);
        for(int j=i+1; j<=m; j++){
            sum += B_in[j];
            vB.push_back(sum);

        }
    }
    
    sort(vA.begin(), vA.end());
    sort(vB.begin(), vB.end());
    
    for(int i=0; i<vA.size(); i++){
        ll target = T - vA[i];
        
        int s = lower_bound(vB.begin(), vB.end(), target) - vB.begin();
        int e = upper_bound(vB.begin(), vB.end(), target) - vB.begin();
        
        ans += e - s;

    }

    
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> T;
    
    cin >> n;
    for(int i=1; i<=n; i++) cin >> A_in[i];

    cin >> m;
    for(int i=1; i<=m; i++) cin >> B_in[i];
    
    solve();
    
    cout << ans << '\n';
    
    return 0;
}
