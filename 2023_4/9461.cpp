#include <iostream>
#include <queue>
#include <algorithm>
#include <vector>
#include <cmath>
#include <cstring>
#include <list>

using namespace std;

int N;
list<int> nums;

int main(){
    scanf("%d", &N);
    
    for(int i=0; i<N; i++){
        int tmp = 0;
        scanf("%d", &tmp);
        nums.push_back(tmp);
    }
    
    long long arr[101];
    arr[1]=1;
    arr[2]=1;
    arr[3]=1;
    arr[4]=2;
    arr[5]=2;
    
    for(int j=6; j<=100; j++){
        arr[j] = arr[j-1]+arr[j-5];
    }
    for(int i=0; i<N; i++){
        int tmp2 = nums.front();
        nums.pop_front();
        printf("%lld\n", arr[tmp2]);
    }
    
   
}
