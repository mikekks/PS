#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>

using namespace std;

int G, P;
int p[100001];
int ans;

int find(int n){
    if(n == p[n]){
        return n;
    }
    else{
        return p[n] = find(p[n]);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> G >> P;
    
    for(int i=1; i<=G; i++){
        p[i] = i;
    }
    
    int tmp;
    
    for(int i=1; i<=P; i++){
        cin >> tmp;
        int ret = find(tmp);
        
        if(ret == 0){
            break;
        }
        
        ans++;
        p[ret] = p[ret - 1];
        
    }
    
    cout << ans << '\n';
    
    return 0;
}
