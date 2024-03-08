#include <cstring>
#include <vector>
#include <iostream>
#include <cmath>
#include <algorithm>

#define MAX 100005

using namespace std;

int N;
int cur[3];

int max_arr[3];
int min_arr[3];


int main(){
    cin >> N;
    
    cin >> cur[0];
    cin >> cur[1];
    cin >> cur[2];
    
    max_arr[0] = cur[0];
    min_arr[0] = cur[0];
    max_arr[1] = cur[1];
    min_arr[1] = cur[1];
    max_arr[2] = cur[2];
    min_arr[2] = cur[2];

    
    for(int i=1; i<N; i++){
        cin >> cur[0];
        cin >> cur[1];
        cin >> cur[2];
        
        int tmp0 = max_arr[0];
        int tmp2 = max_arr[2];
        max_arr[0] = max(max_arr[0], max_arr[1]) + cur[0];
        max_arr[2] = max(max_arr[1], max_arr[2]) + cur[2];
        max_arr[1] = max(max(tmp0, max_arr[1]), tmp2) + cur[1];
        
        tmp0 = min_arr[0];
        tmp2 = min_arr[2];
        
        min_arr[0] = min(min_arr[0], min_arr[1]) + cur[0];
        min_arr[2] = min(min_arr[1], min_arr[2]) + cur[2];
        min_arr[1] = min(min(tmp0, min_arr[1]), tmp2) + cur[1];
    }

  
    int result_max = max(max(max_arr[0], max_arr[2]), max_arr[1]);

    int result_min = min(min(min_arr[0], min_arr[2]), min_arr[1]);

    cout << result_max << " " << result_min << '\n';
}
