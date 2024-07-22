#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 505
#define INF 987654321

using namespace std;

// 01:10 시작

int N, T;


int main() {
//    ios::sync_with_stdio(false);
//    cin.tie(0);
//    cout.tie(0);
    
    cin >> T;
    
    while (T--) {
        cin >> N;
        vector<string> arr;
        
        for(int i=0; i<N; i++){
            string tmp;
            cin >> tmp;
            arr.push_back(tmp);
        }
        
        std::sort(arr.begin(), arr.end());
        
        bool check = true;
        
        for(int i=0; i<N-1; i++){
            string cur = arr[i];
            string next = arr[i+1];
            
            if(next.find(cur) == 0){
                check = false;
                break;
            }
        }
        
        if(check){
            cout << "YES" << '\n';
        }
        else{
            cout << "NO" << '\n';
        }
        
    }
    
}
