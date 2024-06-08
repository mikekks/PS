#include <iostream>
#include <string>
#include <sstream>
#include <cmath>
#include <vector>
#include <algorithm>
#include <queue>
#include <cstring>
#include <climits>

using namespace std;

#define MAX 205
int N, M;
bool connect[MAX][MAX];
vector<int> route;
int p[MAX];
bool ans = true;

int findParent(int x){
    if(x == p[x]) return x;
    
    return p[x] = findParent(p[x]);
}

void merge(int a, int b){
    a = findParent(a);
    b = findParent(b);
    
    if(a < b){
        p[b] = a;
    }
    else{
        p[a] = b;
    }
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    
    cin >> N;
    cin >> M;
    
    for(int i=1; i<=N; i++){
        p[i] = i;
        for(int j=1; j<=N; j++){
            int tmp;
            cin >> tmp;
            if(tmp == 1){
                connect[i][j] = true;
            }
        }
    }
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            if(connect[i][j]){
                merge(i, j);
            }
        }
    }
    
    for(int i=0; i<M; i++){
        int tmp;
        cin >> tmp;
        route.push_back(tmp);
    }
    
    
    int oper = findParent(route[0]);
    for(int i=1; i<M; i++){
        if(oper != findParent(route[i])){
            ans = false;
            break;
        }
    }
    
    
    if(ans){
        cout << "YES" << '\n';
    }
    else{
        cout << "NO" << '\n';
    }
    
    return 0;
}
