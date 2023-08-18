#include <iostream>
#include <vector>
#include <cmath>

#define MAX 1000000

using namespace::std;

typedef unsigned long long ll;

int n;
int A,B,C;

//int Map[MAX+1];
vector<ll> tree;

int query(int node, int start, int end, int cnt){
    if(start == end)
        return start;
    
    int mid = (start+end) / 2;
    if(tree[2*node] >= cnt){
        return query(2*node, start, mid, cnt);
    }
    else{
        return query(2*node+1, mid+1, end, cnt - tree[2*node]);
    }
}

void update(int node, int start, int end, int diff, int idx){
    
    if(idx < start || idx > end) return;
    tree[node] += diff;
    
    if(start != end){
        int mid = (start+end)/2;
        update(2*node, start, mid, diff, idx);
        update(2*node+1, mid+1, end, diff, idx);
    }
    
}

void make_tree(){
    int h = (int) ceil(log2(MAX));
    int Size = 1 << (h + 1);
    tree.resize(Size);
}

int main(){
    scanf("%d", &n);
    
    make_tree();
    
    for(int i=0; i<n; i++){
        scanf("%d", &A);
        
        if(A == 1){  // 빼기
            scanf("%d", &B);
            int ans = query(1, 1, MAX, B);
            printf("%d\n", ans);
            //Map[ans]--;
            update(1, 1, MAX, -1, ans);
        }
        else{  // 넣기
            scanf("%d %d", &B, &C);
            //Map[B] += C;
            update(1, 1, MAX, C, B);
        }
    }
}
