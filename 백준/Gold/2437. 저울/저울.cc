#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <cmath>
#include <algorithm>
#include <cstring>
#include <stack>

#define MAX 1000000000

using namespace std;

int N;
int ans;

vector<int> arr;


int main(){
    cin >> N;
    
    for(int i=0; i<N; i++){
        int tmp;
        cin >> tmp;
        arr.push_back(tmp);
    }
    
    sort(arr.begin(), arr.end());
    
    
    ans = 1;
    
    for(int i=0; i<N; i++){
        if(arr[i] > ans){
            break;
        }
        ans += arr[i];
    }
    
    
    cout << ans << '\n';

   
}
