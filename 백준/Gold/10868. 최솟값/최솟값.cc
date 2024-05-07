#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>
#include <list>
#include <sstream>
#include <cmath>

#define MAX 100005
#define INF 1000000005

using namespace std;

int N, M;
int arr[MAX];
int tree[MAX*4];

int query(int node, int start, int end, int cur_start, int cur_end){
    // 완전 범위 밖 -> INF
    // 걸치는 경우
    // 딱 들어오는 경우
    
    if(cur_start > end || cur_end < start){
        return INF;
    }
    else if(cur_start >= start && cur_end <= end){
        return tree[node];
    }
    else{
        int mid = (cur_start + cur_end) / 2;
        int r1 = query(2*node, start, end, cur_start, mid);
        int r2 = query(2*node+1, start, end, mid+1, cur_end);
        return min(r1, r2);
    }
}

void init(int node, int start, int end){
    if(start == end){
        tree[node] = arr[start];
        return;
    }
    else{
        int mid = (start + end) / 2;
        init(node*2, start, mid);
        init(node*2+1, mid+1, end);
        
        int ret = min(tree[node*2], tree[node*2+1]);
        tree[node] = ret;
        return;
    }
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N >> M;
    
    for(int i=1; i<=N; i++){
        cin >> arr[i];
    }
    
    init(1, 1, N);
    
    for(int i=0; i<M; i++){
        int a,b;
        cin >> a >> b;
        cout << query(1, a, b, 1, N) << '\n';
    }
   
   
}
