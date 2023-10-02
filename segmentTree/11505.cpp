#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>
#include <map>


#define MAX 1000005
#define INF 1
#define MOD 1000000007



using namespace std;

typedef long long ll;

int N, M, K;
int arr[MAX];

ll SegTree[4*MAX];


void init(int node, int left, int right){
    
    if(left == right){
        SegTree[node] = arr[left];
        return;
    }
    
    int mid = (left + right)/2;
    init(2*node, left, mid);
    init(2*node+1, mid+1, right);
    
    SegTree[node] = (SegTree[2*node] * SegTree[2*node+1]) % MOD;
    return;
}

ll updateTree(int node, int updatePos, int updateValue, int left, int right){
    if(updatePos < left || updatePos > right) return SegTree[node];
    
    if(left == right){
        SegTree[node] = updateValue;
        return updateValue;
    }
    
    int mid = (left+right)/2;
    ll leftRet = updateTree(node*2, updatePos, updateValue, left, mid);
    ll rightRet = updateTree(node*2+1, updatePos, updateValue, mid+1, right);
    
    SegTree[node] = (leftRet * rightRet) % MOD;
    return SegTree[node];
}

ll queryTree(int node, int curLeft, int curRight, int queryLeft, int queryRight){
    
    if(curLeft > queryRight || curRight < queryLeft){
        return 1;
    }
    else if(queryLeft <= curLeft && curRight <= queryRight){
        return SegTree[node];
    }
    else{
        int mid = (curLeft+curRight)/2;
        ll leftRet = queryTree(node*2, curLeft, mid, queryLeft, queryRight);
        ll rightRet = queryTree(node*2+1, mid+1, curRight, queryLeft, queryRight);
        
        return (leftRet * rightRet) % MOD;
    }
}

void solve(){
    
    init(1, 1, N);
    
    for(int i=0; i<M+K; i++){
        int a,b,c;
        cin >> a >> b >> c;
        if(a == 1){
            updateTree(1, b, c, 1, N);
        }
        else{
            ll ret = queryTree(1, 1, N, b, c);
            cout << ret << '\n';
        }
    }
    
    
    
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N >> M >> K;
    
    for(int i=1; i<=N; i++){
        cin >> arr[i];
    }
    
    solve();
    
    
    return 0;
}
