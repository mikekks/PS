#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <cmath>
#include <algorithm>
#include <cstring>

#define MAX 500005
#define INF 987654321

using namespace std;

int N, M;

int p[MAX];

int find(int x) {
    if (p[x] == x)
        return x;
    return p[x] = find(p[x]);
}

void merge(int x, int y) {
    x = find(x);
    y = find(y);
    
    if (x == y) return;
    
    p[y] = x;
}

bool isCycle(int x, int y){
    x = find(x);
    y = find(y);
    
    if(x == y) return true;
    else return false;
}

int main(){
    cin >> N >> M;
    bool isFind = false;
    
    for(int i=0; i<N; i++){
        p[i] = i;
    }
    
    for(int i=1; i<=M; i++){
        int a,b;
        cin >> a >> b;
        if(isCycle(a, b)){
            cout << i << '\n';
            isFind = true;
            break;
        }
        merge(a,b);
        
    }
    
    if(!isFind) cout << 0 << '\n';
}
