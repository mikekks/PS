#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 2005

using namespace std;
int N, M;

vector<int> arr;

int main() {
    cin >> N;
    
    for(int i=0; i<N; i++){
        int tmp;
        cin >> tmp;
        arr.push_back(tmp);
    }
    
    sort(arr.begin(), arr.end());
    
    int ans = 0;
    
    for(int i=0; i<N; i++){
        int cur = arr[i];
        
        int s = 0;
        int e = N-1;
        int sum = 0;
        
        while(s < e){
            sum = arr[s]+arr[e];
            if(sum == cur){
                if(s != i && e != i){
                    ans++;
                    break;
                }
                else if(s == i){
                    s++;
                }
                else{
                    e--;
                }
            }
            else if(sum < cur){
                s++;
            }
            else{
                e--;
            }
        }
        
    }
    
    
    cout << ans << '\n';
}
