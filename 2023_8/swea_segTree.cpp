#include <iostream>
#include <algorithm>
#include <cstring>

#define INF 2000000000
#define MAX 100005

using namespace std;

int n,q;

int min_segtree[4 * MAX];
int max_segtree[4 * MAX];
int a[MAX];

void init(int node, int start, int end){
  if(start == end){
      min_segtree[node] = max_segtree[node] = a[start];
    return;
  }else{
    init(node*2, start, (start + end)/2 );
    init(node*2 +1 , (start + end)/2 +1, end);
    min_segtree[node] = min(min_segtree[node*2], min_segtree[node*2+1]);
    max_segtree[node] = max(max_segtree[node*2], max_segtree[node*2+1]);
    return;
  }
}


pair<int, int> update(size_t i, int x, int node, int l, int r) {
    
    if(i < l || r < i) return {min_segtree[node], max_segtree[node]};
    
    if(l == r){
        min_segtree[node] = max_segtree[node] = x;
        return {x, x};
    }
    
    int mid = (l+r)/2;
    pair<int, int> lv = update(i, x, node*2, l, mid);
    pair<int, int> rv = update(i, x, node*2+1, mid+1, r);
    
    min_segtree[node] = min(lv.first, rv.first);
    max_segtree[node] = max(lv.second, rv.second);
    return {min_segtree[node], max_segtree[node]};
}

pair<int, int> query_minMax(int node, int left, int right, int nodeLeft, int nodeRight) {

    if(nodeLeft > right || nodeRight < left){
        return {INF, 0};
    }
    else if(left <= nodeLeft && nodeRight <= right){
        return {min_segtree[node], max_segtree[node]};
    }else{
        pair<int, int> l_value,r_value;
        int mid = (nodeLeft+nodeRight)/2;
        l_value = query_minMax(2*node, left, right , nodeLeft, mid);
        r_value = query_minMax(2*node+1, left, right, mid+1, nodeRight);
        return {min(l_value.first, r_value.first), max(l_value.second, r_value.second)};
    }

}

int main(int argc, char** argv)
{
    int test_case;
    int T;

    //freopen("input.txt", "r", stdin);
    scanf("%d", &T);
    /*
       여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
    */
    for(test_case = 1; test_case <= T; ++test_case)
    {
        memset(a, '\0', sizeof(a));
        scanf("%d %d", &n, &q);
        
        for(int i=0; i<n; i++){
            int tmp;
            scanf("%d", &tmp);
            a[i] = tmp;
        }
        
        
        memset(min_segtree, '\0', sizeof(min_segtree));
        memset(max_segtree, '\0', sizeof(max_segtree));
        
        init(1,0,n-1);
        
        printf("#%d ", test_case);
        
        for(int i=0; i<q; i++){
            int oper, x,y;
            scanf("%d %d %d", &oper, &x, &y);
            
            if(oper == 0){
                update(x, y, 1, 0, n-1);
            }
            else{
                pair<int, int> sol = query_minMax(1, x, y-1, 0, n-1);
                printf("%d ", sol.second - sol.first);
            }
            
        }
        
        printf("\n");

    }
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}
