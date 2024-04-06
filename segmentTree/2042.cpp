#include <string>
#include <vector>
#include <iostream>
#include <queue>

#define MAX 4000000

using namespace std;

int N, M, K;

long long tree[MAX];
long long arr[1000005];

long long query(int node, int cur_s, int cur_e, long long target_s, long long target_e){
    
    if(cur_e < target_s || cur_s > target_e){
        return 0;
    }
    else if(target_s <= cur_s && cur_e <= target_e){
        return tree[node];
    }
    
    int mid = (cur_s + cur_e)/2;
    long long left = query(2*node, cur_s, mid, target_s, target_e);
    long long right = query(2*node+1, mid+1, cur_e, target_s, target_e);
    
    return left + right;
}


void modify(long long idx, int node, long long diff, int s, int e){
    if(idx < s || idx > e){
        return;
    }
    tree[node] = tree[node] + diff;
    
    if(s != e){
        int mid = (s+e)/2;
        modify(idx, 2*node, diff, s, mid);
        modify(idx, 2*node+1, diff, mid+1, e);
    }
}

long long init(int node, int s, int e){
    if(s == e) {
        tree[node] = arr[s];
        return tree[node];
    }
    
    int mid = (s+e)/2;
    long long left = init(2*node, s, mid);
    long long right = init(2*node+1, mid+1, e);
    tree[node] = left + right;
    
    return tree[node];
}

int main(){
    cin >> N >> M >> K;
    
    for(int i=1; i<=N; i++){
        long long tmp;
        cin >> tmp;
        arr[i] = tmp;
    }
    
    init(1, 1, N);
    
    for(int i=0; i<M+K; i++){
        long long a, b, c;
        cin >> a >> b >> c;
        
        if(a == 1){
            long long diff = c - arr[b];
            arr[b] = c;
            modify(b, 1, diff, 1, N);
        }
        else{
            long long ret = query(1, 1, N, b, c);
            cout << ret << '\n';
        }
    }
}
