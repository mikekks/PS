#include <iostream>
#include <vector>
#include <cmath>

#define MAX 1000001

using namespace::std;

typedef long long ll;

int N,M,K;
int A,B,C;
ll D;

ll tree[MAX * 4];
ll Arr[MAX];  // 입력값
ll lazy[MAX * 4];

void check_lazy(int node, int start, int end){
    if(lazy[node] != 0){
        tree[node] += (end-start+1) * lazy[node];
        if(start != end){
            lazy[2*node] += lazy[node];
            lazy[2*node+1] += lazy[node];
        }
        lazy[node] = 0;
    }
}

ll query(int node, int start, int end, int cal_L, int cal_R){
    check_lazy(node, start, end);
    
    if(cal_L > end || cal_R < start) return 0;
    
    if(cal_L <= start && cal_R >= end){
        return tree[node];
    }

    int mid = (start+end) / 2;
    return query(2*node, start, mid, cal_L, cal_R) + query(2*node+1, mid+1, end, cal_L, cal_R);
}

void update(int node, int start, int end, ll diff, int cal_L, int cal_R){
    // lazy check
    check_lazy(node, start, end);
    
    if(cal_L > end || cal_R < start) return;
    
    if(cal_L <= start && cal_R >= end){  // 구간에 완전히 들어왔을 때
        // 자식 노드 수 구하기
        tree[node] += diff * (end - start + 1);
        if(start != end){
            lazy[2*node] += diff;
            lazy[2*node+1] += diff;
        }
        return;
    }
    
    int mid = (start+end)/2;
    update(2*node, start, mid, diff, cal_L, cal_R);
    update(2*node+1, mid+1, end, diff, cal_L, cal_R);
    tree[node] = tree[2*node] + tree[2*node+1];
    
}

void make_tree(int node, int start, int end){
    if(start == end){
        tree[node] = Arr[start];
        return;
    }
    
    int mid = (start+end)/2;
    make_tree(2*node, start, mid);
    make_tree(2*node+1, mid+1, end);
    tree[node] = tree[2*node] + tree[2*node+1];
}


int main(){
    scanf("%d %d %d", &N, &M, &K);
    for(int i=1; i<=N; i++){
        scanf("%lld", &Arr[i]);
    }

    make_tree(1, 1, N);
    
    for(int i=0; i<M+K; i++){
        scanf("%d", &A);
        if(A == 1){  // b번째 수부터 c번째 수에 d를 더하고
            scanf("%d %d %lld", &B, &C, &D);
            update(1, 1, N, D, B, C);
        }
        else{  //  b번째 수부터 c번째 수의 합을 구하여 출력하면 된다.
            scanf("%d %d", &B, &C);
            ll ans = query(1, 1, N, B, C);
            printf("%lld\n", ans);
        }
    }

}
