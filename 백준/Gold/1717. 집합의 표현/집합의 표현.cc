#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 1000005

using namespace std;

// 13:10 시작

int N, M;
int p[MAX];
int ans = 0;

int find_parent(int a){
    if(p[a] == a) return a;
    
    return p[a] = find_parent(p[a]);
}

void mergeNode(int a, int b){
    a = find_parent(a);
    b = find_parent(b);
    
    if(a < b){
        p[b] = a;
    }
    else{
        p[a] = b;
    }
}


int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    
    cin >> N >> M;
    
    for(int i=1; i<=N; i++){
        p[i] = i;
    }
    
    for(int i=0; i<M; i++){
        int oper,a,b;
        cin >> oper >> a >> b;
        
        if(oper == 0){
            mergeNode(a,b);
        }
        else{
            int pA = find_parent(a);
            int pB = find_parent(b);
            if(pA == pB){
                cout << "YES" << '\n';
            }
            else{
                cout << "NO" << '\n';
            }
        }
    }
    
}
