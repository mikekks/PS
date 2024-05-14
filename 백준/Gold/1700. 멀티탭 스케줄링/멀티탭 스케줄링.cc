#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>
#include <list>
#include <sstream>
#include <cmath>

#define MAX 105

using namespace std;
int N, K;
int order[MAX];


int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N >> K;
    int ans = 0;
    
    for(int i=0; i<K; i++){
        cin >> order[i];
    }
    
    
    int plug[K];
    memset(plug, 0, sizeof(plug));
    
    for(int i=0; i<K; i++){
        bool isCatch = false;
        
        for(int j=0; j<N; j++){
            if(order[i] == plug[j]){
                isCatch = true;
                break;
            }
        }
        
        if(isCatch) continue;
        
        for(int j=0; j<N; j++){
            if(plug[j] == 0){
                isCatch = true;
                plug[j] = order[i];
                break;
            }
        }
        
        if(isCatch) continue;
        
        int catchList[MAX];
        memset(catchList, MAX, sizeof(catchList));
        
        for(int j=0; j<N; j++){
            int idx = -1;
            for(int k=i+1; k<K; k++){
                if(plug[j] == order[k]){
                    catchList[j] = k;
                    break;
                }
            }
        }
        
        int tmp = -1;
        int pos = -1;
        
        for(int j=0; j<N; j++){
            if(catchList[j] > tmp){
                tmp = catchList[j];
                pos = j;
            }
        }
        
        plug[pos] = order[i];
        ans++;
    }
    
    cout << ans << '\n';
    
}
