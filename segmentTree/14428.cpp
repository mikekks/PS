#include <iostream>
#include <algorithm>
#include <math.h>
#include <queue>
#include <vector>
#include <cstring>
#include <map>


#define MAX 100005
#define INF 1500000000


using namespace std;

typedef long long ll;

int N, M;

pair<int, int> minSegTree[4 * MAX];
int arr[MAX];

void init(int node, int start, int end){
    if(start == end){
        minSegTree[node].first = arr[start];
        minSegTree[node].second = start;
        return;
    }
    else{
        int mid = (start + end) / 2;
        init(node*2, start, mid);
        init(node*2 + 1, mid+1, end);
        
        if(minSegTree[node*2].first <= minSegTree[node*2+1].first){
            minSegTree[node].first = minSegTree[node*2].first;
            minSegTree[node].second = minSegTree[node*2].second;
        }else if(minSegTree[node*2].first > minSegTree[node*2+1].first){
            minSegTree[node].first = minSegTree[node*2+1].first;
            minSegTree[node].second = minSegTree[node*2+1].second;
        }
        
        return;
    }
}

pair<int, int> update(int idx, int updateValue, int node, int start, int end){
    
    if(idx < start || idx > end) return minSegTree[node];
    
    if(start == end){
        minSegTree[node].first = updateValue;
        minSegTree[node].second = start;
        return minSegTree[node];
    }
    
    int mid = (start + end) / 2;
    pair<int, int> leftValue = update(idx, updateValue, node*2, start, mid);
    pair<int, int> rightValue = update(idx, updateValue, node*2+1, mid+1, end);
    
    minSegTree[node] = min(leftValue, rightValue);
    return minSegTree[node];
}

pair<int, int> queryMin(int node, int queryStart, int queryEnd, int curStart, int curEnd){
    
    if(curStart > queryEnd || curEnd < queryStart ){
        return {INF, INF};
    }
    else if(queryStart <= curStart && curEnd <= queryEnd){
        return minSegTree[node];
    }
    else{
        int mid = (curStart + curEnd)/2;
        pair<int, int> leftValue = queryMin(node*2, queryStart, queryEnd, curStart, mid);
        pair<int, int> rigthValue = queryMin(node*2+1, queryStart, queryEnd, mid+1, curEnd);
        return min(leftValue, rigthValue);
    }
}


void solve(){
    for(int i=0; i<MAX; i++){
        minSegTree[i].first = INF;
        minSegTree[i].second = INF;
    }
    init(1, 1, N);
    
    for(int i=0; i<M; i++){
        int oper;
        cin >> oper;
        if(oper == 1){
            int i,v;
            cin >> i >> v;
            update(i, v, 1, 1, N);
        }
        else{
            int i,j;
            cin >> i >> j;
            pair<int, int> ret = queryMin(1, i, j, 1, N);
            cout << ret.second << '\n';
        }
    }
    
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> N;
    
    for(int i=1; i<=N; i++){
        cin >> arr[i];
    }
    
    cin >> M;
    
    solve();
    
    
    return 0;
}
