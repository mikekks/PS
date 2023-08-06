#include <iostream>
#include <algorithm>
#include <cstring>

#define INF 2000000000
#define MAX 100005

using namespace std;

int N, M;

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

    scanf("%d %d", &N, &M);
    
    memset(a, '\0', sizeof(a));
    
    for(int i=0; i<N; i++){
        int tmp;
        scanf("%d", &tmp);
        a[i] = tmp;
    }
    
    init(1,0,N-1);
        
    for(int i=0; i<M; i++){
        int a,b;
        scanf("%d %d", &a, &b);
        pair<int, int> sol = query_minMax(1, a-1, b-1, 0, N-1);
        printf("%d %d\n", sol.first, sol.second);
        
    }

    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}
