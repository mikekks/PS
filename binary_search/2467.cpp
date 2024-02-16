#include <string>
#include <vector>
#include <iostream>
#include <cmath>

#define MAX 100005

using namespace std;

int N;
int num[MAX];
int minPos;
int maxPos;
long long ans;
int ansList[2];

int main(){
    cin >> N;
    maxPos = N-1;
    ans = 2000000000;
    
    for(int i=0; i<N; i++){
        int tmp;
        cin >> tmp;
        num[i] = tmp;
    }
    
    while(minPos < maxPos){
        long long cur = num[minPos] + num[maxPos];
        if(abs(cur) < ans){
            ans = abs(cur);
            ansList[0] = num[minPos];
            ansList[1] = num[maxPos];
        }
        if(cur < 0) minPos++;
        else if(cur >= 0) maxPos--;
    }
   
    
    
    cout << ansList[0] << " " << ansList[1] << '\n';
    
}
