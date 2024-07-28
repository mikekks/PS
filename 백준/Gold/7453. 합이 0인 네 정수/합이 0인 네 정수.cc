#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>
#include <vector>
#include <algorithm>
#include <cstring>

#define MAX 4005

using namespace std;

int A[MAX], B[MAX], C[MAX], D[MAX];

vector<int> sum1;
vector<int> sum2;

int main(void)
{
    int N;
    cin >> N;

    for(int i=0; i<N; i++){
        cin >> A[i] >> B[i] >> C[i] >> D[i];
    }
    
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            sum1.push_back(A[i] + B[j]);
        }
    }
    
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            sum2.push_back(C[i] + D[j]);
        }
    }
    
    sort(sum1.begin(), sum1.end());
    sort(sum2.begin(), sum2.end());

    long long ans = 0;
    
    for(int i=0; i<sum1.size(); i++){
        long long start_idx = lower_bound(sum2.begin(), sum2.end(), -sum1[i]) - sum2.begin();
        long long end_idx = upper_bound(sum2.begin(), sum2.end(), -sum1[i]) - sum2.begin();
        
        ans += (end_idx - start_idx);
    }
    
    
    cout << ans << '\n';
    

    return 0;
}
