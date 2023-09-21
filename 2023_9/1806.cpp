#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>

#define MAX 100005

using namespace std;

typedef long long ll;

int N, S;

int arr[MAX];
int ans = 987654321;

void solve(){
    int sP = 0;
    
    int curValue = 0;
    for(int i=0; i<N; i++){
        curValue += arr[i];
        
        while(curValue >= S){
            ans = min(i - sP + 1, ans);
            curValue -= arr[sP++];
        }
    }
    
    if(ans == 987654321){
        ans = 0;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N >> S;
    
    for(int i=0; i<N; i++){
        cin >> arr[i];
    }
    
    solve();
    
    cout << ans << '\n';
        
    return 0;
}
