#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 200005

using namespace std;
int N;
int ans = 987654321;

vector<int> arr;

vector<int> dArr;

int gcd(int a, int b){
    while (b != 0) {
        int tmp = a % b;
        a = b;
        b = tmp;
    }
    
    return a;
}

int main() {
    cin >> N;
    
    for(int i=0; i<N; i++){
        int tmp;
        cin >> tmp;
        arr.push_back(tmp);
    }
    
    sort(arr.begin(), arr.end());
    
    for(int i=0; i<N-1; i++){
        int diff = abs(arr[i]-arr[i+1]);
        dArr.push_back(diff);
    }
   
    
    int ret = dArr[0];
    for(int i=1; i<dArr.size(); i++){
        ret = gcd(ret, dArr[i]);
    }
    
    vector<int> ans;
    ans.push_back(ret);
    
    for(int i=2; i*i<=ret; i++){
        if(ret % i == 0){
            ans.push_back(i);
            
            if(i != ret/i){
                ans.push_back(ret/i);
            }
        }
    }
    
    sort(ans.begin(), ans.end());
    
    for(int i=0; i<ans.size(); i++){
        cout << ans[i];
        if(i+1 != ans.size()){
            cout << " ";
        }
    }
    
}
