#include <iostream>
#include <string>
#include <sstream>
#include <cmath>
#include <vector>
#include <algorithm>
#include <queue>
#include <cstring>
#include <climits>

using namespace std;

#define MAX 2005
int N, C;
int ans;
vector<int> arr;

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    
    cin >> N >> C;
    
    for(int i=0; i<N; i++){
        int tmp;
        cin >> tmp;
        arr.push_back(tmp);
    }
    
    sort(arr.begin(), arr.end());
    
    int start = 1;
    int end = arr[arr.size()-1];
    
    while (start<=end) {
        int mid = (start+end)/2;
        int cnt = 1;
        int prev = arr[0];
        
        for(int i=1; i<N; i++){
            if(arr[i] - prev >= mid){
                prev = arr[i];
                cnt++;
            }
        }
        
        if(cnt >= C){
            ans = mid > ans ? mid : ans;
            start = mid+1;
        }
        else{
            end = mid-1;
        }
    }
    
    cout << ans << '\n';
    
    return 0;
}
