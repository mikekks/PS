#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

#define MAX 100001
using namespace::std;

int N;
vector<pair<int, int>> q;


int main(){
    scanf("%d", &N);
    
    for(int i=0; i<N; i++){
        int t1,t2;
        scanf("%d %d", &t1, &t2);
        q.push_back({t1,t2});
    }
    sort(q.begin(), q.end());
    
    for(int i=0; i<N; i++){
        printf("%d %d\n", q[i].first, q[i].second);
    }
}
