#include <iostream>
#include <queue>
#include <algorithm>
#include <vector>
 
using namespace std;

int N;
int arr[1000001];
int ans = 1;
vector<int> v;

void solve(){
    v.push_back(arr[0]);
    
    for(int i=1; i<N; i++){
        if(v.back() < arr[i]){
            v.push_back(arr[i]);
            ans++;
        }
        else{
            int idx = lower_bound(v.begin(), v.end(), arr[i]) - v.begin();
            v[idx] = arr[i];
        }
    }
}

int main(){
    scanf("%d", &N);
    
    for(int i=0; i<N; i++)
        scanf("%d", &arr[i]);
    
    solve();
    printf("%d\n", ans);
}
